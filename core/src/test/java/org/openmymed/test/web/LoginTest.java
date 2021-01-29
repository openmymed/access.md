/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.web;

import org.openmymed.test.base.WebTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openmymed.accessmd.domain.auth.entity.User;

/**
 *
 * @author tareq
 */
public class LoginTest extends WebTest {

    @Test
    public void testDoctorLogin() {
        User doctor = randomDoctor();
        String activeLink = openLoginPage().then()
                .login(doctor.getUsername(), doctor.getPassword())
                .then()
                .get().sidebar().activeLink();
        assertEquals(url("/dashboard"), activeLink);
    }
}
