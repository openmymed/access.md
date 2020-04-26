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
    
    public void addNotification(Long userId, String name, Object data);
    
    public List<Notification> getUserNotificationAndDeleteThem(Long userId);
}
