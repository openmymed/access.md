/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import { el, text, mount } from 'redom';
export class PatientUpdates {
constructor(attr, text) {
<div this="el" class="container-fluid mt-3">
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
                        <a class="btn btn-primary btn-sm px-3"  href="#patient/3">Details</a>
                    </td>
                </tr>
                <tr>
                    <td>Mary</td>
                    <td>Moe</td>
                    <td>mary@example.com</td>
                    <td>john@examsfm</td>
                    <td>
                        <a class="btn btn-primary btn-sm px-3"  href="#patient/2">Details</a>
                    </td>
                </tr>
                <tr>
                    <td>July</td>
                    <td>Dooley</td>
                    <td>july@example.com</td>
                    <td>john@examsfm</td>
                    <td>
                    <a class="btn btn-primary btn-sm px-3" href="#patient/1">Details</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
}

update(data) {

}
}