/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.android.page;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

/**
 *
 * @author tareq
 */
public class LoginActivity {
    
    private SelenideElement loginField = $(By.xpath("//*[contains(@id, 'et_username')]"));
    
    private SelenideElement passwordField = $(By.xpath("//*[contains(@id, 'tv_password')]"));
    
    private SelenideElement signinButton = $(By.xpath("//*[contains(@id, 'btn_sign_in')]"));
    
    public MainActivity login(String username, String password) {
        loginField.val(username);
        passwordField.val(password);
        signinButton.click();
        return new MainActivity();
    }
    
    public LoginActivity then() {
        return this;
    }
    
    public LoginActivity get() {
        return this;
    }
}
