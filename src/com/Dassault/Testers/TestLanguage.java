/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dassault.Testers;

import com.gtranslate.Language;
import com.gtranslate.Translator;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;

import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NRT4
 */
public class TestLanguage {

    public static String LanguageDetector(String text) {
        //load all languages:
        List<LanguageProfile> languageProfiles = null;
        try {
            languageProfiles = new LanguageProfileReader().readAllBuiltIn();
        } catch (IOException ex) {
            Logger.getLogger(TestLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                .withProfiles(languageProfiles)
                .build();

//create a text object factory
        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();

//query:
        TextObject textObject = textObjectFactory.forText(text);
        com.google.common.base.Optional<LdLocale> lang = languageDetector.detect(textObject);
        String language = lang.asSet().toString();
        language = language.replaceAll("[^A-Za-z]+", "");

        return language;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(LanguageDetector("Et rapide. Bon, j\\'arrête de le dire... LOL"));
    }

}
