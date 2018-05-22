package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class AirQualityLvlForecastStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long aid;
    //区域ID
    private Long zoneId;
    //城市编码
    private Integer cityCode;
    //记录日期
    private Date creatDate;
    //预测日期
    private Date forecastDate;
    //填表人
    private String creatName;
    //预测等级名称
    private String levelName;
    //预测等级编码
    private String levelCode;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreatDate() {
        return creatDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getForecastDate() {
        return forecastDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }
}