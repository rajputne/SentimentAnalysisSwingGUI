/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dassault.Algorithms;

/**
 *
 * @author NRT4
 */
import com.Dassault.Utilities.GeneralUtility;
import java.io.BufferedReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Based on SentiWordnet
 *
 * @author Adam Gibson
 *
 */
public class SWN3 implements Serializable {

    /**
     *
     */
    
    public static Double totalScore;

    private static final String FILENAME = "C:\\Users\\nrt4\\Documents\\NetBeansProjects\\SentimentAnalysisGUI\\sentiment\\sentiwordnet.txt";
    private static final long serialVersionUID = -2614454572930777658L;
    private HashMap<String, Double> _dict;

    public SWN3(String sentiWordNetPath) {

        //   ClassPathResource resource = new ClassPathResource(sentiWordNetPath);
        try {
            _dict = new HashMap<>();
            HashMap<String, Vector<Double>> _temp = new HashMap<String, Vector<Double>>();
            BufferedReader csv = null;
            FileReader fr = null;
            fr = new FileReader(FILENAME);
            csv = new BufferedReader(fr);
            //csv =  new BufferedReader(new FileReader(FILENAME));
            String line = "";
            while ((line = csv.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split("\t");

                if (data[2].isEmpty() || data[3].isEmpty()) {
                    continue;
                }
                Double score = Double.parseDouble(data[2]) - Double.parseDouble(data[3]);
                String[] words = data[4].split(" ");
                for (String w : words) {
                    if (w.isEmpty()) {
                        continue;
                    }
                    String[] w_n = w.split("#");
                    w_n[0] += "#" + data[0];
                    int index = Integer.parseInt(w_n[1]) - 1;
                    if (_temp.containsKey(w_n[0])) {
                        Vector<Double> v = _temp.get(w_n[0]);
                        if (index > v.size()) {
                            for (int i = v.size(); i < index; i++) {
                                v.add(0.0);
                            }
                        }
                        v.add(index, score);
                        _temp.put(w_n[0], v);
                    } else {
                        Vector<Double> v = new Vector();
                        for (int i = 0; i < index; i++) {
                            v.add(0.0);
                        }
                        v.add(index, score);
                        _temp.put(w_n[0], v);
                    }
                }
            }

            Set<String> temp = _temp.keySet();
            for (Iterator<String> iterator = temp.iterator(); iterator.hasNext();) {
                String word = iterator.next();
                Vector<Double> v = _temp.get(word);
                double score = 0.0;
                double sum = 0.0;
                for (int i = 0; i < v.size(); i++) {
                    score += ((double) 1 / (double) (i + 1)) * v.get(i);
                }
                for (int i = 1; i <= v.size(); i++) {
                    sum += (double) 1 / (double) i;
                }
                score /= sum;
                _dict.put(word, score);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String classForScore(Double score) {
        String sent = "Neutral";
        if (score >= 0.75) {
            sent = "Positive";
        } else if (score > 0.25 && score <= 0.5) {
            sent = "Positive";
        } else if (score > 0 && score >= 0.25) {
            sent = "Positive";
        } else if (score < 0 && score >= -0.25) {
            sent = "Negative";
        } else if (score < -0.25 && score >= -0.5) {
            sent = "Negative";
        } else if (score <= -0.75) {
            sent = "Negative";
        }
        return sent;
    }

    public Double extract(String word) {
        double total = 0.0;
        if (_dict.get(word + "#n") != null) {
            total = _dict.get(word + "#n") + total;
        }
        if (_dict.get(word + "#a") != null) {
            total = _dict.get(word + "#a") + total;
        }
        if (_dict.get(word + "#r") != null) {
            total = _dict.get(word + "#r") + total;
        }
        if (_dict.get(word + "#v") != null) {
            total = _dict.get(word + "#v") + total;
        }
        return total;
    }

    public static double extractSentenceScore(String sentence, SWN3 swn) {
        String[] words = sentence.split("\\s+");

        Double sentenceScore = 0d;
        Double total = 0d;
        for (int i = 0; i < words.length; i++) {

            words[i] = words[i].replaceAll("[^\\w]", "");
            total += swn.extract(words[i]);
        }
        sentenceScore = total / words.length;
        return sentenceScore;
    }
    
    public static String extractParagraphSentiment(String paragraph, SWN3 swn) {
        String simple = "[.?!]";
        String[] splitString = (paragraph.split(simple));
        Double totalSentenceScore = 0d;
        for (String string : splitString) {
            System.out.println(string);
            Double tempSentence = extractSentenceScore(string.toLowerCase(), swn);
            totalSentenceScore += tempSentence;
        }
        totalScore=totalSentenceScore;
        String overallSentimentScore = swn.classForScore(totalSentenceScore);
        return overallSentimentScore;
    }


    public static String removeUrl(String commentstr) {
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(commentstr);
        int i = 0;
        while (m.find()) {
            commentstr = commentstr.replaceAll(m.group(i), "").trim();
            i++;
        }
        return commentstr;
    }

    public static void main(String[] args) {
        SWN3 swn = new SWN3("/sentiment/sentiwordnet.txt");
        
        System.out.println(swn.classForScore(swn.extract("Toyota")));
        Double sentenceScore = extractSentenceScore("Toyota", swn);
        System.out.println(sentenceScore);
        
        System.out.println(swn.classForScore(swn.extract("good")));
        sentenceScore = extractSentenceScore("good", swn);
        System.out.println(sentenceScore);
        
        System.out.println(swn.classForScore(swn.extract("bad")));
        sentenceScore = extractSentenceScore("bad", swn);
        System.out.println(sentenceScore);
        
   
        
        System.out.println(sentenceScore);

        String paragraph = "Good.";
        paragraph = paragraph.toLowerCase();
        paragraph = paragraph.toString().trim().replaceAll(" +", " ");
       
        //1. Remove all the Digits
        paragraph = paragraph.replaceAll("[0-9]", "");
        //2. Remove Stopwords
        paragraph = GeneralUtility.cleanStopWords(paragraph);
        //3. Remove remove Urls
        paragraph = removeUrl(paragraph);

        String t = extractParagraphSentiment(paragraph, swn);
        System.out.println("Heyy:" + t);
    }
}
