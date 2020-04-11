/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.app.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.java.Log;
import me.kisoft.covid19.infra.auth.factory.SecurityCodeServiceFactory;

/**
 *
 * @author tareq
 */
@Service
@Log
public class SecurityCodesExpiryService extends BackgroundService {

    private static final ScheduledExecutorService SERVICE = Executors.newScheduledThreadPool(1);

    @Override
    public void start() {
        SERVICE.scheduleAtFixedRate(new SecurityCodesExpiryRunnable(), 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        SERVICE.shutdownNow();
    }

    private final class SecurityCodesExpiryRunnable implements Runnable {

        @Override
        public void run() {
            log.finest("Begun Expiring Security Codes");
            try {
                SecurityCodeServiceFactory.getInstance().get().expireCodes();
                log.finest("Done Expiring Security Codes");
            } catch (Exception ex) {
                log.severe("Error In Expiring Security Codes");
                log.severe(ex.getMessage());
            }

        }

    }

}
