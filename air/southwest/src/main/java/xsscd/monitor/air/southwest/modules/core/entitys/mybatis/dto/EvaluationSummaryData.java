package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;


public class EvaluationSummaryData implements Serializable {

    private static final long serialVersionUID = 1L;


    private String time;
    private String provinceName;
    private String provinceId;
    private String areaName;
    private String cityName;
    private String cityCode;
    private String quality;
    private String primarypollutant;
    private String PM25;
    private String aqi;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPrimarypollutant() {
        return primarypollutant;
    }

    public void setPrimarypollutant(String primarypollutant) {
        this.primarypollutant = primarypollutant;
    }

    public String getPM25() {
        return PM25;
    }

    public void setPM25(String PM25) {
        this.PM25 = PM25;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
}