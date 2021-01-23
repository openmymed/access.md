/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.pages;

import org.openmymed.test.components.Sidebar;

/**
 *
 * @author tareq
 */
public class ApplicationPage {

    private Sidebar sidebar;

    public ApplicationPage() {
        this.sidebar = new Sidebar();
    }

    public Sidebar sidebar() {
        return sidebar;
    }
}
