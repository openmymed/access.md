/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.android.page;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 *
 * @author tareq
 */
public class LoginActivity {

    @AndroidFindBy(xpath = "//*[contains(@id, 'et_username')]")
    private SelenideElement loginField;
    
    @AndroidFindBy(xpath= "//*[contains(@id, 'tv_password')]")
    private SelenideElement passwordField;
    
    @AndroidFindBy(xpath="//*[contains(@id, 'btn_sign_in')]")
    private SelenideElement signinButton;

    public MainActivity login(String username, String password) {
        loginField.val(username);
        passwordField.val(password);
        signinButton.click();
        return new MainActivity();
    }
    
    public LoginActivity then(){
        return this;
    }
    
    public LoginActivity get(){
        return this;
    }
}
