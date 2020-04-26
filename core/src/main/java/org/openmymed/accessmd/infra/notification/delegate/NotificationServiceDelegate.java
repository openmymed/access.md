/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.notification.delegate;

import java.util.List;
import org.openmymed.accessmd.infra.notification.service.NotificationService;
import org.openmymed.accessmd.infra.notification.service.impl.NotificationServiceImpl;
import org.openmymed.accessmd.infra.notification.vo.Notification;

/**
 *
 * @author tareq
 */
public class NotificationServiceDelegate implements NotificationService{
    
    private NotificationService service = new NotificationServiceImpl();
    private static NotificationServiceDelegate instance = getInstance();
    private NotificationServiceDelegate(){
        
    }
    
    public static final NotificationServiceDelegate getInstance(){
        if(instance == null){
            instance = new NotificationServiceDelegate();
        }
        return instance;
    }
    @Override
    public void addNotification(Long userId, String name, Object data) {
        service.addNotification(userId, name, data);
    }

    @Override
    public List<Notification> getUserNotificationAndDeleteThem(Long userId) {
        return service.getUserNotificationAndDeleteThem(userId);
    }
}
