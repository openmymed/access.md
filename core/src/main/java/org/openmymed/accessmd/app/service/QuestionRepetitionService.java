/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.app.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.java.Log;

/**
 *
 * @author tareq
 */
@Service
@Log
public class QuestionRepetitionService extends BackgroundService {

  private static final ScheduledExecutorService SERVICE = Executors.newScheduledThreadPool(1);

  @Override
  public void start() {
    SERVICE.scheduleAtFixedRate(new QuestionRepetitionRunnable(), 0, 5, TimeUnit.SECONDS);
  }

  @Override
  public void stop() {
    SERVICE.shutdownNow();
  }

  private final class QuestionRepetitionRunnable implements Runnable {

    @Override
    public void run() {

    }

  }

}
