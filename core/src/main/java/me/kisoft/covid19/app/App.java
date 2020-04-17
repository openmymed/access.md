/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.app;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import static io.javalin.core.security.SecurityUtil.roles;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.java.Log;
import me.kisoft.covid19.app.service.BackgroundService;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.enums.UserRole;
import static me.kisoft.covid19.domain.auth.enums.UserRole.NONE;
import static me.kisoft.covid19.domain.auth.enums.UserRole.ROLE_ADMIN;
import static me.kisoft.covid19.domain.auth.enums.UserRole.ROLE_DOCTOR;
import static me.kisoft.covid19.domain.auth.enums.UserRole.ROLE_PATIENT;
import me.kisoft.covid19.domain.auth.repo.UserRepository;
import me.kisoft.covid19.domain.core.entity.ICPCEntry;
import me.kisoft.covid19.domain.core.enums.ICPCType;
import me.kisoft.covid19.domain.event.EventBus;
import me.kisoft.covid19.infra.auth.factory.UserRepositoryFactory;
import me.kisoft.covid19.infra.core.service.rest.PatientRestService;
import me.kisoft.covid19.infra.auth.service.rest.UserRestService;
import me.kisoft.covid19.infra.core.factory.ICPCServiceFactory;
import me.kisoft.covid19.infra.core.service.rest.DoctorRestService;
import me.kisoft.covid19.infra.core.service.rest.ICPCRestService;
import me.kisoft.covid19.infra.factory.EntityManagerFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;

/**
 *
 * @author tareq
 */
@Log
public class App {

  private static Javalin app;

  public static void main(String[] args) throws Throwable {

    String persistenceUnitName = "app_dev_PU";
    if (Boolean.valueOf(System.getProperty("app.production", "false"))) {
      persistenceUnitName = "app_prod_PU";
    }
    EntityManagerFactory.getInstance().setPersistenceUnit(persistenceUnitName);
    readICPCCodes();
    startBackgroundServices();
    startServer();
    registerDomainHandlers();
    registerDerbyShutdownHook();
    createAdminIfNotFound();
  }

  public static UserRole getUserRole(Context ctx) {
    User user = ctx.sessionAttribute("user");
    if (user == null || user.getUserRole() == null) {
      return UserRole.NONE;
    }
    return user.getUserRole();
  }

  private static void startBackgroundServices() {
    BackgroundService.startBackgroundServices();
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        BackgroundService.stopBackgroundServices();
      }
    }));
  }

  private static void registerDomainHandlers() throws Throwable {
    EventBus.getInstance().searchForHandlers();
  }

  private static void registerDerbyShutdownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          String jdbc = String.valueOf(EntityManagerFactory.getInstance().get().getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url"));
          DriverManager.getConnection(jdbc + ";shutdown=true");
        } catch (SQLException ex) {
          Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }));
  }

  public static void startServer() {
    app = Javalin.create().start(7000);

    // Set the access-manager that Javalin should use
    app.config.accessManager((handler, ctx, permittedRoles) -> {
      if (!permittedRoles.contains(NONE)) {
        UserRole userRole = getUserRole(ctx);
        if (!permittedRoles.contains(userRole)) {
          ctx.status(401).result("Unauthorized");
          return;
        }
      }
      handler.handle(ctx);
    });

    UserRestService userService = new UserRestService();
    PatientRestService patientService = new PatientRestService();
    ICPCRestService icpcService = new ICPCRestService();
    DoctorRestService doctorService = new DoctorRestService();
    app.routes(() -> {
      path("login", () -> {
        post(userService::signIn, roles(NONE));
      });
      path("symptom", () -> {
        path("codes", () -> {
          get(icpcService::getICPCSymptoms, roles(NONE));
        });
      });
      path("patient", () -> {
        path("doctor",() ->{
          get(patientService::getDoctor, roles(ROLE_PATIENT));
        });
        path("signup", () -> {
          post(patientService::signUp, roles(NONE));
        });
        path("profile", () -> {
          put(patientService::updateMedicalProfile, roles(ROLE_PATIENT));
          get(patientService::getMedicalProfile, roles(ROLE_PATIENT));
        });
        path("reccomendation", () -> {
          get(patientService::getReccomendations, roles(ROLE_PATIENT));
        });
        path("question", () -> {
          get(patientService::getUnansweredQuestions, roles(ROLE_PATIENT));
          path(":id", () -> {
            path("answer", () -> {
              put(patientService::answerQuestion, roles(ROLE_PATIENT));
            });
          });
        });
        path("code", () -> {
          get(patientService::getSecurityCode, roles(ROLE_PATIENT));
        });
        path("symptom", () -> {
          post(patientService::addSymptom, roles(ROLE_PATIENT));
        });
      });
      path("doctor", () -> {
        path("feed", () -> {
          get(doctorService::getPatientsFeed, roles(ROLE_DOCTOR));
        });
        path("patient", () -> {
          path("symptom", () -> {
            path("count", () -> {
              get(doctorService::getUnseenSymptomCount, roles(ROLE_DOCTOR));
            });
          });
          path("answer", () -> {
            path("count", () -> {
              get(doctorService::getUnseenAnswerCount, roles(ROLE_DOCTOR));
            });
          });
          path("count", () -> {
            get(doctorService::getPatientCount, roles(ROLE_DOCTOR));
          });
          get(doctorService::listPatients, roles(ROLE_DOCTOR));
          path("add", () -> {
            post(doctorService::consumePatientCode, roles(ROLE_DOCTOR));
          });
          path(":id", () -> {
            get(doctorService::getPatient, roles(ROLE_DOCTOR));
            path("profile", () -> {
              get(doctorService::getPatientProfile, roles(ROLE_DOCTOR));
            });
            path("symptom", () -> {
              get(doctorService::listPatientSymptoms, roles(ROLE_DOCTOR));
              path(":symptom_id", () -> {
                path("seen", () -> {
                  put(doctorService::markSymptomSeen, roles(ROLE_DOCTOR));
                });
              });
            });
            path("answer", () -> {
              get(doctorService::listPatientAnswers, roles(ROLE_DOCTOR));
              path(":answer_id", () -> {
                path("seen", () -> {
                  put(doctorService::markAnswerSeen, roles(ROLE_DOCTOR));
                });
              });
            });
            path("question", () -> {
              get(doctorService::listPatientQuestions, roles(ROLE_DOCTOR));
              post(doctorService::createPatientQuestion, roles(ROLE_DOCTOR));
              path(":question_id", () -> {
                delete(doctorService::deletePatientQuestion, roles(ROLE_DOCTOR));
                put(doctorService::updatePatientQuestion, roles(ROLE_DOCTOR));
                get(doctorService::getPatientQuestion, roles(ROLE_DOCTOR));
              });
            });
          });
        });

      });
      path("admin", () -> {
        path("doctor", () -> {
          post(doctorService::createDoctor, roles(ROLE_ADMIN));
          get(doctorService::getDoctors, roles(ROLE_ADMIN));
        });
      });
    });

    app.exception(Exception.class, (e, ctx) -> {
      e.printStackTrace();
      ctx.status(500);
      ctx.result(e.getMessage());
    });
    app.config.sessionHandler(() -> fileSessionHandler());
    if (Boolean.valueOf(System.getProperty("app.production", "false"))) {
      app.config.addStaticFiles("/webapp");
    } else {
      app.config.addStaticFiles("./src/main/webapp/dist", Location.EXTERNAL);
    }

  }

  private static SessionHandler fileSessionHandler() {
    SessionHandler sessionHandler = new SessionHandler();
    SessionCache sessionCache = new DefaultSessionCache(sessionHandler);
    sessionCache.setSessionDataStore(fileSessionDataStore());
    sessionHandler.setSessionCache(sessionCache);
    sessionHandler.setHttpOnly(true);
    // make additional changes to your SessionHandler here
    return sessionHandler;
  }

  private static FileSessionDataStore fileSessionDataStore() {
    FileSessionDataStore fileSessionDataStore = new FileSessionDataStore();
    File baseDir = new File(System.getProperty("java.io.tmpdir"));
    File storeDir = new File(baseDir, "javalin-session-store");
    storeDir.mkdir();
    fileSessionDataStore.setStoreDir(storeDir);
    return fileSessionDataStore;
  }

  private static void readICPCCodes() {
    try {
      ArrayList<ICPCEntry> icpcEntries = new ArrayList<>();
      BufferedReader reader = new BufferedReader(new InputStreamReader(
              App.class.getClassLoader().getResourceAsStream("icpc_en.csv")));
      CSVParser csvFileParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'));
      csvFileParser.iterator().forEachRemaining(record -> {
        icpcEntries.add(new ICPCEntry(record.get(0), record.get(1), ICPCType.valueOf(record.get(2))));
      });
      ICPCServiceFactory.getInstance().get().setEntries(icpcEntries);
    } catch (IOException ex) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
    }
    Logger.getLogger(App.class.getName()).log(Level.INFO, "Done Reading ICPC Codes");

  }

  public static void stopServer() {
    app.stop();
  }

  private static void createAdminIfNotFound() {
    try ( UserRepository repo = UserRepositoryFactory.getInstance().get()) {
      if (repo.getUsersByRole(ROLE_ADMIN).isEmpty()) {
        log.info("No Admins Found; Creating one now");
        String password = RandomStringUtils.random(16, true, true);
        User user = new User();
        user.setUsername("admin");
        user.setUserRole(ROLE_ADMIN);
        user.setPassword(password);
        repo.save(user);
        log.log(Level.INFO, "Admin Username is 'admin' and Admin Password is {0}", password);
      }
    }
  }

}
