package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto;

import java.util.Date;

public class ExpertAirLvlCountSource {
	private static final long serialVersionUID = 1L;
    private Long id;

    private Date dateTime;

    private String provinceName;

    private String areaName;

    private String cityName;

    private String cityCode;

    private String qualityScore;

    private String pm25Score;

    private String aqiScore;

    private String primaryPollutant1Score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(String qualityScore) {
        this.qualityScore = qualityScore;
    }

    public String getPm25Score() {
        return pm25Score;
    }

    public void setPm25Score(String pm25Score) {
        this.pm25Score = pm25Score;
    }

    public String getAqiScore() {
        return aqiScore;
    }

    public void setAqiScore(String aqiScore) {
        this.aqiScore = aqiScore;
    }

    public String getPrimaryPollutant1Score() {
        return primaryPollutant1Score;
    }

    public void setPrimaryPollutant1Score(String primaryPollutant1Score) {
        this.primaryPollutant1Score = primaryPollutant1Score;
    }
}