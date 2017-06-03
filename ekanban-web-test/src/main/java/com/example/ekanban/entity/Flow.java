package com.example.ekanban.entity;

import com.opencsv.bean.CsvBindByName;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mdzahidraza on 03/06/17.
 */

@Entity
public class Flow implements Serializable{

    @Id
    @Column(name = "flow_id")
    @CsvBindByName(column = "FLOW ID")
    private Long id;

    @Column(name = "desc")
    @CsvBindByName(column = "DESC")
    private String desc;

    @Column(name = "run")
    @CsvBindByName(column = "RUN")
    private String run;

    @OneToMany(mappedBy = "flow", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<Test> tests = new HashSet<>();

    public Flow() {
    }

    public Flow(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Test> getTestList() {
        return tests;
    }

    public void setTestList(Set<Test> testList) {
        this.tests = testList;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", run='" + run + '\'' +
                ", testList=" + tests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flow flow = (Flow) o;

        return id.equals(flow.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
