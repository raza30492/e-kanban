package com.example.ekanban.dto;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public class FlowCsv {

    @CsvBindByName(column = "FLOW ID")
    private Double id;

    @CsvBindByName(column = "DESC")
    private String desc;

    @CsvBindByName(column = "RUN")
    private String run;

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

    @Override
    public String toString() {
        return "FlowCsv{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", run='" + run + '\'' +
                '}';
    }
}
