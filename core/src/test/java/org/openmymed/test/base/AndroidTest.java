/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.base;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;
import lombok.extern.java.Log;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openmymed.test.android.page.LoginActivity;

/**
 *
 * @author tareq
 */
@Log
public abstract class AndroidTest extends AccessMdTest {

    @BeforeClass
    public static void setUp() throws Throwable {
        AccessMdTest.startServer();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.browser = AndroidDriverProvider.class.getName();
       
    }
    
    public LoginActivity openLoginActivity(){
         open();
         return new LoginActivity();
    }

    @AfterClass
    public static void tearDown() {
        AccessMdTest.stopServer();
    }
}
