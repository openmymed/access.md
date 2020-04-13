/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import { Overview } from "../component/overview";
import { PatientUpdates } from "../component/patient-updates";
import { el, text, mount } from "redom";
import { NavBar } from "../component/navbar";
import {Page} from "../component/page"
export class Home {
    constructor(attr, text) {
        <Page this="el">
            <Overview this="overview"></Overview>
            <PatientUpdates this="patientUpdates"></PatientUpdates>
        </Page>
    }
    update(params) {
        console.log(this.patientUpdates, this.overview)
        this.patientUpdates.update(params)
        this.overview.update(params)
    }
}
