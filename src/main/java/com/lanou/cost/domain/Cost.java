package com.lanou.cost.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dllo on 17/11/13.
 */
public class Cost implements Serializable{
    private int cost_id;//资费id
    @Length(min = 1,max = 10,message = "字符长度在1到10之间")
    private String name; //资费名字
    private int base_duration; //基本时长
    private int base_cost;//基本花费
    private int unit_cost;//单位花费
    private String status; //状态
    @Length(min = 1, max = 100, message = "100长度的字母、数字、汉字和下划线的组合")
    private String descr; //描述
    private Timestamp creatTime; //创建时间
    private Timestamp startTime;//开始时间
    private String cost_type;//套餐类型

    public Cost() {
    }

    public Cost(int cost_id, String name, int base_duration, int base_cost, int unit_cost, String status, String descr, Timestamp creatTime, Timestamp startTime, String cost_type) {
        this.cost_id = cost_id;
        this.name = name;
        this.base_duration = base_duration;
        this.base_cost = base_cost;
        this.unit_cost = unit_cost;
        this.status = status;
        this.descr = descr;
        this.creatTime = creatTime;
        this.startTime = startTime;
        this.cost_type = cost_type;
    }

    public Cost(String name, int base_duration, int base_cost, int unit_cost, String status, Timestamp creatTime, Timestamp startTime) {
        this.name = name;
        this.base_duration = base_duration;
        this.base_cost = base_cost;
        this.unit_cost = unit_cost;
        this.status = status;
        this.creatTime = creatTime;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "cost_id=" + cost_id +
                ", name='" + name + '\'' +
                ", base_duration=" + base_duration +
                ", base_cost=" + base_cost +
                ", unit_cost=" + unit_cost +
                ", status='" + status + '\'' +
                ", descr='" + descr + '\'' +
                ", creatTime=" + creatTime +
                ", startTime=" + startTime +
                ", cost_type='" + cost_type + '\'' +
                '}';
    }

    public int getCost_id() {
        return cost_id;
    }

    public void setCost_id(int cost_id) {
        this.cost_id = cost_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBase_duration() {
        return base_duration;
    }

    public void setBase_duration(int base_duration) {
        this.base_duration = base_duration;
    }

    public int getBase_cost() {
        return base_cost;
    }

    public void setBase_cost(int base_cost) {
        this.base_cost = base_cost;
    }

    public int getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(int unit_cost) {
        this.unit_cost = unit_cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }
}
