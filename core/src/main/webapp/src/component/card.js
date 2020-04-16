/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount } from "redom";
export class Card {
  constructor(attr, text) {
    let title = "";
    let number = "";
    <div this="el" class="col-lg-4 col-sm-12 p-2">
      <div class="card card-common rounded-lg">
        <div class="card-body">
          <div class="d-flex justify-content-center">
            <div class="flex-column">
              <h6 class="font-weight-bold">{title}</h6>
              <h1 class="pt-2 text-info font-weight-bold">{number}</h1>
            </div>
          </div>
        </div>
      </div>
    </div>;
  }

  update(data) {}
}
