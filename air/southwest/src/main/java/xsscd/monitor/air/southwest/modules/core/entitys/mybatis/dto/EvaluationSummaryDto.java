package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;


public class EvaluationSummaryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int timeType; //时段间隔（1：月，2：季度，3：年）
    private int startYear;//开始时间：年
    private int startTime;//开始时间：月、季度
    private int endYear;//结束时间：年
    private int endTime;//结束时间：月、季度
    private int model;//模式选择
    private int day;//预测时间选择：1d,2d.......
    private String[] provinces;//模式选择

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String[] getProvinces() {
        return provinces;
    }

    public void setProvinces(String[] provinces) {
        this.provinces = provinces;
    }
}