/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from "redom";
import * as $ from "jquery";
export class AddDoctor {
  constructor(attr, text) {
    <div
      this="el"
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              Add Doctor
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body text-left container-fluid">
            <div class="form-group container-fluid">
              <label for="username">Username</label>
              <input
                class="form-control"
                id="username"
                type="text"
                this="username"
              ></input>
            </div>
            <div class="form-group container-fluid">
              <label for="password">Password</label>
              <input
                class="form-control"
                id="password"
                type="password"
                this="password"
              ></input>
            </div>
            <div class="form-group container-fluid">
              <label for="firstName">First Name</label>
              <input
                class="form-control"
                id="firstName"
                type="text"
                this="firstName"
              ></input>
            </div>
            <div class="form-group container-fluid">
              <label for="lastName">Last Name</label>
              <input
                class="form-control"
                id="lastName"
                type="text"
                this="lastName"
              ></input>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              Close
            </button>
            <button
              class="btn btn-primary"
              data-dismiss="modal"
              onclick={(e) => {
                this.addDoctor();
              }}
            >
              Add
            </button>
          </div>
        </div>
      </div>
    </div>;
  }

  show() {
    $(this.el).modal({ backdrop: false });
  }

  addDoctor() {
    fetch("/admin/doctor", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: this.username.value,
        password: this.password.value,
        firstName: this.firstName.value,
        lastName: this.lastName.value,
      }),
    })
      .then((res) => {
        if (res.ok) {
          return res.json();
        } else {
          alert("Wrong username or password");
        }
      })
      .then((json) => {});
  }

  update(data) {}
}
