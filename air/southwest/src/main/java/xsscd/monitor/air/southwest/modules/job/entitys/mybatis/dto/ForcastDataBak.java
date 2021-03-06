package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto;

import java.util.Date;

public class ForcastDataBak {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.forecast_model
     *
     * @mbggenerated
     */
    private String forecastModel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.forecast_model_value
     *
     * @mbggenerated
     */
    private Integer forecastModelValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.AQI
     *
     * @mbggenerated
     */
    private String aQI;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.PM2_5
     *
     * @mbggenerated
     */
    private String pM25;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.PM10
     *
     * @mbggenerated
     */
    private String pM10;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.SO2
     *
     * @mbggenerated
     */
    private String sO2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.NO2
     *
     * @mbggenerated
     */
    private String nO2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.CO
     *
     * @mbggenerated
     */
    private String cO;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.O3
     *
     * @mbggenerated
     */
    private String o3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.RH
     *
     * @mbggenerated
     */
    private String rH;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.Precipitation
     *
     * @mbggenerated
     */
    private String precipitation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.Temperature
     *
     * @mbggenerated
     */
    private String temperature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.WindSpeed
     *
     * @mbggenerated
     */
    private String windSpeed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.Pressure
     *
     * @mbggenerated
     */
    private String pressure;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.CloudCover
     *
     * @mbggenerated
     */
    private String cloudCover;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.PBL
     *
     * @mbggenerated
     */
    private String pBL;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.forcast_type
     *
     * @mbggenerated
     */
    private String forcastType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.forcast_time
     *
     * @mbggenerated
     */
    private Integer forcastTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.predict_time
     *
     * @mbggenerated
     */
    private Date predictTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.province
     *
     * @mbggenerated
     */
    private String province;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.province_code
     *
     * @mbggenerated
     */
    private String provinceCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.city
     *
     * @mbggenerated
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.city_code
     *
     * @mbggenerated
     */
    private String cityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.station
     *
     * @mbggenerated
     */
    private String station;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.station_code
     *
     * @mbggenerated
     */
    private String stationCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.county
     *
     * @mbggenerated
     */
    private String county;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.county_code
     *
     * @mbggenerated
     */
    private String countyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forcast_data_bak.WindDIR
     *
     * @mbggenerated
     */
    private String windDIR;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.id
     *
     * @return the value of forcast_data_bak.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.id
     *
     * @param id the value for forcast_data_bak.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.forecast_model
     *
     * @return the value of forcast_data_bak.forecast_model
     *
     * @mbggenerated
     */
    public String getForecastModel() {
        return forecastModel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.forecast_model
     *
     * @param forecastModel the value for forcast_data_bak.forecast_model
     *
     * @mbggenerated
     */
    public void setForecastModel(String forecastModel) {
        this.forecastModel = forecastModel == null ? null : forecastModel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.forecast_model_value
     *
     * @return the value of forcast_data_bak.forecast_model_value
     *
     * @mbggenerated
     */
    public Integer getForecastModelValue() {
        return forecastModelValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.forecast_model_value
     *
     * @param forecastModelValue the value for forcast_data_bak.forecast_model_value
     *
     * @mbggenerated
     */
    public void setForecastModelValue(Integer forecastModelValue) {
        this.forecastModelValue = forecastModelValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.AQI
     *
     * @return the value of forcast_data_bak.AQI
     *
     * @mbggenerated
     */
    public String getaQI() {
        return aQI;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.AQI
     *
     * @param aQI the value for forcast_data_bak.AQI
     *
     * @mbggenerated
     */
    public void setaQI(String aQI) {
        this.aQI = aQI == null ? null : aQI.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.PM2_5
     *
     * @return the value of forcast_data_bak.PM2_5
     *
     * @mbggenerated
     */
    public String getpM25() {
        return pM25;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.PM2_5
     *
     * @param pM25 the value for forcast_data_bak.PM2_5
     *
     * @mbggenerated
     */
    public void setpM25(String pM25) {
        this.pM25 = pM25 == null ? null : pM25.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.PM10
     *
     * @return the value of forcast_data_bak.PM10
     *
     * @mbggenerated
     */
    public String getpM10() {
        return pM10;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.PM10
     *
     * @param pM10 the value for forcast_data_bak.PM10
     *
     * @mbggenerated
     */
    public void setpM10(String pM10) {
        this.pM10 = pM10 == null ? null : pM10.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.SO2
     *
     * @return the value of forcast_data_bak.SO2
     *
     * @mbggenerated
     */
    public String getsO2() {
        return sO2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.SO2
     *
     * @param sO2 the value for forcast_data_bak.SO2
     *
     * @mbggenerated
     */
    public void setsO2(String sO2) {
        this.sO2 = sO2 == null ? null : sO2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.NO2
     *
     * @return the value of forcast_data_bak.NO2
     *
     * @mbggenerated
     */
    public String getnO2() {
        return nO2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.NO2
     *
     * @param nO2 the value for forcast_data_bak.NO2
     *
     * @mbggenerated
     */
    public void setnO2(String nO2) {
        this.nO2 = nO2 == null ? null : nO2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.CO
     *
     * @return the value of forcast_data_bak.CO
     *
     * @mbggenerated
     */
    public String getcO() {
        return cO;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.CO
     *
     * @param cO the value for forcast_data_bak.CO
     *
     * @mbggenerated
     */
    public void setcO(String cO) {
        this.cO = cO == null ? null : cO.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.O3
     *
     * @return the value of forcast_data_bak.O3
     *
     * @mbggenerated
     */
    public String getO3() {
        return o3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.O3
     *
     * @param o3 the value for forcast_data_bak.O3
     *
     * @mbggenerated
     */
    public void setO3(String o3) {
        this.o3 = o3 == null ? null : o3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.RH
     *
     * @return the value of forcast_data_bak.RH
     *
     * @mbggenerated
     */
    public String getrH() {
        return rH;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.RH
     *
     * @param rH the value for forcast_data_bak.RH
     *
     * @mbggenerated
     */
    public void setrH(String rH) {
        this.rH = rH == null ? null : rH.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.Precipitation
     *
     * @return the value of forcast_data_bak.Precipitation
     *
     * @mbggenerated
     */
    public String getPrecipitation() {
        return precipitation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.Precipitation
     *
     * @param precipitation the value for forcast_data_bak.Precipitation
     *
     * @mbggenerated
     */
    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation == null ? null : precipitation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.Temperature
     *
     * @return the value of forcast_data_bak.Temperature
     *
     * @mbggenerated
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.Temperature
     *
     * @param temperature the value for forcast_data_bak.Temperature
     *
     * @mbggenerated
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.WindSpeed
     *
     * @return the value of forcast_data_bak.WindSpeed
     *
     * @mbggenerated
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.WindSpeed
     *
     * @param windSpeed the value for forcast_data_bak.WindSpeed
     *
     * @mbggenerated
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed == null ? null : windSpeed.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.Pressure
     *
     * @return the value of forcast_data_bak.Pressure
     *
     * @mbggenerated
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.Pressure
     *
     * @param pressure the value for forcast_data_bak.Pressure
     *
     * @mbggenerated
     */
    public void setPressure(String pressure) {
        this.pressure = pressure == null ? null : pressure.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.CloudCover
     *
     * @return the value of forcast_data_bak.CloudCover
     *
     * @mbggenerated
     */
    public String getCloudCover() {
        return cloudCover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.CloudCover
     *
     * @param cloudCover the value for forcast_data_bak.CloudCover
     *
     * @mbggenerated
     */
    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover == null ? null : cloudCover.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.PBL
     *
     * @return the value of forcast_data_bak.PBL
     *
     * @mbggenerated
     */
    public String getpBL() {
        return pBL;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.PBL
     *
     * @param pBL the value for forcast_data_bak.PBL
     *
     * @mbggenerated
     */
    public void setpBL(String pBL) {
        this.pBL = pBL == null ? null : pBL.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.forcast_type
     *
     * @return the value of forcast_data_bak.forcast_type
     *
     * @mbggenerated
     */
    public String getForcastType() {
        return forcastType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.forcast_type
     *
     * @param forcastType the value for forcast_data_bak.forcast_type
     *
     * @mbggenerated
     */
    public void setForcastType(String forcastType) {
        this.forcastType = forcastType == null ? null : forcastType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.forcast_time
     *
     * @return the value of forcast_data_bak.forcast_time
     *
     * @mbggenerated
     */
    public Integer getForcastTime() {
        return forcastTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.forcast_time
     *
     * @param forcastTime the value for forcast_data_bak.forcast_time
     *
     * @mbggenerated
     */
    public void setForcastTime(Integer forcastTime) {
        this.forcastTime = forcastTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.predict_time
     *
     * @return the value of forcast_data_bak.predict_time
     *
     * @mbggenerated
     */
    public Date getPredictTime() {
        return predictTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.predict_time
     *
     * @param predictTime the value for forcast_data_bak.predict_time
     *
     * @mbggenerated
     */
    public void setPredictTime(Date predictTime) {
        this.predictTime = predictTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.province
     *
     * @return the value of forcast_data_bak.province
     *
     * @mbggenerated
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.province
     *
     * @param province the value for forcast_data_bak.province
     *
     * @mbggenerated
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.province_code
     *
     * @return the value of forcast_data_bak.province_code
     *
     * @mbggenerated
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.province_code
     *
     * @param provinceCode the value for forcast_data_bak.province_code
     *
     * @mbggenerated
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.city
     *
     * @return the value of forcast_data_bak.city
     *
     * @mbggenerated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.city
     *
     * @param city the value for forcast_data_bak.city
     *
     * @mbggenerated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.city_code
     *
     * @return the value of forcast_data_bak.city_code
     *
     * @mbggenerated
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.city_code
     *
     * @param cityCode the value for forcast_data_bak.city_code
     *
     * @mbggenerated
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.station
     *
     * @return the value of forcast_data_bak.station
     *
     * @mbggenerated
     */
    public String getStation() {
        return station;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.station
     *
     * @param station the value for forcast_data_bak.station
     *
     * @mbggenerated
     */
    public void setStation(String station) {
        this.station = station == null ? null : station.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.station_code
     *
     * @return the value of forcast_data_bak.station_code
     *
     * @mbggenerated
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.station_code
     *
     * @param stationCode the value for forcast_data_bak.station_code
     *
     * @mbggenerated
     */
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.county
     *
     * @return the value of forcast_data_bak.county
     *
     * @mbggenerated
     */
    public String getCounty() {
        return county;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.county
     *
     * @param county the value for forcast_data_bak.county
     *
     * @mbggenerated
     */
    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.county_code
     *
     * @return the value of forcast_data_bak.county_code
     *
     * @mbggenerated
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.county_code
     *
     * @param countyCode the value for forcast_data_bak.county_code
     *
     * @mbggenerated
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forcast_data_bak.WindDIR
     *
     * @return the value of forcast_data_bak.WindDIR
     *
     * @mbggenerated
     */
    public String getWindDIR() {
        return windDIR;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forcast_data_bak.WindDIR
     *
     * @param windDIR the value for forcast_data_bak.WindDIR
     *
     * @mbggenerated
     */
    public void setWindDIR(String windDIR) {
        this.windDIR = windDIR == null ? null : windDIR.trim();
    }
}