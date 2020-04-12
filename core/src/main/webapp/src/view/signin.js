/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from 'redom';
import {goto} from 'redom-app';
export class Signin {
    constructor() {
        const _self = this;
        <div this="el" >
            <h4 this="title">SIGNIN</h4>
            <LoginForm this="form"/>
        </div>

    }

    update(data) {
    }
}

class LoginForm {
    constructor() {
        <form this="el" onsubmit={(e) => {
                this.login(e)
                  }} >
            <input this="username" type="text"></input>
            <input this="password" type="text"></input>
            <input type="submit"></input>
        </form>
    }

    login(e) {
        e.preventDefault();
        fetch("/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: this.username.value,
                password: this.password.value
            }) 
        }).then(res => {
            if (res.ok) {
                return res.json()
            } else {
                alert("Wrong username or password")
            }
        }).then(json => {
            if (json.userRole == "ROLE_DOCTOR") {
                 window.auth = true;
                goto('home')
            } else if (json.userRole == "ROLE_ADMIN") {
                 window.auth = true;
                goto("admin")
            } else {
                 window.auth = false;
                alert("You do not have the authorization for this page")
            }
        });
    }

}