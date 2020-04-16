/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from "redom";
export class MedicalProfile {
  constructor(attr, text) {
    <div this="el" class="col-lg-4 p-0 border-right">
      <div class="d-flex row mt-4 pl-5">
        <img
          alt="patient-profile-img"
          class="profile-img"
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png"
        />
        <div class="ml-2">
          <h5 id="full-name" class="mb-0">
            Sarah Ahmad
          </h5>
          <h6 class="text-secondary">25 years old</h6>
        </div>
      </div>
      <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
        General Info
      </h5>
      <div class="row text-center p-3">
        <div class="col-4">
          <h6>Sex</h6>
          <h4 id="sex-value" class="text-info">
            Female
          </h4>
        </div>
        <div class="col-4">
          <h6>Height</h6>
          <div class="d-inline-flex text-info">
            <h4 id="height-value">170</h4>
            <p class="ml-1">cm</p>
          </div>
        </div>
        <div class="col-4">
          <h6>Weight</h6>
          <div class="d-inline-flex text-info">
            <h4 id="weight-value">60</h4>
            <p class="ml-1">kg</p>
          </div>
        </div>
      </div>
      <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
        Medical Flags
      </h5>
      <div class="col-12 py-1">
        <input type="checkbox" id="G6PD" name="G6PD" value="G6PD Deficiency" />
        <label class="ml-1" for="G6PD">
          G6PD Deficiency
        </label>
        <br />
        <input
          type="checkbox"
          id="respiratory"
          name="respiratory"
          value="Respiratory Diseases"
        />
        <label class="ml-1" for="respiratory">
          Respiratory Diseases
        </label>
        <br />
        <input type="checkbox" id="diabetes" name="diabetes" value="Diabetes" />
        <label class="ml-1" for="diabetes">
          Diabetes
        </label>
        <br />
        <input
          type="checkbox"
          id="cardio"
          name="cardio"
          value="Cardiovascular Diseases"
        />
        <label class="ml-1" for="cardio">
          Cardiovascular Diseases
        </label>
        <br />
        <input type="checkbox" id="obesity" name="obesity" value="Obesity" />
        <label class="ml-1" for="obesity">
          Obesity
        </label>
        <br />
      </div>
      <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
        Current Medications
      </h5>
      <div class="p-1">
        <ul>
          <li>Kafgbvjiw</li>
          <li>Gwjkbgnw</li>
          <li>Wbnwjbn</li>
        </ul>
      </div>
    </div>;
  }

  update(data) {}
}
