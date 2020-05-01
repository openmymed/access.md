/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.localization.service;

import java.util.Locale;

/**
 *
 * @author tareq
 */
public interface LocalizationService {

    String get(String id, Locale locale);

    void set(String id, Locale locale, String value);
}
