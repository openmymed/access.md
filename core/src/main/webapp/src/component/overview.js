/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from "redom";
import { Card } from "../component/card";
export class Overview {
  constructor(attr, text) {
    <div this="el">
      <section class="mt-4 ml-5 border-top">
        <div class="container-fluid mt-5">
          <div class="row">
            <div class="col-sm-12 col-xl-11 col-lg-9 col-md-8">
              <h4>Overview</h4>
              <div class="row pt-md-2">
                <Card></Card>
                <Card></Card>
                <Card></Card>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section class="m-5">
        <div class="container-fluid">
          <h4>Last Activities</h4>
          <div class="panel panel-default">
            <table class="table table-striped">
              <thead class="bg-blue-primary">
                <tr class="text-white">
                  <th>Patient code</th>
                  <th>Name</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>John</td>
                  <td>Doe</td>
                  <td>john@example.com</td>
                  <td>john@examsfm</td>
                  <td>
                    <button class="btn btn-primary btn-sm px-3">Details</button>
                  </td>
                </tr>
                <tr>
                  <td>Mary</td>
                  <td>Moe</td>
                  <td>mary@example.com</td>
                  <td>john@examsfm</td>
                  <td>
                    <button class="btn btn-primary btn-sm px-3">Details</button>
                  </td>
                </tr>
                <tr>
                  <td>July</td>
                  <td>Dooley</td>
                  <td>july@example.com</td>
                  <td>john@examsfm</td>
                  <td>
                    <button class="btn btn-primary btn-sm px-3">Details</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </section>
    </div>;
  }

  update(data) {}
}
