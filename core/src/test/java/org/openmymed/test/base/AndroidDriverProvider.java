/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.test.base;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.FULL_RESET;
import static io.appium.java_client.remote.MobileCapabilityType.NEW_COMMAND_TIMEOUT;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.remote.CapabilityType.APPLICATION_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static org.openqa.selenium.remote.CapabilityType.VERSION;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author tareq
 */
public class AndroidDriverProvider implements WebDriverProvider {
  @CheckReturnValue
  @Nonnull
  @Override
  public WebDriver createDriver(DesiredCapabilities capabilities) {
    capabilities.setCapability(PLATFORM_NAME, "Android");
    capabilities.setCapability(DEVICE_NAME, "Android Emulator");
    capabilities.setCapability(VERSION, "9.0");
    capabilities.setCapability(APPLICATION_NAME, "Appium");
    capabilities.setCapability(APP_PACKAGE, "me.kisoft.covid19");
    capabilities.setCapability(APP_ACTIVITY, "me.kisoft.covid19.LoginActivity");
    capabilities.setCapability(NEW_COMMAND_TIMEOUT, 11);
    capabilities.setCapability(FULL_RESET, false);
    try {
      return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}