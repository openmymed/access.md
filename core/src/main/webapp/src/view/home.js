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
    <div class="container-fluid display-table">
      <div class="row display-table-row">
        <div
          class="col-md-2 col-sm-1 hidden-xs display-table-cell v-align box"
          id="navigation"
        >
          <div class="logo">
            <a hef="home.html">Covid Dashboard</a>
          </div>
          <div class="navi">
            <ul>
              <li class="active">
                <a href="#">
                  <i class="fa fa-home" aria-hidden="true"></i>
                  <span class="hidden-xs hidden-sm">Home</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fa fa-tasks" aria-hidden="true"></i>
                  <span class="hidden-xs hidden-sm">Workflow</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fa fa-bar-chart" aria-hidden="true"></i>
                  <span class="hidden-xs hidden-sm">Statistics</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fa fa-user" aria-hidden="true"></i>
                  <span class="hidden-xs hidden-sm">Calender</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fa fa-calendar" aria-hidden="true"></i>
                  <span class="hidden-xs hidden-sm">Users</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fa fa-cog" aria-hidden="true"></i>
                  <span class="hidden-xs hidden-sm">Setting</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-md-10 col-sm-11 display-table-cell v-align">
          {/* <!--<button type="button" class="slide-toggle">Slide Toggle</button> --> */}
          <div class="row">
            <header>
              <div class="col-md-7">
                <nav class="navbar-default pull-left">
                  <div class="navbar-header">
                    <button
                      type="button"
                      class="navbar-toggle collapsed"
                      data-toggle="offcanvas"
                      data-target="#side-menu"
                      aria-expanded="false"
                    >
                      <span class="sr-only">Toggle navigation</span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                  </div>
                </nav>
                <div class="search hidden-xs hidden-sm">
                  <input type="text" placeholder="Search" id="search"></input>
                </div>
              </div>
              <div class="col-md-5">
                <div class="header-rightside">
                  <ul class="list-inline header-top pull-right">
                    <li class="hidden-xs">
                      <a
                        href="#"
                        class="add-project"
                        data-toggle="modal"
                        data-target="#add_project"
                      >
                        Add Project
                      </a>
                    </li>
                    <li>
                      <a href="#">
                        <i class="fa fa-envelope" aria-hidden="true"></i>
                      </a>
                    </li>
                    <li>
                      <a href="#" class="icon-info">
                        <i class="fa fa-bell" aria-hidden="true"></i>
                        <span class="label label-primary">3</span>
                      </a>
                    </li>
                    <li class="dropdown">
                      <a
                        href="#"
                        class="dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        <img src="" alt="user" />
                        <b class="caret"></b>
                      </a>
                      <ul class="dropdown-menu">
                        <li>
                          <div class="navbar-content">
                            <span>JS Krishna</span>
                            <p class="text-muted small">email goes here</p>
                            <div class="divider"></div>
                            <a href="#" class="view btn-sm active">
                              View Profile
                            </a>
                          </div>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </header>
          </div>
          <div class="user-dashboard">
            <h1>Hello, JS</h1>
            <div class="row">
              <div class="col-md-5 col-sm-5 col-xs-12 gutter">
                <div class="sales">
                  <h2>Your Sale</h2>

                  <div class="btn-group">
                    <button
                      class="btn btn-secondary btn-lg dropdown-toggle"
                      type="button"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      <span>Period:</span> Last Year
                    </button>
                    <div class="dropdown-menu">
                      <a href="#">2012</a>
                      <a href="#">2014</a>
                      <a href="#">2015</a>
                      <a href="#">2016</a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-7 col-sm-7 col-xs-12 gutter">
                <div class="sales report">
                  <h2>Report</h2>
                  <div class="btn-group">
                    <button
                      class="btn btn-secondary btn-lg dropdown-toggle"
                      type="button"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      <span>Period:</span> Last Year
                    </button>
                    <div class="dropdown-menu">
                      <a href="#">2012</a>
                      <a href="#">2014</a>
                      <a href="#">2015</a>
                      <a href="#">2016</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>;

    // <div this="el">
    // <h4>HOME</h4>
    //     <Overview/>
    //     <PatientUpdates/>
    // </div>
  }

  update(data) {}
}
