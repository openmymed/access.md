/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.notification.service.rest;

import io.javalin.http.Context;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.infra.notification.delegate.NotificationServiceDelegate;

/**
 *
 * @author tareq
 */
public class NotificationRestService {

    public void getNotifications(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(NotificationServiceDelegate.getInstance().getUserNotificationAndDeleteThem(user.getId()));
    }
}
