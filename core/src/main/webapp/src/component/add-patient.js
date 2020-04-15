/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from 'redom';
import * as $ from "jquery"


export class AddPatient {
  constructor(attr, text) {
    <div this="el" class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Add Patient</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body container-fluid">
          <div class="col-12">
            <div class="form-group">
              <label for="code-field">Secret Code</label>
              <input class="form-control" id="code-field" type="text" this="code"></input>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button class="btn btn-primary" data-dismiss="modal" onclick={(e) => {this.addPatient()}}>Add</button>
        </div>
      </div>
    </div>
  </div>
  }

  show() {
    $(this.el).modal({backdrop:false})
  }

  addPatient() {
    fetch("/doctor/patient/add", {
      method: "POST",
      headers: {
       "Content-Type": "application/json",
      },
      body: JSON.stringify({
        code: this.code.value
      })
    }).then((res) => {
      if (res.ok){
      } else{
        throw new Error('Failed to Add Patient')
      }
    }).catch((error) => {alert(error)})
  }

  update(data) {

  }
}