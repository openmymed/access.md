/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

export const getPatientVitals = (patientId) => {
    return  fetch("/doctor/patient/" + patientId + "/vitals", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    }).then((res) => {
        if (res.ok) {
            return res.json();
        } else {
            alert("Wrong username or password");
        }
    })
}
