/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import { MedicalProfile } from "../component/medical-profile";
import { Symptoms } from "../component/symptoms";
import { Answers } from "../component/answers";
import { el, text, mount } from "redom";
import { Page } from "../component/page";
export class PatientDetails {
  constructor(attr, text) {
    <Page this="el" class="mt-2">
      <h4 class="ml-4">Patient Profile</h4>
      <div class="row m-3 border bg-light">
        <MedicalProfile this="medicalProfile" />
        <div class="col-lg-8 bg-light">
          <div class="row">
            <Symptoms this="symptoms" />
            <Answers this="answers" />
          </div>
        </div>
        <label this="patientLabel"></label>
      </div>
    </Page>;
  }

  update(params) {
    this.medicalProfile.update(params[0]);
    this.symptoms.update(params[0]);
    this.answers.update(params[0]);
  }
}
