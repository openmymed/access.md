/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.base;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openmymed.accessmd.app.App;
import org.openmymed.accessmd.infra.factory.EntityManagerFactory;

/**
 *
 * @author tareq
 */
@Log
public abstract class AndroidTest {

    @BeforeClass
    public void setUp() throws Throwable {
        System.setProperty("selenide.browser", "Firefox");
        App.startServer(Integer.parseInt(System.getProperty("testPort", "5313")));
        EntityManagerFactory.getInstance().setPersistenceUnit("app_test_PU");
        log.log(Level.INFO, "Starting Test on Port {0}", System.getProperty("testPort", "5312"));
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.browser = AndroidDriverProvider.class.getName();
        open();
    }

    @AfterClass
    public void tearDown() {
        try {
            String jdbc = String.valueOf(EntityManagerFactory.getInstance().get().getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url"));
            DriverManager.getConnection(jdbc + ";shutdown=true");
        } catch (SQLException ex) {
            log.info("Database Shutdown");
        } finally {
            App.stopServer();
            closeWebDriver();
        }
    }
}
