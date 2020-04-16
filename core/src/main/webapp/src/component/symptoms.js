/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount, list,unmount } from "redom";
import {getTitle} from "../utils/icpc"
export class Symptoms {
  constructor(attr, text) {
    <div this="el" class="col-lg-6">
      <div class="bg-light-grey mx-2 my-4">
        <h5 class="p-3 text-info font-weight-bold">Patient Symptoms</h5>
        <div class="col-12">
          {this.symptoms = list('div.container-fluid', Symptom) }
        </div>
      </div>
    </div>;
  }

  update(patientId) {
    this._getPatientSymptoms(patientId).then((data)=>{
      this.symptoms.update(data,{"patientId":patientId});
    })
    
  }
  
  
  _getPatientSymptoms(patientId){
    return  fetch("/doctor/patient/"+patientId+"/symptom", {
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

class Symptom {
  constructor(attr, text) {
    <div this="el" class="row">
      <div class="card card-common rounded-lg w-100">
        <div class="card-header d-flex justify-content-start">
          <h5 this="name"></h5>
        </div>
        <div class="card-body">
          <p this="note"></p>
          <p>
          <div class="d-flex justify-content-between w-100">
            <p this="date"></p>
            <button this="dismiss" class="btn btn-danger">Dismiss</button>
          </div>
          </p>
        </div>
      </div>
    </div>
  }

  update(data, index, items, context) {
    this.data = data;
    this.patientId = context.patientId;
    this.name.textContent = getTitle(data.symptomCode);
    this.note.textContent = data.note;
    this.date.textContent = data.creationDate;
    this.dismiss.onclick = (e) =>{
     this._dismiss().then((res)=>{
       unmount(this.el.parentNode,this)
     })
    }
  }
  
  _dismiss(){
     return  fetch("/doctor/patient/"+this.patientId+"/symptom/"+this.data.id+"/seen", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => {
        if (res.ok) {
          return "ok"
        } else {
          alert("Wrong username or password");
        }
      })
  }

}


