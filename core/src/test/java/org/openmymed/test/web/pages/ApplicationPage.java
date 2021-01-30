/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.web.pages;

import org.openmymed.test.web.component.Sidebar;

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

    public ApplicationPage then() {
        return this;
    }
    
     public ApplicationPage get(){
        return this;
    }
}
