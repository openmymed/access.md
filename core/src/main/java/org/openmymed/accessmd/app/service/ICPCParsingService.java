/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Pattern;
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
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

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
        for (String localizationFileName : getLocalizationFileNames()) {
            parseLocaleFile(localizationFileName);
        }
        log.info("Done Reading All Locales");
    }

    private static void parseLocaleFile(String fileName) throws IOException {
        log.log(Level.INFO, "Reading File {0}", fileName);
        ClassLoader loader = App.class.getClassLoader();
        String[] splitFileName = fileName.replaceAll("\\.csv", "").split("/");
        Locale locale = new Locale(splitFileName[splitFileName.length -1]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(fileName)));
        CSVParser csvFileParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(','));
        csvFileParser.iterator().forEachRemaining(record -> {
            localizationService.set(record.get(0), locale, record.get(1));
        });
        log.log(Level.INFO, "Done Reading Locale {0}", locale.getLanguage());
    }

    private static Set<String> getLocalizationFileNames() throws IOException {
        Reflections reflections = new Reflections("icpc.localization", new ResourcesScanner());
        return reflections.getResources(Pattern.compile(".*\\.csv"));
    }
}
