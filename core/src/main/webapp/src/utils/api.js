/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import {goto} from "redom-app";

const headers = {
    "Content-Type": "application/json"
}
const process = (res) => {
    if (res.ok) {
        return res.json();
    } else if (res.status == 401 || res.status == 403) {
        goto("login", []);
    } else {
        throw new Error(res.status);
    }
}

export const getPatientVitals = (patientId) => {
    return  fetch("/doctor/patient/" + patientId + "/vitals", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    })
}


export const addDoctor = (doctor) => {
    return fetch("/admin/doctor", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(doctor)
    }).then((res) => {
        return process(res);
    })
}

export const assignPatientToMyself = (code) => {

    return fetch("/doctor/patient/add", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            code: code
        })
    }).then((res) => {
        return process(res);
    });
}

export const getPatientAnswers = (patientId) => {
    return  fetch("/doctor/patient/" + patientId + "/answer", {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res)
    })

}


export const archiveAnswer = (patientId, answerId) => {
    return  fetch("/doctor/patient/" + patientId + "/answer/" + answerId + "/seen", {
        method: "PUT",
        headers: headers,
    }).then((res) => {
        return process(res);
    })
}

export const getPatientMedicalProfile = (patientId) => {
    return  fetch("/doctor/patient/" + patientId, {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res);
    });

}

export const getPatientPersonalProfile = (patientId) => {
    return  fetch("/doctor/patient/" + patientId, {
        method: "GET",
        headers: headers,
    }).then((res) => {
        return process(res);
    });
}


export const getPatientsCount = () => {
    fetch("/doctor/patient/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

export const getUarchivedSymptomsCount = () => {
    fetch("/doctor/patient/symptom/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

export const getUnarchivedAnswersCount = () => {
    fetch("/doctor/patient/answer/count", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

export const getDoctorFeed = () => {
    fetch("/doctor/feed", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

export const getPatientSymptoms = (patientId) => {
    return  fetch("/doctor/patient/" + patientId + "/symptom", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

export const getPatients = () => {
    return fetch("/doctor/patient", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

export const getPatientQuestions = (patientId) => {
    return fetch("/doctor/patient/" + patientId + "/question", {
        method: "GET",
        headers: headers
    }).then((res) => {
        return process(res);
    });
}

export const addQuestion = (patientId, question) => {
    fetch("/doctor/patient/" + patientId + "/question", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(question)
    }).then((res) => {
        return process(res);
    });
}

export const login = (username, password) => {
    fetch("/login", {
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