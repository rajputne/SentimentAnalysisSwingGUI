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
public class ServiceRequest {

    String srNumber;
    String surveyComments;

    String sentiment;
    double sentimentScore;

    double supportRepresentativeHelpfulness;
    double helpfulScore;
    double communicationQuality;
    double communicationScore;
    double productKnowledge;
    double productKnowledgeScore;
    double customerServiceQuality;
    double customerServiceScore;
    double sentimentWeightage;
    double sumOfScores;

    String submitterPlatform;
    String submitterSite;
    String closureType;
    String FEResponsible;
    String submitterGeography;
    String srType;
    


  
    
    

    public String getSrType() {
        return srType;
    }

    public void setSrType(String srType) {
        this.srType = srType;
    }

    public String getSubmitterPlatform() {
        return submitterPlatform;
    }

    public void setSubmitterPlatform(String submitterPlatform) {
        this.submitterPlatform = submitterPlatform;
    }

    public String getSubmitterSite() {
        return submitterSite;
    }

    public void setSubmitterSite(String submitterSite) {
        this.submitterSite = submitterSite;
    }

    public String getClosureType() {
        return closureType;
    }

    public void setClosureType(String closureType) {
        this.closureType = closureType;
    }

    public String getFEResponsible() {
        return FEResponsible;
    }

    public void setFEResponsible(String FEResponsible) {
        this.FEResponsible = FEResponsible;
    }

    public String getSubmitterGeography() {
        return submitterGeography;
    }

    public void setSubmitterGeography(String submitterGeography) {
        this.submitterGeography = submitterGeography;
    }

    public double getSentimentScore() {
        return sentimentScore;
    }

    public void setSentimentScore(double sentimentScore) {
        this.sentimentScore = sentimentScore;
    }

    public double getSentimentWeightage() {
        return sentimentWeightage;
    }

    public void setSentimentWeightage(double sentimentWeightage) {
        this.sentimentWeightage = sentimentWeightage;
    }

    public String getSrNumber() {
        return srNumber;
    }

    public void setSrNumber(String srNumber) {
        this.srNumber = srNumber;
    }

    public String getSurveyComments() {
        return surveyComments;
    }

    public void setSurveyComments(String surveyComments) {
        this.surveyComments = surveyComments;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public double getSupportRepresentativeHelpfulness() {
        return supportRepresentativeHelpfulness;
    }

    public void setSupportRepresentativeHelpfulness(double supportRepresentativeHelpfulness) {
        this.supportRepresentativeHelpfulness = supportRepresentativeHelpfulness;
    }

    public double getHelpfulScore() {
        return helpfulScore;
    }

    public void setHelpfulScore(double helpfulScore) {
        this.helpfulScore = helpfulScore;
    }

    public double getCommunicationQuality() {
        return communicationQuality;
    }

    public void setCommunicationQuality(double communicationQuality) {
        this.communicationQuality = communicationQuality;
    }

    public double getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(double communicationScore) {
        this.communicationScore = communicationScore;
    }

    public double getProductKnowledge() {
        return productKnowledge;
    }

    public void setProductKnowledge(double productKnowledge) {
        this.productKnowledge = productKnowledge;
    }

    public double getProductKnowledgeScore() {
        return productKnowledgeScore;
    }

    public void setProductKnowledgeScore(double productKnowledgeScore) {
        this.productKnowledgeScore = productKnowledgeScore;
    }

    public double getCustomerServiceQuality() {
        return customerServiceQuality;
    }

    public double getSumOfScores() {
        return sumOfScores;
    }

    public void setSumOfScores(double sumOfScores) {
        this.sumOfScores = sumOfScores;
    }

    public void setCustomerServiceQuality(double customerServiceQuality) {
        this.customerServiceQuality = customerServiceQuality;
    }

    public double getCustomerServiceScore() {
        return customerServiceScore;
    }

    public void setCustomerServiceScore(double customerServiceScore) {
        this.customerServiceScore = customerServiceScore;
    }

    public double getSumOfAllScores() {
        sumOfScores = 0.0;
        sumOfScores = getSentimentWeightage() + getCustomerServiceScore() + getCustomerServiceQuality() + getProductKnowledgeScore() + getProductKnowledge() + getCommunicationScore() + getCommunicationQuality() + getHelpfulScore() + getSupportRepresentativeHelpfulness();
        return sumOfScores;
    }
}
