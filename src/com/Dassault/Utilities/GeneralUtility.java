/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dassault.Utilities;

import com.Dassault.Business.ScoreWeightage;
import com.Dassault.GUI.SentimentAnalysisFileGenerator;
import com.Dassault.Testers.TestLanguage;
import com.google.common.base.Optional;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NRT4
 */
public class GeneralUtility {

    public static double genericScoresForValues(int numericValue) {
        double numericScores = 0.0;
        switch (numericValue) {
            case 1:
                numericScores = ScoreWeightage.TWENTY_FIVE_PERCENT;
            case 2:
                numericScores = ScoreWeightage.FIFTY_PERCENT;
            case 3:
                numericScores = ScoreWeightage.SEVENTY_FIVE_PERCENT;
            case 4:
                numericScores = ScoreWeightage.HUNDERED_PERCENT;
            default:
                numericScores = 0.5;
        }
        return numericScores;
    }

    public static String LanguageDetector(String text) {
        //load all languages:
        List<LanguageProfile> languageProfiles = null;
        try {
            languageProfiles = new LanguageProfileReader().readAllBuiltIn();
        } catch (IOException ex) {
            Logger.getLogger(TestLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard()).withProfiles(languageProfiles).build();
        //create a text object factory
        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
        //query:
        TextObject textObject = textObjectFactory.forText(text);
        Optional<LdLocale> lang = languageDetector.detect(textObject);
        String language = lang.asSet().toString();
        language = language.replaceAll("[^A-Za-z]+", "");
        return language;
    }

    public static String cleanStopWords(String inputText) {
        String[] stopwords = {"the", "-RRB-", "-LRB-", "a", "as", "able", "about", "WHEREAS", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
        List<String> wordsList = new ArrayList<String>();
        //String tweet = "Feeling miserable with the cold? Here's WHAT you can do.";
        inputText = inputText.trim().replaceAll("\\s+", " ");
        //Get all the words Tokenize rather than spliting
        String[] words = inputText.split(" ");
        for (String word : words) {
            wordsList.add(word);
        }
        //remove stop words here from the temp list
        for (int i = 0; i < wordsList.size(); i++) {
            // get the item as string
            for (int j = 0; j < stopwords.length; j++) {
                if (stopwords[j].contains(wordsList.get(i)) || stopwords[j].toUpperCase().contains(wordsList.get(i))) {
                    wordsList.remove(i);
                }
            }
        }
        String cleanString = "";
        for (String str : wordsList) {
            cleanString = cleanString.replaceAll(",", "");
            cleanString = cleanString + " " + str;
        }
        return cleanString;
    }

    public enum Operation {
        NOT_APPLICABLE("not applicable"),
        VERY_SATISFIED("very satisfied"),
        SATISFIED("satisfied"),
        UNSATSFIED("unsatisfied"),
        VERY_UNSATISFIED("very unsatisfied");
        private String text;

        Operation(String text) {
            this.text = text;
        }

        public String text() {
            return text;
        }

        double calculate() {
            switch (this) {
                case NOT_APPLICABLE:
                    return ScoreWeightage.ZERO_PERCENT;
                case SATISFIED:
                    return ScoreWeightage.SEVENTY_FIVE_PERCENT;
                case VERY_SATISFIED:
                    return ScoreWeightage.HUNDERED_PERCENT;
                case UNSATSFIED:
                    return ScoreWeightage.TWENTY_FIVE_PERCENT;
                case VERY_UNSATISFIED:
                    return ScoreWeightage.ZERO_PERCENT;
                default:
                    throw new AssertionError("Unknown operations " + this);
            }
        }
    }
    
    public static double genericScoresForValues(String valueOfFeedback) {
        double score = 0.0;
        if (!valueOfFeedback.isEmpty()) {
            if (valueOfFeedback.equals(GeneralUtility.Operation.NOT_APPLICABLE.text())) {
                score = GeneralUtility.Operation.NOT_APPLICABLE.calculate();
            } else if (valueOfFeedback.equals(GeneralUtility.Operation.SATISFIED.text())) {
                score = GeneralUtility.Operation.SATISFIED.calculate();
            } else if (valueOfFeedback.equals(GeneralUtility.Operation.UNSATSFIED.text())) {
                score = GeneralUtility.Operation.UNSATSFIED.calculate();
            } else if (valueOfFeedback.equals(GeneralUtility.Operation.VERY_SATISFIED.text())) {
                score = GeneralUtility.Operation.VERY_SATISFIED.calculate();
            } else if (valueOfFeedback.equals(GeneralUtility.Operation.VERY_UNSATISFIED.text())) {
                score = GeneralUtility.Operation.VERY_UNSATISFIED.calculate();
            }
        }
        return score;
    }
    
}
