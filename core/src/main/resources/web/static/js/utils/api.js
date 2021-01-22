/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var headers = {
    "Content-Type": "application/json"
}

function api() {}
;
const process = (res) => {
    if (res.ok) {
        return res.json().catch(() => res.text());
    } else if (res.status === 401 || res.status === 403) {
        window.location.href = "/"
    } else {
        throw new Error(res.status);
    }
}

api.prototype.getPatientVitals = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/vitals", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    })
}


api.prototype.addDoctor = (doctor) => {
    return fetch("/api/admin/doctor", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(doctor)
    }).then((res) => {
        return process(res);
    })
}

api.prototype.assignPatientToMyself = (code) => {

    return fetch("/api/doctor/patient/add", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            code: code
        })
    }).then((res) => {
        return process(res);
    });
}

api.prototype.getPatientAnswers = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/answer", {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res)
    })

}


api.prototype.archiveAnswer = (patientId, answerId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/answer/" + answerId + "/seen", {
        method: "PUT",
        headers: headers,
    }).then((res) => {
        return process(res);
    })
}

api.prototype.archiveSymptom = (patientId, symptomId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/symptom/" + symptomId + "/seen", {
        method: "PUT",
        headers: headers,
    }).then((res) => {
        return process(res);
    })
}

api.prototype.getPatientMedicalProfile = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId, {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res);
    });

}

api.prototype.getPatientPersonalProfile = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId, {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res);
    });
}


api.prototype.getPatientsCount = () => {
    return fetch("/api/doctor/patient/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

api.prototype.getUnarchivedSymptomsCount = () => {
    return fetch("/api/doctor/patient/symptom/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

api.prototype.getUnarchivedAnswersCount = () => {
    return fetch("/api/doctor/patient/answer/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

api.prototype.getDoctorFeed = () => {
    return fetch("/api/doctor/feed", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

api.prototype.getPatientSymptoms = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/symptom", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

api.prototype.getPatients = () => {
    return fetch("/api/doctor/patient", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

api.prototype.getPatientQuestions = (patientId) => {
    return fetch("/api/doctor/patient/" + patientId + "/question", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

api.prototype.addQuestion = (patientId, question) => {
    return fetch("/api/doctor/patient/" + patientId + "/question", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(question)
    }).then((res) => {
        return process(res);
    });
}

api.prototype.login = (username, password) => {
    return fetch("/api/login", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            username: username,
            password: password
        }),
    }).then((res) => {
        return process(res);
    })
}

api.prototype.createQuestion = (patientId, question) => {
    return fetch(`/api/doctor/patient/${patientId}/question`, {
        method: "POST",
        headers: headers,
        body: JSON.stringify(question),
    }).then((res) => {
        return res;
    })
}

api.prototype.getPatientQuestion = (patientId, questionId) => {
    return fetch(`/api/doctor/patient/${patientId}/question/${questionId}`, {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res);
    })
}

api.prototype.updateQuestion = (patientId, questionId, question) => {
    return fetch(`/api/doctor/patient/${patientId}/question/${questionId}`, {
        method: "PUT",
        headers: headers,
        body: JSON.stringify(question),
    }).then((res) => {
        return res;
    })
}
window.apiService = new api();