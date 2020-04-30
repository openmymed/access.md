/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount, list } from "redom";
import { Page } from "../component/page";
import * as api from "../utils/api";
export class PatientList {
    constructor(attr, text) {
        <Page this="el">
            <h4 class="ml-4">Patient List</h4>
            <div class="col-12">
                <table class="table" this="table">
                    <thead>
                    <th scope="col" class="text-left">
                        Name
                    </th>
                    <th scope="col" class="text-center">
                        Number
                    </th>
                    <th scope="col" class="text-right">
                        Profile
                    </th>
                    </thead>
                    {(this.patients = list("tbody", PatientEntry))}
                </table>
            </div>
        </Page>;
    }
    update() {
        api.getPatients().then((json) => {
            this.patients.update(json);
        });
    }
}


class PatientEntry {
    constructor() {
        <tr this="el">
            <th scope="row" class="text-left" this="name"></th>
            <td this="number" class="text-center"></td>
            <td class="text-right">
                <a this="profile" class="btn btn-primary">
                    Profile
                </a>
            </td>
        </tr>;
    }

    update(data) {
        this.data = data;
        this.name.textContent = data.firstName + " " + data.lastName;
        this.number.textContent = data.telephoneNumber;
        this.profile.href = "#patient/" + this.data.id;
    }
}
