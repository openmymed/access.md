/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import { Overview } from "../component/overview";
import { PatientUpdates } from "../component/patient-updates";
import { el, text, mount } from "redom";
export class Home {
  constructor(attr, text) {
    <nav this="el" class="navbar navbar-expand-md navbar-light">
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="myNavBar"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="myNavBar">
        <div class="container-fluid">
          <div class="row">
            <SideBar></SideBar>
            <NavBar></NavBar>
          </div>
        </div>
      </div>
    </nav>;
  }

  update(data) {}
}

class NavBar {
  constructor() {
    <div this="el" class="col-lg-9"></div>;
  }
}

class SideBar {
  constructor() {
    <div this="el" class="col-lg-3 sidebar fixed-bottom">
      <a
        href="#"
        class="navbar-brand text-white d-block mx-auto text-center py-3"
      >
        Covid Dashboard
      </a>
      <ul class="navbar-nav flex-column">
        <li class="nav-item">
          <a href="#" class="sidebar-link nav-link p-3 mb-2">
            <i class="fa fa-home fa-lg mr-3"></i>Home
          </a>
        </li>
        <li class="nav-item">
          <a href="#" class="sidebar-link nav-link p-3 mb-2">
            <i class="fa fa-user fa-lg mr-3"></i>Patient List
          </a>
        </li>
        <li class="nav-item">
          <a href="#" class="sidebar-link nav-link p-3 mb-2">
            <i class="fa fa-calendar-alt fa-lg mr-3"></i>Questions
          </a>
        </li>
        <li class="nav-item">
          <a href="#" class="sidebar-link nav-link p-3 mb-2">
            <i class="fa fa-cog fa-lg mr-3"></i>Settings
          </a>
        </li>
      </ul>
      <button type="button" class="btn btn-primary ml-5 btn-lg">
        <i class="fa fa-plus fa-lg mr-3"></i>
        Add Patient
      </button>
    </div>;
  }
}
