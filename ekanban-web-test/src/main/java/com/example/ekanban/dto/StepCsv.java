package com.example.ekanban.dto;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public class StepCsv {

    @CsvBindByName(column = "TEST ID")
    private Double testId;

    @CsvBindByName(column = "STEP ID")
    private Double id;

    @CsvBindByName(column = "STEP NO")
    private Double stepNo;

    @CsvBindByName(column = "DESC")
    private String desc;

    @CsvBindByName(column = "CONDITION")
    private String condition;

    @CsvBindByName(column = "OCCURENCE")
    private String occurence;

    @CsvBindByName(column = "ELEMENT NO")
    private Double elementNo;

    @CsvBindByName(column = "ACTION")
    private String action;

    @CsvBindByName(column = "XPATH")
    private String xpath;

    @CsvBindByName(column = "VALUE")
    private String value;

    public Double getTestId() {
        return testId;
    }

    public void setTestId(Double testId) {
        this.testId = testId;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getStepNo() {
        return stepNo;
    }

    public void setStepNo(Double stepNo) {
        this.stepNo = stepNo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOccurence() {
        return occurence;
    }

    public void setOccurence(String occurence) {
        this.occurence = occurence;
    }

    public Double getElementNo() {
        return elementNo;
    }

    public void setElementNo(Double elementNo) {
        this.elementNo = elementNo;
    }

    @Override
    public String toString() {
        return "StepCsv{" +
                "testId=" + testId +
                ", id=" + id +
                ", stepNo=" + stepNo +
                ", desc='" + desc + '\'' +
                ", condition='" + condition + '\'' +
                ", occurence='" + occurence + '\'' +
                ", elementNo=" + elementNo +
                ", action='" + action + '\'' +
                ", xpath='" + xpath + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
