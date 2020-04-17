/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import { el, text, mount, list,unmount} from 'redom';
import {getTitle} from "../utils/icpc"
export class PatientUpdates {
  constructor(attr, text) {
    <div this="el" class="container-fluid mt-3">
      <h4>Last Activities</h4>
      <div class="col-12">
        <table class="table" this="table">
          <thead>
          <th scope="col" class="text-left">Name</th>
          <th scope="col" class="text-center">Update Type</th>
          <th scope="col" class="text-center">Time</th>
          <th scope="col" class="text-center">Note</th>
          <th scope="col" class="text-right"></th>
          </thead>
          {this.updates = list('tbody', PatientUpdate)}
        </table>
      </div>
    </div>
  }

  update() {
    fetch("/doctor/feed", {
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
    }).then((json) => {
      this.updates.update(json);
    });
  }

}


class PatientUpdate {
  constructor() {
    <tr this="el">
      <th this="name" scope="row" class="text-left"> 
      </th>
      <td this="updateType"  class="text-center">
      </td>
      <td this="time"  class="text-center">
      </td>
      <td this="value"  class="text-center">
      </td>
      <td  class="text-right">
        <button this="dismissButton" class="btn btn-danger btn-sm">Dismiss</button>
        <a this="patient" class="btn btn-primary btn-sm">Profile</a>
      </td>
    </tr>
  }

  update(data) {
    this.data = data;
    this.name.textContent = data.patientName
    this.time.textContent = new Date(data.time).toLocaleDateString() + " @ " + new Date(data.time).toLocaleTimeString();
    if (data.type == "UNSEEN_SYMPTOM") {
      this.updateType.textContent = "Symptom"
      this.value.textContent = getTitle(data.value);
      this._dismiss = this._dismissSymptom;
    } else if (data.type == "UNSEEN_ANSWER") {
      this.updateType.textContent = "Answer"
      this.value.textContent = data.value
      this._dismiss =  _dismissAnswer;
    }
    this.dismissButton.onclick = (e) => {
      this._dismiss().then(()=>{
        unmount(this.el.parentNode,this)
      });
     
    }
    this.patient.href = "#patient/" + data.patientId
  }
  
  _dismissSymptom() {
    return  fetch("/doctor/patient/" + this.data.patientId + "/symptom/" + this.data.entityId + "/seen", {
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
  
  _dismissAnswer() {
    return  fetch("/doctor/patient/" + this.data.patientId + "/answer/" + this.data.entityId + "/seen", {
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