/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {MedicalProfile} from "../component/medical-profile"
import {Symptoms} from "../component/symptoms"
import {Answers} from "../component/answers"
import { el, text, mount } from 'redom';
import {Page} from "../component/page"
export class PatientDetails {
    constructor(attr, text) {
        <Page this="el">           
            <h4>PATIENT DETAILS</h4>
            <MedicalProfile this="medicalProfile"/>
            <Symptoms this="symptoms"/>
            <Answers this="answers"/>
             <label this="patientLabel"></label>
        </Page>
    }

    update(params) {
        this.patientLabel.innerHTML = "patient" + params[0]
        alert("YOU WENT TO PATIENT DIALOG WITH PARAMS " + params)
        
        this.medicalProfile.update(params[0])
        this.symptoms.update(params[0])
        this.answers.update(params[0])
    }
}