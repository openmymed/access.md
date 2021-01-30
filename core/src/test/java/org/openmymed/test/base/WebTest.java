/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.base;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import org.openmymed.test.web.pages.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.webdriver.FirefoxDriverFactory;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.auth.enums.UserRole;
import org.openmymed.accessmd.domain.core.service.DoctorService;
import org.openmymed.accessmd.infra.core.factory.DoctorServiceFactory;

/**
 *
 * @author tareq
 */
@Log
public abstract class WebTest extends AccessMdTest {

    @BeforeClass
    public static final void initTest() throws InterruptedException, Throwable {
        AccessMdTest.startServer();
        Configuration.browser = FirefoxDriverFactory.class.getName();
    }

    public final void openUrl(String url) {
        open(url(url));
    }

    public LoginPage openLoginPage() {
        openUrl("");
        return new LoginPage();
    }

    public String url(String url) {
        String formattedUrl = url.startsWith("/") ? url : "/" + url;
        return "http://localhost:" + System.getProperty("testPort", "5313") + formattedUrl;
    }

    @AfterClass
    public static final void destroyTest() {
        AccessMdTest.stopServer();
        closeWebDriver();
    }

}
