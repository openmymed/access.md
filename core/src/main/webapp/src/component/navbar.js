/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from "redom";
export class NavBar {
  constructor(attr, text) {
    <nav this="el" class="navbar navbar-expand-md navbar-light">
      <button
        class="navbar-toggler mb-2 bg-light"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNav"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <div class="container-fluid">
          <div class="row">
            <TopNavBar></TopNavBar>
            <SideBar></SideBar>
          </div>
        </div>
      </div>
    </nav>;
  }

  update(data) {}
}

class TopNavBar {
  constructor() {
    <div
      this="el"
      class="col-xl-10 col-lg-9 col-md-8 ml-auto fixed-top py-2 top-navbar"
    >
      <div class="row align-items-center">
        <div class="col-md float-right">
          <div class="d-flex flex-row-reverse justify-content-center justify-content-md-start p-2 align-items-center">
            <h6 id="profile-name" class="align-middle ml-2">
              Majed Nuss
            </h6>
            <img
              id="profile-pic"
              class="profile-img"
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png"
              alt="profile-pic"
            />
          </div>
        </div>
      </div>
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
        href="#"
        class="navbar-brand text-white bg-blue-primary d-block mx-auto text-center py-4 mb-5"
      >
        Covid Dashboard
      </a>
      <div class="row">
        <ul class="navbar-nav flex-column text-left col-centered">
          <li class="nav-item">
            <a href="#" class="sidebar-link active nav-link p-3 mb-2">
              <i class="fa fa-home fa-lg mr-3"></i>Home
            </a>
          </li>
          <li class="nav-item">
            <a href="#" class="sidebar-link nav-link p-3 mb-2 ">
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
      <button type="button" class="btn btn-primary btn-md col-centered mt-5">
        <i class="fa fa-plus fa-lg mr-3"></i>
        Add Patient
      </button>
    </div>;
  }
}
