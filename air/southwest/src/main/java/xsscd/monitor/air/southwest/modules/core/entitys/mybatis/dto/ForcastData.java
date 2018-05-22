package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.Date;

public class ForcastData implements Serializable {

    private static final long serialVersionUID = 1L;

    //省ID
    private Long aid;
    //区域ID
    private Long zoneId;
    //城市编码
    private Integer cityCode;
    //城市名
    private String cityName;
    //城市名
    private String ProvinceName;
    //城市名
    private String areaName;
    //预测日期
    private Date forecastDate;
    //首要污染物
    private String primarypollutant;
    //AQI
    private String AQI;
    //PM2_5
    private String PM2_5;
    //O3
    private String O3;
    //quality--污染等级
    private String quality;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String[] getPrimarypollutant() {
        if(primarypollutant!=null){
            String[] primarypollutants = primarypollutant.split("\\|");
            return primarypollutants;
        }
        return null;
    }

    public void setPrimarypollutant(String primarypollutant) {
        this.primarypollutant = primarypollutant;
    }

    public String[] getAQI() {
        if(AQI!=null){
            String[] AQIs = AQI.split("\\|");
            return AQIs;
        }
        return null;
    }

    public void setAQI(String AQI) {
        this.AQI = AQI;
    }

    public String[] getPM2_5() {
        if(PM2_5!=null){
            String[] PM2_5s = PM2_5.split("\\|");
            return PM2_5s;
        }
        return null;
    }

    public void setPM2_5(String PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public String[] getO3() {
        if(O3!=null){
            String[] O3s = O3.split("\\|");
            return O3s;
        }
        return null;
    }

    public void setO3(String o3) {
        O3 = o3;
    }

    public String[] getQuality() {
        if(quality!=null){
            String[] qualitys = quality.split("\\|");
            return qualitys;
        }
        return null;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}