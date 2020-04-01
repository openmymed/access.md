/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.app;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.java.Log;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.entity.UserRole;
import static me.kisoft.covid19.domain.auth.entity.UserRole.NONE;
import me.kisoft.covid19.domain.event.EventBus;
import me.kisoft.covid19.infra.factory.EntityManagerFactory;
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
    startServer();
    registerDomainHandlers();
    registerDerbyShutdownHook();
  }

  public static UserRole getUserRole(Context ctx) {
    User user = ctx.sessionAttribute("user");
    if (user == null || user.getUserRole() == null) {
      return UserRole.NONE;
    }
    return user.getUserRole();
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

  public static void stopServer() {
    app.stop();
  }

}
