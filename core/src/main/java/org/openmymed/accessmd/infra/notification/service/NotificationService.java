/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.notification.service;

import java.util.List;
import org.openmymed.accessmd.infra.notification.vo.Notification;

/**
 *
 * @author tareq
 */
public interface NotificationService {
    
    /**
     * Add Notification for a user to the notification queue
     * @param userId the user to add notifications for
     * @param name the name of the notification event
     * @param data the data of the notification
     */
    public void addNotification(Long userId, String name, Object data);
    
    /**
     * Retrieves all the user's notifications and removes them from the notification queue
     * @param userId the user to get notifications for
     * @return  a list of notifications
     */
    public List<Notification> getUserNotificationAndDeleteThem(Long userId);
}
