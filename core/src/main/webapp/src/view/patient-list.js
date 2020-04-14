/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount, list} from 'redom';
import {Page} from '../component/page';
export class PatientList {
    constructor(attr, text) {
        <Page this="el">
            {this.patients = list('div.container', PatientEntry)}
        </Page>

    }

    update(data) {
        fetch("/doctor/patient", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        }).then((res) => {
            if (res.ok) {
                return res.json();
            } else {
                alert("Wrong username or password");
            }
        }).then((json) => {
            this.patients(json)
        });

    }
}

class PatientEntry {
    constructor() {
        <div this="el" class="row">
            <div class="col-6">
                <label this="name"></label>
            </div>
            <div class="col-4">
                <label this="number"></label>
            </div>
            <div class="col-2">
                <a this="profile" class="btn btn-primary">Profile</a>
            </div>
        </div>
    }

    update(data) {
        this.data = data
        this.name = data.name
        this.number = data.number
        this.profile.href = "#patient/" + this.data.id
    }

}