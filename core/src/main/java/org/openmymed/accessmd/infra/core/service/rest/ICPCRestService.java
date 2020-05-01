/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.rest;

import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.openmymed.accessmd.domain.core.entity.ICPCEntry;
import org.openmymed.accessmd.domain.core.service.ICPCService;
import org.openmymed.accessmd.domain.localization.service.LocalizationService;
import org.openmymed.accessmd.infra.core.factory.ICPCServiceFactory;
import org.openmymed.accessmd.infra.core.service.rest.vo.ICPCEntryVo;
import org.openmymed.accessmd.infra.localization.delegate.LocalizationServiceFactory;

/**
 *
 * @author tareq
 */
public class ICPCRestService {

    private final ICPCService icpcService = ICPCServiceFactory.getInstance().get();
    private final LocalizationService localizationService = LocalizationServiceFactory.getInstance().get();

    public void getICPCSymptoms(Context ctx) {
        List<ICPCEntryVo> entries = new ArrayList<>();
        String language = ctx.queryParam("language", String.class).getOrNull();
        if (language == null) {
            language = "en";
        }
        Locale locale = new Locale(language);
        for (ICPCEntry entry : icpcService.getSymptoms()) {
            entries.add(new ICPCEntryVo(entry, localizationService.get(entry.getCode(), locale)));
        }
        ctx.json(entries);
        ctx.res.setStatus(200);
    }
}
