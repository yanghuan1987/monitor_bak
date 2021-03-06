package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

public class AreaStation {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area_station.sid
     *
     * @mbggenerated
     */
    private Long sid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area_station.StationCode
     *
     * @mbggenerated
     */
    private String stationCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area_station.StationName
     *
     * @mbggenerated
     */
    private String stationName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area_station.CityName
     *
     * @mbggenerated
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area_station.CityCode
     *
     * @mbggenerated
     */
    private String cityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area_station.OrderP
     *
     * @mbggenerated
     */
    private Integer orderP;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area_station.sid
     *
     * @return the value of area_station.sid
     *
     * @mbggenerated
     */
    public Long getSid() {
        return sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area_station.sid
     *
     * @param sid the value for area_station.sid
     *
     * @mbggenerated
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area_station.StationCode
     *
     * @return the value of area_station.StationCode
     *
     * @mbggenerated
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area_station.StationCode
     *
     * @param stationCode the value for area_station.StationCode
     *
     * @mbggenerated
     */
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area_station.StationName
     *
     * @return the value of area_station.StationName
     *
     * @mbggenerated
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area_station.StationName
     *
     * @param stationName the value for area_station.StationName
     *
     * @mbggenerated
     */
    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area_station.CityName
     *
     * @return the value of area_station.CityName
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area_station.CityName
     *
     * @param cityName the value for area_station.CityName
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area_station.CityCode
     *
     * @return the value of area_station.CityCode
     *
     * @mbggenerated
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area_station.CityCode
     *
     * @param cityCode the value for area_station.CityCode
     *
     * @mbggenerated
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area_station.OrderP
     *
     * @return the value of area_station.OrderP
     *
     * @mbggenerated
     */
    public Integer getOrderP() {
        return orderP;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area_station.OrderP
     *
     * @param orderP the value for area_station.OrderP
     *
     * @mbggenerated
     */
    public void setOrderP(Integer orderP) {
        this.orderP = orderP;
    }
}