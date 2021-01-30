/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.android;

import org.junit.Test;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.test.base.AndroidTest;

/**
 *
 * @author tareq
 */
public class LoginTest extends AndroidTest{
    
    @Test
    public void doLogin(){
        User patient = randomPatient();
        openLoginActivity().then()
                .login(patient.getUsername(), patient.getPassword())
                .then().addSymptomsButton().exists();
    }
}
