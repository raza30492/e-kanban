package com.example.ekanban.entity;

import com.example.ekanban.Action;
import com.example.ekanban.Condition;
import com.example.ekanban.Occurence;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mdzahidraza on 03/06/17.
 */

@Entity
public class Step implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "step_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "step_no")
    private Integer stepNo;

    @Column(name = "desc")
    private String desc;

    @Column(name = "condition")
    private String condition;

    @Column(name = "occurence")
    private String occurence;

    @Column(name = "element_no")
    private int elementNo;

    @Column(name = "action")
    private String action;

    @Column(name = "xpath")
    private String xpath;

    @Column(name = "value")
    private String value;

    public Step() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStepNo() {
        return stepNo;
    }

    public void setStepNo(Integer stepNo) {
        this.stepNo = stepNo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Condition getCondition() {
        return Condition.parse(condition);
    }

    public void setCondition(Condition condition) {
        this.condition = condition.getValue();
    }

    public Action getAction() {
        return Action.parse(action);
    }

    public void setAction(Action action) {
        this.action = action.getValue();
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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Occurence getOccurence() {
        return Occurence.parse(occurence);
    }

    public void setOccurence(Occurence occurence) {
        this.occurence = occurence.getValue();
    }

    public int getElementNo() {
        return elementNo;
    }

    public void setElementNo(int elementNo) {
        this.elementNo = elementNo;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        return id.equals(step.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
