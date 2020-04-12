/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {Overview} from "../component/overview"
import {PatientUpdates} from  "../component/patient-updates";
import { el, text, mount } from 'redom';
export class Home {
    constructor(attr, text) {
        <div this="el">
        <h4>HOME</h4>
            <Overview/>
            <PatientUpdates/>
        </div>
    }
    
    update(data) {

    }
}