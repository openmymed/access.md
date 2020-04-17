/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount, list, unmount } from "redom";
import {getTitle} from "../utils/icpc"
export class Answers {
  constructor(attr, text) {
    <div this="el" class="col-lg-6">
      <div class="bg-light-grey mx-2 my-4 ">
        <div class="col-12 d-flex justify-content-between">
          <h5 class="p-3 text-info font-weight-bold">Patient Answers</h5>
          <a this="questionsButton" class="btn btn-primary">Questions</a>
        </div>
        <div class="col-12">
          {this.answers = list('div.container-fluid', Answer) }
        </div>
      </div>
    </div>;
  }

  update(patientId) {
    this._getPatientAnswers(patientId).then((data) => {
      this.answers.update(data, {"patientId": patientId});
    })
    this.questionsButton.href="#ask/"+patientId

  }

  _getPatientAnswers(patientId) {
    return  fetch("/doctor/patient/" + patientId + "/answer", {
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

class Answer {
  constructor(attr, text) {
    <div this="el" class="row">
      <div class="card card-common rounded-lg w-100">
        <div class="card-header d-flex justify-content-start">
          <h6 this="name"></h6>
        </div>
        <div class="card-body w-100">
          <div class="answer-note" this="note"></div>
          <p>
          <div class="d-flex justify-content-between w-100">
            <div>
              <div this="date"></div>
              <div class="d-flex">
                <i class="fa fa-clock"></i>&nbsp;
                <div this="time" ></div>
              </div>
            </div>
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
    this.name.textContent = getTitle(data.answerCode);
    this.note.textContent = data.note;
    this.date.textContent = new Date(data.creationDate).toLocaleDateString()
    this.time.textContent = new Date(data.creationDate).toLocaleTimeString()
    this.dismiss.onclick = (e) => {
      this._dismiss().then((res) => {
        unmount(this.el.parentNode, this)
      })
    }
  }

  _dismiss() {
    return  fetch("/doctor/patient/" + this.patientId + "/answer/" + this.data.id + "/seen", {
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