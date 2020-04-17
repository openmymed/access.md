/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import { el, text, mount, list, unmount} from "redom";
import { Page } from "../component/page";

export class PatientQuestions {
  constructor(attr, text) {
    <Page this="el">
      <div class="col-12 d-flex justify-content-between">
        <h4 class="ml-4">Questions</h4>
        <button this="addQuestionButton" class="btn btn-primary btn-sm">
          <i class="fa fa-plus"></i>
        </button>
      </div>
      <div class="col-12">
        <table class="table" this="table">
          <thead>
          <th scope="col" class="text-left">
            Question
          </th>
          <th scope="col" class="text-center">
            Type
          </th>
          <th scope="col" class="text-center">
            Repetition
          </th>
          <th scope="col" class="text-right">
    
          </th>
          </thead>
          {(this.questions = list("tbody", PatientQuestion))}
        </table>
      </div>
    </Page>;
    this.addQuestionButton.onclick = (e) => {
      this._addQuestion()
    }
  }

  _addQuestion() {
    this.editModal = new AddEditQuestionModal();
    mount(this, this.editModal)
    this.editModal.show(undefined, this.patientId);
    $(this.editModal.el).on('hidden.bs.modal', (e) => {
      this.update(this.patientId)
    })
  }

  update(params) {
    this.patientId = params[0]
    this._loadQuestions()
  }

  _loadQuestions() {
    fetch("/doctor/patient/" + this.patientId + "/question", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      }}).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        alert('error getting questions')
      }
    }).then((json) => {
      this.questions.update(json)
    })
  }
}



class PatientQuestion {
  constructor() {
    <tr this="el">
      <th scope="row" class="text-left" this="question"></th>
      <td this="type" class="text-center"></td>
      <td this="repetition" class="text-center"></td>
      <td class="text-right">
        <button this="editButton" class="btn btn-primary">
          <i class="fa fa-pencil"></i>
        </button>
      </td>
    </tr>;

    this.editButton.onclick = (e) => {
      this.edit();
    }
  }

  edit() {
    this.editModal = new AddEditQuestionModal();
    mount(this, this.editModal)
    this.editModal.show(this.data);

  }

  update(data) {
    this.data = data;
    this.question.textContent = data.question
    this.type = data.type
    if (data.recurring == true) {
      this.repetition.textContent = data.recurrance.length  + " Times";
    } else {
      this.repetition.textContent = "None";
    }
  }
}

class AddEditQuestionModal {
  constructor() {
    <div this="el" class="modal fade" id="addEditQuestionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">  
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Add/Edit Question</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body container-fluid">
            <div class="row">
              <div class="col-12 form-group">
                <label for="question-text">Question</label>
                <input class="form-control" id="question-text" type="text" this="questionText"></input>
              </div>
            </div>
            <div class="row">
              <div class="col-6 form-group">
                <label for="question-type">Question Type</label>
                <select this="questionType" id="question-type" class="form-control">
                  <option value="" selected="true">Please Select</option>
                  <option value="Scale" >Scale</option>
                  <option value="Binary" >Binary</option>
                  <option value="Text" >Text</option>
                </select>
    
              </div>
              <div class="col-6 form-group" >
                <label></label>
                <div class="d-flex justify-content-between mt-3">
                  <label class="checkbox-inline" for="question-type">Recurring?</label>
                  <input type="checkbox" this="recurring"  id="recurring"></input>
                </div>
              </div>
            </div>
            <div this="recurrenceContainer" class="row hidden-recurrence">
              <div class="col-6 form-group">
                <label for="from-date-input">From Date</label>
                <input this="fromDate" id="from-date-input" type="text" class="form-control"/>
              </div>
              <div class="col-6 form-group">
                <label for="to-date-input">To Date</label>
                <input this="toDate" id="to-date-input" type="text" class="form-control"/>
              </div>
              <div class="col-12">
                <table class="table" this="table">
                  <thead>
                  <th scope="col" class="text-left">
                    #
                  </th>
                  <th scope="col" class="text-center">
                    Recurrence Time
                  </th>
                  <th scope="col" class="text-right">
                  </th>
                  </thead>
                  {(this.repetitions = list("tbody", Repetition))}
                </table>
              </div>
              <div class="col-12 d-flex justify-content-end">
                <button this="addRecurrenceButton" class="btn bt-primary btn-sm">
                  <i class="fa fa-plus"></i>
                </button>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button class="btn btn-primary" this="saveButton" >Save</button>
          </div>
        </div>
      </div>
    </div>

    this.data = {};
    this.data.recurrance = []
    this.saveButton.onclick = (e) => {
      this.saveQuestion();
    }
    this.repetitions.el.addEventListener("repetitionRemoved", (e) => {
      this.repetitions.update(e.detail.newList)
    })
    this.addRecurrenceButton.onclick = (e) => {

      this.data.recurrance.push({hourOfDay: 0, minuteOfHour: 0})
      this.repetitions.update(this.data.recurrance)
    }
    this.recurring.onchange = (e) => {
      if (e.target.checked) {
        this.recurrenceContainer.classList.remove('hidden-recurrence')
      } else {
        this.recurrenceContainer.classList.add('hidden-recurrence')
      }

    }
  }

  saveQuestion() {
    fetch("/doctor/patient/" + this.patientId + "/question", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        question: this.questionText.value,
        type: this.questionType.value,
        recurring: this.recurring.checked,
        recurrance: this.data.recurrance,
        startDate: Date.parse(this.fromDate.value),
        endDate: Date.parse(this.toDate.value)
      })}).then((res) => {
      this.hide()
    });
  }
  update(data) {
    if (data) {

      this.fromDate.value = new Date(data.startDate).toLocaleDateString();
      this.toDate.value = new Date(data.endDate).toLocaleDateString()
      this.questionText.value = data.question;
      this.questionType.value = data.type;
      if (data.recurrance == true) {
        this.data.recurrance = data.recurrance;
      }
      this.recurring.checked = data.recurring;
      this.recurring.dispatchEvent(new Event("change"));
    }
  }

  show(data, patientId) {
    this.patientId = patientId
    $(this.el).modal({backdrop: false})
    $(this.fromDate).datepicker();
    $(this.toDate).datepicker();
    $.datepicker.regional['en'];
    $(this.el).on('hidden.bs.modal', (e) => {
      unmount(this.el.parentNode, this)
    })
    this.update(data)
  }

  hide() {
    $(this.el).modal('hide')
  }

}

class Repetition {
  constructor() {
    <tr this="el">
      <th scope="row" class="text-left" this="indexText"></th>
      <td  class="text-center">
        <input this="timeInput" type="time" class="repetition-time" ></input>
      </td>
      <td class="text-right">
        <a this="delete" >
          <i class="fa fa-times"></i>
        </a>
      </td>
    </tr>;
    this.delete.onclick = (e) => {
      this._delete();
    }

    this.timeInput.oninput = (e) => {
      if (e.target.value) {
        let splits = e.target.value.split(":")
        this.data.hourOfDay = parseInt(splits[0])
        this.data.minuteOfHour = parseInt(splits[1])
      }
    }
  }

  _delete() {
    this.items.splice(this.index, 1)
    this.el.dispatchEvent(new CustomEvent("repetitionRemoved", {
      detail: {newList: this.items}, bubbles: true}))
  }

  update(data, index, items) {
    this.data = data;
    this.index = index;
    this.items = items;
    this.indexText.textContent = index + 1;
    this.timeInput.value = this.data.hourOfDay + ":" + this.data.minuteOfHour
  }
}





