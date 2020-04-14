/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import { el, text, mount } from "redom";
import {NavBar} from "./navbar";
import {AdminNavBar} from "./navbar";
export class Page {
  constructor(args) {
    <div this="el" class="row">
      <NavBar></NavBar>
      <div class="col-xl-10 col-sm-12 col-md-8 col-lg-9">
        {arguments}
      </div>
    </div>
  }
  update() {
  }
}

export class AdminPage {
  constructor(args) {
    <div this="el" class="row">
      <AdminNavBar></AdminNavBar>
      <div class="col-xl-10 col-sm-12 col-md-8 col-lg-9">
        {arguments}
      </div>
    </div>
  }
}