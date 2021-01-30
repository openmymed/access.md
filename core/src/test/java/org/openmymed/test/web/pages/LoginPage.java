/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.web.pages;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

/**
 *
 * @author tareq
 */
public class LoginPage {

    private final SelenideElement usernameField;
    private final SelenideElement passwordField;
    private final SelenideElement submitButton;

    public LoginPage() {
        this.usernameField = $("#username");
        this.passwordField = $("#password");
        this.submitButton = $(".btn.btn-black");
    }
    
    public ApplicationPage login(String username,String password){
        this.usernameField.val(username);
        this.passwordField.val(password);
        this.submitButton.click();
        return new ApplicationPage();
    }
    
    public LoginPage then(){
        return this;
    }
    
    public LoginPage get(){
        return this;
    }
}
