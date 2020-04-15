/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import { el, text, mount, list} from 'redom';
export class PatientUpdates {
  constructor(attr, text) {
    <div this="el" class="container-fluid mt-3">
      <h4>Last Activities</h4>
      <div class="col-12">
        <table class="table" this="table">
          <thead>
          <th scope="col" class="text-left">Name</th>
          <th scope="col" class="text-center">Update Type</th>
          <th scope="col" class="text-center">Time</th>
          <th scope="col" class="text-center">Note</th>
          <th scope="col" class="text-right"></th>
          </thead>
          {this.updates = list('tbody', PatientUpdate)}
        </table>
      </div>
    </div>
  }

  update() {

  }

 
}


class PatientUpdate {
  constructor() {
    <tr this="el">
      <th this="name" scope="row" class="text-left"> 
      </th>
      <td this="updateType"  class="text-center">
      </td>
      <td this="time"  class="text-center">
      </td>
      <td this="note"  class="text-center">
      </td>
      <td  class="text-right">
        <button this="dismiss" class="btn btn-danger btn-sm">Dismiss</button>
        <a this="patient" class="btn btn-primary btn-sm">Profile</a>
      </td>
    </tr>
  }

  update(data) {
    this.data = data;
    this.name.textContent = data.name
    this.updateType.textContent = data.updateType
    this.time.textContent = data.time;
    this.note.textContent = data.note;
    this.dissmiss.onclick = (e) => {
      console.log(e, data);
    }
    this.patient.href = "#patient/" + data.id
  }

}