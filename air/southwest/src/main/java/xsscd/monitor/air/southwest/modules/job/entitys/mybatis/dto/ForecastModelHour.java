package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectVO;

import java.util.Date;

public class ForecastModelHour extends SelectVO {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.timepoint
     *
     * @mbggenerated
     */
    private Date timepoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.stationname
     *
     * @mbggenerated
     */
    private String stationname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.CityCode
     *
     * @mbggenerated
     */
    private Integer cityCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.timepoint
     *
     * @return the value of forecastModelHour.timepoint
     *
     * @mbggenerated
     */
    public Date getTimepoint() {
        return timepoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.timepoint
     *
     * @param timepoint the value for forecastModelHour.timepoint
     *
     * @mbggenerated
     */
    public void setTimepoint(Date timepoint) {
        this.timepoint = timepoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.stationname
     *
     * @return the value of forecastModelHour.stationname
     *
     * @mbggenerated
     */
    public String getStationname() {
        return stationname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.stationname
     *
     * @param stationname the value for forecastModelHour.stationname
     *
     * @mbggenerated
     */
    public void setStationname(String stationname) {
        this.stationname = stationname == null ? null : stationname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.CityCode
     *
     * @return the value of forecastModelHour.CityCode
     *
     * @mbggenerated
     */
    public Integer getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.CityCode
     *
     * @param cityCode the value for forecastModelHour.CityCode
     *
     * @mbggenerated
     */
    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.ID
     *
     * @mbggenerated
     */
    private Integer iD;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.CityName
     *
     * @mbggenerated
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.CO
     *
     * @mbggenerated
     */
    private String cO;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.NO2
     *
     * @mbggenerated
     */
    private String nO2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.O3_1H
     *
     * @mbggenerated
     */
    private String o31H;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.O3_8H
     *
     * @mbggenerated
     */
    private String o38H;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.SO2
     *
     * @mbggenerated
     */
    private String sO2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.PM10_24H
     *
     * @mbggenerated
     */
    private String pM1024H;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.PM2_5_24H
     *
     * @mbggenerated
     */
    private String pM2524H;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.AQI
     *
     * @mbggenerated
     */
    private String aQI;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.PrimaryPollutant
     *
     * @mbggenerated
     */
    private String primaryPollutant;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.SVR
     *
     * @mbggenerated
     */
    private String sVR;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.HNO3
     *
     * @mbggenerated
     */
    private String hNO3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.NH3
     *
     * @mbggenerated
     */
    private String nH3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.NOX
     *
     * @mbggenerated
     */
    private String nOX;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.EC
     *
     * @mbggenerated
     */
    private String eC;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.OC
     *
     * @mbggenerated
     */
    private String oC;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.SO4
     *
     * @mbggenerated
     */
    private String sO4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.NO3
     *
     * @mbggenerated
     */
    private String nO3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.NH4
     *
     * @mbggenerated
     */
    private String nH4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.Soil
     *
     * @mbggenerated
     */
    private String soil;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.OM
     *
     * @mbggenerated
     */
    private String oM;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.RH
     *
     * @mbggenerated
     */
    private String rH;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.Precipitation
     *
     * @mbggenerated
     */
    private String precipitation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.Pressure
     *
     * @mbggenerated
     */
    private String pressure;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.PBL
     *
     * @mbggenerated
     */
    private String pBL;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.CloudCover
     *
     * @mbggenerated
     */
    private String cloudCover;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.Temperature
     *
     * @mbggenerated
     */
    private String temperature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.WindSpeed
     *
     * @mbggenerated
     */
    private String windSpeed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.WindDIR
     *
     * @mbggenerated
     */
    private String windDIR;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.PM10_1H
     *
     * @mbggenerated
     */
    private String pM101H;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.PM2_5_1H
     *
     * @mbggenerated
     */
    private String pM251H;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastModelHour.quality
     *
     * @mbggenerated
     */
    private String quality;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.ID
     *
     * @return the value of forecastModelHour.ID
     *
     * @mbggenerated
     */
    public Integer getiD() {
        return iD;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.ID
     *
     * @param iD the value for forecastModelHour.ID
     *
     * @mbggenerated
     */
    public void setiD(Integer iD) {
        this.iD = iD;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.CityName
     *
     * @return the value of forecastModelHour.CityName
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.CityName
     *
     * @param cityName the value for forecastModelHour.CityName
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.CO
     *
     * @return the value of forecastModelHour.CO
     *
     * @mbggenerated
     */
    public String getcO() {
        return cO;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.CO
     *
     * @param cO the value for forecastModelHour.CO
     *
     * @mbggenerated
     */
    public void setcO(String cO) {
        this.cO = cO == null ? null : cO.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.NO2
     *
     * @return the value of forecastModelHour.NO2
     *
     * @mbggenerated
     */
    public String getnO2() {
        return nO2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.NO2
     *
     * @param nO2 the value for forecastModelHour.NO2
     *
     * @mbggenerated
     */
    public void setnO2(String nO2) {
        this.nO2 = nO2 == null ? null : nO2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.O3_1H
     *
     * @return the value of forecastModelHour.O3_1H
     *
     * @mbggenerated
     */
    public String getO31H() {
        return o31H;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.O3_1H
     *
     * @param o31H the value for forecastModelHour.O3_1H
     *
     * @mbggenerated
     */
    public void setO31H(String o31H) {
        this.o31H = o31H == null ? null : o31H.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.O3_8H
     *
     * @return the value of forecastModelHour.O3_8H
     *
     * @mbggenerated
     */
    public String getO38H() {
        return o38H;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.O3_8H
     *
     * @param o38H the value for forecastModelHour.O3_8H
     *
     * @mbggenerated
     */
    public void setO38H(String o38H) {
        this.o38H = o38H == null ? null : o38H.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.SO2
     *
     * @return the value of forecastModelHour.SO2
     *
     * @mbggenerated
     */
    public String getsO2() {
        return sO2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.SO2
     *
     * @param sO2 the value for forecastModelHour.SO2
     *
     * @mbggenerated
     */
    public void setsO2(String sO2) {
        this.sO2 = sO2 == null ? null : sO2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.PM10_24H
     *
     * @return the value of forecastModelHour.PM10_24H
     *
     * @mbggenerated
     */
    public String getpM1024H() {
        return pM1024H;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.PM10_24H
     *
     * @param pM1024H the value for forecastModelHour.PM10_24H
     *
     * @mbggenerated
     */
    public void setpM1024H(String pM1024H) {
        this.pM1024H = pM1024H == null ? null : pM1024H.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.PM2_5_24H
     *
     * @return the value of forecastModelHour.PM2_5_24H
     *
     * @mbggenerated
     */
    public String getpM2524H() {
        return pM2524H;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.PM2_5_24H
     *
     * @param pM2524H the value for forecastModelHour.PM2_5_24H
     *
     * @mbggenerated
     */
    public void setpM2524H(String pM2524H) {
        this.pM2524H = pM2524H == null ? null : pM2524H.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.AQI
     *
     * @return the value of forecastModelHour.AQI
     *
     * @mbggenerated
     */
    public String getaQI() {
        return aQI;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.AQI
     *
     * @param aQI the value for forecastModelHour.AQI
     *
     * @mbggenerated
     */
    public void setaQI(String aQI) {
        this.aQI = aQI == null ? null : aQI.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.PrimaryPollutant
     *
     * @return the value of forecastModelHour.PrimaryPollutant
     *
     * @mbggenerated
     */
    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.PrimaryPollutant
     *
     * @param primaryPollutant the value for forecastModelHour.PrimaryPollutant
     *
     * @mbggenerated
     */
    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant == null ? null : primaryPollutant.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.SVR
     *
     * @return the value of forecastModelHour.SVR
     *
     * @mbggenerated
     */
    public String getsVR() {
        return sVR;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.SVR
     *
     * @param sVR the value for forecastModelHour.SVR
     *
     * @mbggenerated
     */
    public void setsVR(String sVR) {
        this.sVR = sVR == null ? null : sVR.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.HNO3
     *
     * @return the value of forecastModelHour.HNO3
     *
     * @mbggenerated
     */
    public String gethNO3() {
        return hNO3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.HNO3
     *
     * @param hNO3 the value for forecastModelHour.HNO3
     *
     * @mbggenerated
     */
    public void sethNO3(String hNO3) {
        this.hNO3 = hNO3 == null ? null : hNO3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.NH3
     *
     * @return the value of forecastModelHour.NH3
     *
     * @mbggenerated
     */
    public String getnH3() {
        return nH3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.NH3
     *
     * @param nH3 the value for forecastModelHour.NH3
     *
     * @mbggenerated
     */
    public void setnH3(String nH3) {
        this.nH3 = nH3 == null ? null : nH3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.NOX
     *
     * @return the value of forecastModelHour.NOX
     *
     * @mbggenerated
     */
    public String getnOX() {
        return nOX;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.NOX
     *
     * @param nOX the value for forecastModelHour.NOX
     *
     * @mbggenerated
     */
    public void setnOX(String nOX) {
        this.nOX = nOX == null ? null : nOX.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.EC
     *
     * @return the value of forecastModelHour.EC
     *
     * @mbggenerated
     */
    public String geteC() {
        return eC;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.EC
     *
     * @param eC the value for forecastModelHour.EC
     *
     * @mbggenerated
     */
    public void seteC(String eC) {
        this.eC = eC == null ? null : eC.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.OC
     *
     * @return the value of forecastModelHour.OC
     *
     * @mbggenerated
     */
    public String getoC() {
        return oC;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.OC
     *
     * @param oC the value for forecastModelHour.OC
     *
     * @mbggenerated
     */
    public void setoC(String oC) {
        this.oC = oC == null ? null : oC.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.SO4
     *
     * @return the value of forecastModelHour.SO4
     *
     * @mbggenerated
     */
    public String getsO4() {
        return sO4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.SO4
     *
     * @param sO4 the value for forecastModelHour.SO4
     *
     * @mbggenerated
     */
    public void setsO4(String sO4) {
        this.sO4 = sO4 == null ? null : sO4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.NO3
     *
     * @return the value of forecastModelHour.NO3
     *
     * @mbggenerated
     */
    public String getnO3() {
        return nO3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.NO3
     *
     * @param nO3 the value for forecastModelHour.NO3
     *
     * @mbggenerated
     */
    public void setnO3(String nO3) {
        this.nO3 = nO3 == null ? null : nO3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.NH4
     *
     * @return the value of forecastModelHour.NH4
     *
     * @mbggenerated
     */
    public String getnH4() {
        return nH4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.NH4
     *
     * @param nH4 the value for forecastModelHour.NH4
     *
     * @mbggenerated
     */
    public void setnH4(String nH4) {
        this.nH4 = nH4 == null ? null : nH4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.Soil
     *
     * @return the value of forecastModelHour.Soil
     *
     * @mbggenerated
     */
    public String getSoil() {
        return soil;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.Soil
     *
     * @param soil the value for forecastModelHour.Soil
     *
     * @mbggenerated
     */
    public void setSoil(String soil) {
        this.soil = soil == null ? null : soil.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.OM
     *
     * @return the value of forecastModelHour.OM
     *
     * @mbggenerated
     */
    public String getoM() {
        return oM;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.OM
     *
     * @param oM the value for forecastModelHour.OM
     *
     * @mbggenerated
     */
    public void setoM(String oM) {
        this.oM = oM == null ? null : oM.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.RH
     *
     * @return the value of forecastModelHour.RH
     *
     * @mbggenerated
     */
    public String getrH() {
        return rH;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.RH
     *
     * @param rH the value for forecastModelHour.RH
     *
     * @mbggenerated
     */
    public void setrH(String rH) {
        this.rH = rH == null ? null : rH.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.Precipitation
     *
     * @return the value of forecastModelHour.Precipitation
     *
     * @mbggenerated
     */
    public String getPrecipitation() {
        return precipitation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.Precipitation
     *
     * @param precipitation the value for forecastModelHour.Precipitation
     *
     * @mbggenerated
     */
    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation == null ? null : precipitation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.Pressure
     *
     * @return the value of forecastModelHour.Pressure
     *
     * @mbggenerated
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.Pressure
     *
     * @param pressure the value for forecastModelHour.Pressure
     *
     * @mbggenerated
     */
    public void setPressure(String pressure) {
        this.pressure = pressure == null ? null : pressure.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.PBL
     *
     * @return the value of forecastModelHour.PBL
     *
     * @mbggenerated
     */
    public String getpBL() {
        return pBL;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.PBL
     *
     * @param pBL the value for forecastModelHour.PBL
     *
     * @mbggenerated
     */
    public void setpBL(String pBL) {
        this.pBL = pBL == null ? null : pBL.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.CloudCover
     *
     * @return the value of forecastModelHour.CloudCover
     *
     * @mbggenerated
     */
    public String getCloudCover() {
        return cloudCover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.CloudCover
     *
     * @param cloudCover the value for forecastModelHour.CloudCover
     *
     * @mbggenerated
     */
    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover == null ? null : cloudCover.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.Temperature
     *
     * @return the value of forecastModelHour.Temperature
     *
     * @mbggenerated
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.Temperature
     *
     * @param temperature the value for forecastModelHour.Temperature
     *
     * @mbggenerated
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.WindSpeed
     *
     * @return the value of forecastModelHour.WindSpeed
     *
     * @mbggenerated
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.WindSpeed
     *
     * @param windSpeed the value for forecastModelHour.WindSpeed
     *
     * @mbggenerated
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed == null ? null : windSpeed.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.WindDIR
     *
     * @return the value of forecastModelHour.WindDIR
     *
     * @mbggenerated
     */
    public String getWindDIR() {
        return windDIR;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.WindDIR
     *
     * @param windDIR the value for forecastModelHour.WindDIR
     *
     * @mbggenerated
     */
    public void setWindDIR(String windDIR) {
        this.windDIR = windDIR == null ? null : windDIR.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.PM10_1H
     *
     * @return the value of forecastModelHour.PM10_1H
     *
     * @mbggenerated
     */
    public String getpM101H() {
        return pM101H;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.PM10_1H
     *
     * @param pM101H the value for forecastModelHour.PM10_1H
     *
     * @mbggenerated
     */
    public void setpM101H(String pM101H) {
        this.pM101H = pM101H == null ? null : pM101H.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.PM2_5_1H
     *
     * @return the value of forecastModelHour.PM2_5_1H
     *
     * @mbggenerated
     */
    public String getpM251H() {
        return pM251H;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.PM2_5_1H
     *
     * @param pM251H the value for forecastModelHour.PM2_5_1H
     *
     * @mbggenerated
     */
    public void setpM251H(String pM251H) {
        this.pM251H = pM251H == null ? null : pM251H.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastModelHour.quality
     *
     * @return the value of forecastModelHour.quality
     *
     * @mbggenerated
     */
    public String getQuality() {
        return quality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastModelHour.quality
     *
     * @param quality the value for forecastModelHour.quality
     *
     * @mbggenerated
     */
    public void setQuality(String quality) {
        this.quality = quality == null ? null : quality.trim();
    }
}