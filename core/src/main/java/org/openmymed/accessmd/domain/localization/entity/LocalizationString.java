/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.localization.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tareq
 */
public class LocalizationString {

    @Getter
    @Setter
    private String id;
    @JsonIgnore
    @Transient
    private final Map<Locale, String> locales = new HashMap<>();

    public String get(Locale locale) {
        String toReturn = locales.getOrDefault(locale, null);
        if (toReturn == null) {
            return get();
        }
        return toReturn;
    }

    public String get() {
        return locales.get(Locale.ENGLISH);
    }

    public void set(String value) {
        if (StringUtils.isBlank(value)) {
            value = null;
        }
        locales.put(Locale.ENGLISH, value);
    }

    public void set(Locale locale, String value) {
        if (StringUtils.isBlank(value)) {
            value = null;
        }
        locales.put(locale, value);
    }

}
