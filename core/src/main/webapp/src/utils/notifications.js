/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import logo from '../res/openmymed.png';

import { goto } from "redom-app";

const channel = new BroadcastChannel('sw-messages');
channel.addEventListener('message', event => {
    if (event.data.type === "NOTIFICATIONS") {
        event.data.notifications.map(notification => {
            if (notification.name === "vitalsMeasurmentCreated") {
                displayNotification("New Vitals Measurment", {
                    body: notification.data.patient.firstName + " " + notification.data.patient.lastName,
                    icon: logo,
                    data: {
                        view: "patient",
                        params: [notification.data.patient.id]
                    }

                })
            }
        })
    } else if (event.data.type === "REDIRECT") {
        goto(event.data.view, event.data.params)
    }
});

const handleNotificationsEvent = (notifications) => {

}
export const displayNotification = (name, options) => {
    if (window.Notification && Notification.permission === 'granted') {
        show(name, options)
    } else if (window.Notification && Notification.permission !== 'denied') {
        Notification.requestPermission(status => {
            if (status === 'granted') {
                show(name, options)
            } else {
                console.log('You denied or dismissed permissions to notifications.');
            }
        });
    } else {
        console.log('You denied permissions to notifications. Please go to your browser or phone setting to allow notifications.');
    }
}

const show = (name, options) => {
    window.sw.showNotification(name, options);
}