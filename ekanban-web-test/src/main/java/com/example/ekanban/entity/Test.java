package com.example.ekanban.entity;

import com.opencsv.bean.CsvBindByName;

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
@Table(name = "TEST")
public class Test implements Serializable{

    @Id
    @Column(name = "test_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flow_id")
    private Flow flow;

    @Column(name = "desc")
    private String desc;

    @Column(name = "url")
    private String url;

    @Column(name = "run")
    private String run;

    @OneToMany
    private Set<Step> stepList = new HashSet<>();

    public Test() {
    }

    public Test(Long id) {
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

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Set<Step> getStepList() {
        return stepList;
    }

    public void setStepList(Set<Step> stepList) {
        this.stepList = stepList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", flowId=" + flow.getId() +
                ", desc='" + desc + '\'' +
                ", run='" + run + '\'' +
                ", stepList=" + stepList +
                ", url=" + url +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        return id.equals(test.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
