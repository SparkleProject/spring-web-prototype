package com.lintech.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Task {
    public final static Integer STATE_RUN=0;
    public final static Integer STATE_STOP=1;
    public final static Integer STATE_PAUSE=2;
    
    private Integer id;

    private String name;

    private Integer period;

    private String targetObject;

    private Date beginDate;

    private Date endDate;

    private String cronExpression;

    private String description;

    private Integer state;

    private Date createDate;

    private String creator;

    private Date modifyDate;

    private String modificator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject == null ? null : targetObject.trim();
    }

    @JsonSerialize(using=com.lintech.core.CustomDateTimeSerializer.class)
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @JsonSerialize(using=com.lintech.core.CustomDateTimeSerializer.class)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }


    @JsonSerialize(using=com.lintech.core.CustomDateTimeSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    @JsonSerialize(using=com.lintech.core.CustomDateTimeSerializer.class)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModificator() {
        return modificator;
    }

    public void setModificator(String modificator) {
        this.modificator = modificator == null ? null : modificator.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}