/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from "redom";
import { goto } from "redom-app";
export class Signin {
  constructor() {
    const _self = this;
    <div this="el">
      <div class="sidenav">
        <div class="login-main-text">
          <h2>
            Covid19
            <br /> Login Page
          </h2>
          <p>Login from here to access.</p>
        </div>
      </div>
      <LoginForm this="form" />
    </div>;
  }

  update(data) {}
}

class LoginForm {
  constructor() {
    <div this="el" class="main">
      <div class="col-md-6 col-sm-12">
        <div class="login-form">
          <form
            onsubmit={(e) => {
              this.login(e);
            }}
          >
            <div class="form-group">
              <label>Username</label>
              <input
                this="username"
                type="text"
                class="form-control"
                placeholder="Username"
              ></input>
            </div>
            <div class="form-group">
              <label>Password</label>
              <input
                this="password"
                type="password"
                class="form-control"
                placeholder="Password"
              ></input>
            </div>
            <button type="submit" class="btn btn-black">
              Login
            </button>
          </form>
        </div>
      </div>
    </div>;
  }

  login(e) {
    e.preventDefault();
    fetch("/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: this.username.value,
        password: this.password.value,
      }),
    })
      .then((res) => {
        if (res.ok) {
          return res.json();
        } else {
          alert("Wrong username or password");
        }
      })
      .then((json) => {
        if (json.userRole == "ROLE_PATIENT") {
          //TODO: change this TO ROLE_DOCTOR.
          window.auth = true;
          goto("home");
        } else if (json.userRole == "ROLE_ADMIN") {
          window.auth = true;
          goto("admin");
        } else {
          window.auth = false;
          alert("You do not have the authorization for this page");
        }
      });
  }
}
