/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.android.page;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 *
 * @author tareq
 */
public class MainActivity {
    
    private SelenideElement addSymptomsButton = $(By.id("me.kisoft.covid19:id/fab_add_symptoms"));
    
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
