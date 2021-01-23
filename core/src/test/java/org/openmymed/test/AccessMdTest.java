/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test;

import org.openmymed.test.pages.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openmymed.accessmd.app.App;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.auth.enums.UserRole;
import org.openmymed.accessmd.domain.core.service.DoctorService;
import org.openmymed.accessmd.infra.core.factory.DoctorServiceFactory;
import org.openmymed.accessmd.infra.factory.EntityManagerFactory;

/**
 *
 * @author tareq
 */
@Log
public abstract class AccessMdTest {

    private DoctorService doctorService = DoctorServiceFactory.getInstance().get();

    @BeforeClass
    public static final void initTest() throws InterruptedException, Throwable {
        System.setProperty("selenide.browser", "Firefox");
        App.startServer(Integer.parseInt(System.getProperty("testPort", "5313")));
        EntityManagerFactory.getInstance().setPersistenceUnit("app_test_PU");
        log.log(Level.INFO, "Starting Test on Port {0}", System.getProperty("testPort", "5312"));
    }

    public final void openUrl(String url) {
        open(url(url));
    }

    public LoginPage loginPage() {
        openUrl("");
        return new LoginPage();
    }

    public String url(String url){
        return "http://localhost:" + System.getProperty("testPort", "5313")+url;
    }
    public User randomDoctor() {
        User testUser = new User();
        testUser.setUserRole(UserRole.ROLE_DOCTOR);
        testUser.setUsername(RandomStringUtils.randomAlphabetic(10));
        testUser.setPassword(RandomStringUtils.randomAlphabetic(10));
        doctorService.createDoctor(testUser);
        return testUser;
    }

    @AfterClass
    public static final void destroyTest() {
        try {
            String jdbc = String.valueOf(EntityManagerFactory.getInstance().get().getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url"));
            DriverManager.getConnection(jdbc + ";shutdown=true");
        } catch (SQLException ex) {
            log.info("Database Shutdown");
        } finally {
            App.stopServer();
        }
    }

    public static void createTestUsers() {

    }
}
