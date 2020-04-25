/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from "redom";
import { goto } from "redom-app";
import {loadIcpc} from "../utils/icpc"
import logo from '../res/openmymed.png'
import {displayNotification} from "../utils/notifications"
export class Signin {
    constructor() {
        const _self = this;
        <div this="el">
            <div class="sidenav">
                <div class="login-main-text">
                    <h1>
                        ACCESS.MD
                    </h1>
                    <img src={logo}  ></img>
                </div>
            </div>
            <LoginForm this="form" />
        </div>;
    }
    update(data) {

    }
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
                            <label for="username">Username</label>
                            <input
                                this="username"
                                id="username"
                                type="text"
                                class="form-control"
                                placeholder="Username"
                                ></input>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input
                                this="password"
                                id="password"
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
        }).then((res) => {
            if (res.ok) {
                return res.json();
            } else {
                alert("Wrong username or password");
            }
        }).then((json) => {
            sessionStorage.setItem("role", json.userRole);
            let fullName = json.firstName + " " + json.lastName;
            if (json.userRole == "ROLE_DOCTOR") {
                sessionStorage.setItem("auth", true)
                loadIcpc();
                sessionStorage.setItem("name", fullName);
                goto("home");
                displayNotification("HELLO", {body: "THERE"});
            } else if (json.userRole == "ROLE_ADMIN") {
                sessionStorage.setItem("auth", true)
                sessionStorage.setItem("name", fullName);
                goto("admin");
            } else {
                sessionStorage.setItem("auth", false)
                alert("You do not have the authorization for this page");
            }
        });
    }
}


