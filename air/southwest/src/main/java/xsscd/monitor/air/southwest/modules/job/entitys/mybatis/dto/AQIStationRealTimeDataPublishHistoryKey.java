package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto;

import java.util.Date;

public class AQIStationRealTimeDataPublishHistoryKey {
	private static final long serialVersionUID = 1L;

    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AQIStationRealTimeDataPublishHistory.TimePoint
     *
     * @mbggenerated
     */
    private Date timePoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AQIStationRealTimeDataPublishHistory.StationCode
     *
     * @mbggenerated
     */
    private String stationCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AQIStationRealTimeDataPublishHistory.TimePoint
     *
     * @return the value of AQIStationRealTimeDataPublishHistory.TimePoint
     *
     * @mbggenerated
     */
    public Date getTimePoint() {
        return timePoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AQIStationRealTimeDataPublishHistory.TimePoint
     *
     * @param timePoint the value for AQIStationRealTimeDataPublishHistory.TimePoint
     *
     * @mbggenerated
     */
    public void setTimePoint(Date timePoint) {
        this.timePoint = timePoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AQIStationRealTimeDataPublishHistory.StationCode
     *
     * @return the value of AQIStationRealTimeDataPublishHistory.StationCode
     *
     * @mbggenerated
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AQIStationRealTimeDataPublishHistory.StationCode
     *
     * @param stationCode the value for AQIStationRealTimeDataPublishHistory.StationCode
     *
     * @mbggenerated
     */
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}