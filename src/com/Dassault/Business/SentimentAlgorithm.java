/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dassault.Business;

/**
 *
 * @author NRT4
 */
public class SentimentAlgorithm {
    
    public String sentimentAlgorithm;
    public double sentimentScore;
    public String sentiment;

    public String getSentimentAlgorithm() {
        return sentimentAlgorithm;
    }

    public void setSentimentAlgorithm(String sentimentAlgorithm) {
        this.sentimentAlgorithm = sentimentAlgorithm;
    }

    public double getSentimentScore() {
        return sentimentScore;
    }

    public void setSentimentScore(double sentimentScore) {
        this.sentimentScore = sentimentScore;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
    
}
