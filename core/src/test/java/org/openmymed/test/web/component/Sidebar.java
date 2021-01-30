/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.web.component;

import static com.codeborne.selenide.Selenide.$;

/**
 *
 * @author tareq
 */
public class Sidebar {

    public String activeLink() {
        return $(".sidebar-link.active").getAttribute("href");
    }
}
