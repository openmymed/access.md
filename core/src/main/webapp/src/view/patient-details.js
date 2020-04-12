/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {MedicalProfile} from "../component/medical-profile"
import {Symptoms} from "../component/symptoms"
import {Answers} from "../component/answers"
import { el, text, mount } from 'redom';

export class PatientDetails {
    constructor(attr, text) {
        <div this="el">
        <h4>PATIENT DETAILS</h4>
            <MedicalProfile/>
            <Symptoms/>
            <Answers/>
        </div>
    }

    update(data) {

    }
}