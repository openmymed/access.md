/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount, setChildren } from "redom";
import { AddPatient } from "./add-patient";
import { AddDoctor } from "./add-doctor";
export class NavBar {
  constructor(attr, text) {
    <div this="el" class="col-12">
      <TopNavBar></TopNavBar>
      <SideBar></SideBar>
    </div>;
  }

  update(data) {}
}

class TopNavBar {
  constructor() {
    let name = sessionStorage.getItem("name");
    <nav this="el" class="navbar navbar-expand-lg navbar-light bg-transparent">
      <div class="d-flex col-12 flex-row-reverse justify-content-center justify-content-md-start p-2 align-items-center">
        <h6 id="profile-name" class="align-middle ml-2">
          {name}
        </h6>
        <img
          id="profile-pic"
          class="profile-img"
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png"
          alt="profile-pic"
        />
      </div>
    </nav>;
  }
}

export class AdminNavBar {
  constructor() {
    <div this="el" class="col-12">
      <TopNavBar></TopNavBar>
      <AdminSideBar></AdminSideBar>
    </div>;
  }
}

export class AdminSideBar {
  constructor() {
    <div
      this="el"
      class="col-xl-2 col-lg-3 col-md-4 sidebar fixed-top text-center"
    >
      <a
        href="#admin"
        class="navbar-brand text-white bg-blue-primary d-block mx-auto text-center py-4 mb-5"
      >
        Admin Dashboard
      </a>
      <div class="row">
        <ul class="navbar-nav flex-column text-left col-centered">
          <li class="nav-item">
            <a href="#admin" class="sidebar-link active nav-link p-3 mb-2">
              <i class="fa fa-home fa-lg mr-3"></i>Home
            </a>
          </li>
          <li class="nav-item">
            <a href="#doctors" class="sidebar-link nav-link p-3 mb-2 ">
              <i class="fa fa-user fa-lg mr-3"></i>Doctor List
            </a>
          </li>
        </ul>
      </div>
      <button
        type="button"
        class="btn btn-primary btn-md col-centered mt-5"
        onclick={(e) => {
          this.addDoctor.show();
        }}
      >
        <i class="fa fa-plus fa-lg mr-3"></i>
        Add Doctor
      </button>
      <AddDoctor this="addDoctor"></AddDoctor>
    </div>;
  }
}
class SideBar {
  constructor() {
    <div
      this="el"
      class="col-xl-2 col-lg-3 col-md-4 sidebar fixed-top text-center"
    >
      <a
        href="#home"
        class="navbar-brand text-white bg-blue-primary d-block mx-auto text-center py-4 mb-5"
      >
        ACCESS.MD
      </a>
      <div class="row">
        <ul class="navbar-nav flex-column text-left col-centered">
          <li class="nav-item">
            <a href="#home" class="sidebar-link active nav-link p-3 mb-2">
              <i class="fa fa-home fa-lg mr-3"></i>Home
            </a>
          </li>
          <li class="nav-item">
            <a href="#patients" class="sidebar-link nav-link p-3 mb-2 ">
              <i class="fa fa-user fa-lg mr-3"></i>Patient List
            </a>
          </li>
          <li class="nav-item">
            <a href="#" class="sidebar-link nav-link p-3 mb-2 ">
              <i class="fa fa-calendar-alt fa-lg mr-3"></i>Questions
            </a>
          </li>
          <li class="nav-item">
            <a href="#" class="sidebar-link nav-link p-3 mb-2 ">
              <i class="fa fa-cog fa-lg mr-3"></i>Settings
            </a>
          </li>
        </ul>
      </div>
      <button
        type="button"
        class="btn btn-primary btn-md col-centered mt-5"
        onclick={(e) => {
          this.addPatient.show();
        }}
      >
        <i class="fa fa-plus fa-lg mr-3"></i>
        Add Patient
      </button>
      <AddPatient this="addPatient"></AddPatient>
    </div>;
  }
}
