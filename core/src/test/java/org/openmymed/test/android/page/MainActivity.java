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
public class MainActivity {
    
    @AndroidFindBy(id="fab_add_symptoms")
    private SelenideElement addSymptomsButton;
    
    public SelenideElement addSymptomsButton(){
        return addSymptomsButton;
    }
    
    public MainActivity then(){
        return this;
    }
    
    public MainActivity get(){
        return this;
    }
}
