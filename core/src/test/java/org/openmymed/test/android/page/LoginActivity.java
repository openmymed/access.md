/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.android.page;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

/**
 *
 * @author tareq
 */
public class LoginActivity {

    private SelenideElement loginField = $("#@id/et_username");

    private SelenideElement passwordField = $("#@id/et_password");

    private SelenideElement signinButton = $("#@id/btn_sign_in");

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
