/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount , list} from "redom";
export class MedicalProfile {
constructor(attr, text) {
<div this="el" class="col-lg-4 p-0 border-right">
  <div class="d-flex row mt-4 pl-5">
    <img
      alt="patient-profile-img"
      class="profile-img"
      src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png"
      ></img>
    <div class="ml-2">
      <h5 this="name" class="mb-0"></h5>
      <h6 this="age" class="text-secondary"></h6>
    </div>
  </div>
  <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
    General Info
  </h5>
  <div class="row text-center p-3">
    <div class="col-4">
      <h6>Sex</h6>
      <h4 this="sex" class="text-info">
      </h4>
    </div>
    <div class="col-4">
      <h6>Height</h6>
      <div class="d-inline-flex text-info">
        <h4 this="height"></h4>
        <p class="ml-1">cm</p>
      </div>
    </div>
    <div class="col-4">
      <h6>Weight</h6>
      <div class="d-inline-flex text-info">
        <h4 this="weight"></h4>
        <p class="ml-1">kg</p>
      </div>
    </div>
  </div>
  <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
    Medical Flags
  </h5>
  <div class="col-12 py-1">
    <input type="checkbox" id="G6PD" this="g6pd" name="G6PD" value="G6PD Deficiency" ></input>
    <label class="ml-1" for="G6PD">
      G6PD Deficiency
    </label>
    <br></br>
    <input type="checkbox" id="respiratory" name="respiratory" value="Respiratory Diseases" this="respiratory"></input>
    <label class="ml-1" for="respiratory">
      Respiratory Diseases
    </label>
    <br ></br>
    <input type="checkbox" id="diabetes" name="diabetes" value="Diabetes" this="diabetes"></input>
    <label class="ml-1" for="diabetes">
      Diabetes
    </label>
    <br ></br>
    <input type="checkbox" id="cardio" name="cardio" value="Cardiovascular Diseases" this="cardiovascular"></input>
    <label class="ml-1" for="cardio">
      Cardiovascular Diseases
    </label>
    <br ></br>
    <input type="checkbox" id="obesity" name="obesity" value="Obesity" this="obese"></input>
    <label class="ml-1" for="obesity">
      Obesity
    </label>
    <br></br>
  </div>
  <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
    Current Medications
  </h5>
  <div class="p-1">
    {this.medications = list('ul', Medication)}
  </div>
</div>;
}

update(patientId) {
  this._getPatientMedicalProfile(patientId).then((json) => {
        this.medicalData = json
        this.age.textContent = json.age + " Years"
        this.height.textContent = json.height
        this.weight.textContent = json.weight
        if(json.sex == 'Male'){
          this.sex.textContent = "Male"
        }else{
          this.sex.textContent = "Female"
        }
        this._processFlags(json.medicalFlags);
        this.medications.update(json.medications);
      });
      
  this._getPatientProfile(patientId).then((json)=>{
    this.personalData = json;
    this.name.textContent = json.firstName +  " " + json.lastName 
  })    
  }
  _processFlags(medicalFlags){
      
  }
  
  
  _getPatientProfile(patientId){
    return  fetch("/doctor/patient/"+patientId, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => {
        if (res.ok) {
          return res.json();
        } else {
          alert("Wrong username or password");
        }
      })

  }
  
  _getPatientMedicalProfile(patientId){
   return  fetch("/doctor/patient/"+patientId+"/profile", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => {
        if (res.ok) {
          return res.json();
        } else {
          alert("Wrong username or password");
        }
      })
  }
}

class Medication{
constructor(){
<li this="el"></li>
}

update(medication){
this.li.textContent = data
}
}