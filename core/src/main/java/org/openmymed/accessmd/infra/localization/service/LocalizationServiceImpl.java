/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.localization.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.openmymed.accessmd.domain.localization.entity.LocalizationString;
import org.openmymed.accessmd.domain.localization.service.LocalizationService;

/**
 *
 * @author tareq
 */
public class LocalizationServiceImpl implements LocalizationService {

    private static final Map<String, LocalizationString> MAP = new HashMap<>();

    @Override
    public String get(String id, Locale locale) {
        LocalizationString localization = MAP.get(id);
        if (localization == null) {
            localization = new LocalizationString();
            localization.setId(id);
            MAP.put(id, localization);
        }
        return localization.get(locale);
    }

    @Override
    public void set(String id, Locale locale, String value) {
        LocalizationString localization = MAP.get(id);
        if (localization == null) {
            localization = new LocalizationString();
            localization.setId(id);

        }
        localization.set(locale, value);
        MAP.put(id, localization);
    }
}
