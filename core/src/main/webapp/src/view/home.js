/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import { Overview } from "../component/overview";
import { PatientUpdates } from "../component/patient-updates";
import { el, text, mount } from "redom";
import { NavBar } from "../component/navbar";
export class Home {
  constructor(attr, text) {
    <div this="el" class="row">
      <div class="col-xl-2 col-lg-3 col-md-4">
        <NavBar></NavBar>
      </div>
      <div class="col-xl-10 col-sm-12 col-md-8 col-lg-9 mt-5">
        <Overview></Overview>
      </div>
    </div>;
  }
  update(data) {}
}
