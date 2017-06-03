package com.example.ekanban.dto;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public class TestCsv {

    @CsvBindByName(column = "FLOW ID")
    private Double flowId;

    @CsvBindByName(column = "TEST ID")
    private Double id;

    @CsvBindByName(column = "DESC")
    private String desc;

    @CsvBindByName(column = "RUN")
    private String run;

    @CsvBindByName(column = "URL")
    private String url;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public Double getFlowId() {
        return flowId;
    }

    public void setFlowId(Double flowId) {
        this.flowId = flowId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TestCsv{" +
                "flowId=" + flowId +
                ", id=" + id +
                ", desc='" + desc + '\'' +
                ", run='" + run + '\'' +
                '}';
    }
}
