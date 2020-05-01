/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.java.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.openmymed.accessmd.app.App;
import org.openmymed.accessmd.domain.core.entity.ICPCEntry;
import org.openmymed.accessmd.domain.core.enums.ICPCType;
import org.openmymed.accessmd.domain.core.service.ICPCService;
import org.openmymed.accessmd.domain.localization.service.LocalizationService;
import org.openmymed.accessmd.infra.core.factory.ICPCServiceFactory;
import org.openmymed.accessmd.infra.localization.delegate.LocalizationServiceFactory;

/**
 *
 * @author tareq
 */
@Log
public class ICPCParsingService {

    private static final ICPCService icpcService = ICPCServiceFactory.getInstance().get();
    private static final LocalizationService localizationService = LocalizationServiceFactory.getInstance().get();

    public static final void parse() {
        try {
            parseCodes();
            parseLocales();
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        log.log(Level.INFO, "Done Reading ICPC Codes");
    }

    private static void parseCodes() throws IOException {
        ArrayList<ICPCEntry> icpcEntries = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                App.class.getClassLoader().getResourceAsStream("icpc/icpc_codes.csv")));
        CSVParser csvFileParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(','));
        csvFileParser.iterator().forEachRemaining(record -> {
            icpcEntries.add(new ICPCEntry(record.get(0), ICPCType.valueOf(record.get(1))));
        });
        icpcService.setEntries(icpcEntries);
        log.info("Done Reading ICPC Codes");
    }

    private static void parseLocales() throws IOException {
        for (File file : getFileList()) {
            parseLocaleFile(file);
        }
        log.info("Done Reading All Locales");
    }

    private static void parseLocaleFile(File file) throws IOException {
        Locale locale = new Locale(file.getName().replaceAll("\\.csv", ""));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        CSVParser csvFileParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(','));
        csvFileParser.iterator().forEachRemaining(record -> {
           localizationService.set(record.get(0), locale, record.get(1));
        });
        log.log(Level.INFO, "Done Reading Locale {0}", locale.getLanguage());
    }

    private static File[] getFileList() {
        URL url = App.class.getClassLoader().getResource("icpc/localization");
        String path = url.getPath();
        File[] files = new File(path).listFiles();
        log.log(Level.INFO, "Found {0} Locales : {1}", new Object[]{files.length, Arrays.toString(files)});
        return files;
    }
}
