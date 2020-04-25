/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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