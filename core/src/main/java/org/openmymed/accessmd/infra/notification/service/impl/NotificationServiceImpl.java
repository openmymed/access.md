/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.notification.service.impl;

import java.io.File;
import java.util.List;
import lombok.extern.java.Log;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.openmymed.accessmd.infra.notification.service.NotificationService;
import org.openmymed.accessmd.infra.notification.vo.Notification;

/**
 *
 * @author tareq
 */
@Log
public class NotificationServiceImpl implements NotificationService {

    private static Nitrite db;

    static {
        File file = new File("./data");
        if (!file.exists()) {
            file.mkdirs();
        }
        db = Nitrite.builder()
                .compressed()
                .filePath("./data/notification.db")
                .enableOffHeapStorage()
                .openOrCreate();
    }

    private static final String USER_COLLECTION = "%s_notifications_";

    private ObjectRepository<Notification> getUserRepository(Long userId) {
        return db.getRepository(String.format(USER_COLLECTION, String.valueOf(userId)), Notification.class);
    }

    @Override
    public void addNotification(Long userId, String name, Object data) {
        Notification n = new Notification();
        n.setName(name);
        n.setData(data);
        getUserRepository(userId).insert(n);
    }

    @Override
    public List<Notification> getUserNotificationAndDeleteThem(Long userId) {
        List<Notification> notifications = getUserRepository(userId).find().toList();
        getUserRepository(userId).drop();
        return notifications;
    }

}
