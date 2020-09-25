/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var headers = {
    "Content-Type": "application/json"
}

window.api = {};
const process = (res) => {
    if (res.ok) {
        return res.json();
    } else if (res.status == 401 || res.status == 403) {
        window.location.path="/login"
    } else {
        throw new Error(res.status);
    }
}

window.api.getPatientVitals = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/vitals", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    })
}


window.api.addDoctor = (doctor) => {
    return fetch("/api/admin/doctor", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(doctor)
    }).then((res) => {
        return process(res);
    })
}

window.api.assignPatientToMyself = (code) => {

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

window.api.getPatientAnswers = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/answer", {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res)
    })

}


window.api.archiveAnswer = (patientId, answerId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/answer/" + answerId + "/seen", {
        method: "PUT",
        headers: headers,
    }).then((res) => {
        return process(res);
    })
}

window.api.getPatientMedicalProfile = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId, {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res);
    });

}

window.api.getPatientPersonalProfile = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId, {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res);
    });
}


window.api.getPatientsCount = () => {
    fetch("/api/doctor/patient/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

window.api.getUarchivedSymptomsCount = () => {
    fetch("/api/doctor/patient/symptom/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

window.api.getUnarchivedAnswersCount = () => {
    fetch("/api/doctor/patient/answer/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

window.api.getDoctorFeed = () => {
    fetch("/api/doctor/feed", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

window.api.getPatientSymptoms = (patientId) => {
    return  fetch("/api/doctor/patient/" + patientId + "/symptom", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

window.api.getPatients = () => {
    return fetch("/api/doctor/patient", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

window.api.getPatientQuestions = (patientId) => {
    return fetch("/api/doctor/patient/" + patientId + "/question", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

window.api.addQuestion = (patientId, question) => {
    fetch("/api/doctor/patient/" + patientId + "/question", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(question)
    }).then((res) => {
        return process(res);
    });
}

window.api.login = (username, password) => {
    fetch("/api/login", {
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