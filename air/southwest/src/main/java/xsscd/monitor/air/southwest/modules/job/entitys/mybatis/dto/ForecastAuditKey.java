package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto;

import java.util.Date;

public class ForecastAuditKey {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastAudit.timepoint
     *
     * @mbggenerated
     */
    private Date timepoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column forecastAudit.citycode
     *
     * @mbggenerated
     */
    private Integer citycode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastAudit.timepoint
     *
     * @return the value of forecastAudit.timepoint
     *
     * @mbggenerated
     */
    public Date getTimepoint() {
        return timepoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastAudit.timepoint
     *
     * @param timepoint the value for forecastAudit.timepoint
     *
     * @mbggenerated
     */
    public void setTimepoint(Date timepoint) {
        this.timepoint = timepoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column forecastAudit.citycode
     *
     * @return the value of forecastAudit.citycode
     *
     * @mbggenerated
     */
    public Integer getCitycode() {
        return citycode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column forecastAudit.citycode
     *
     * @param citycode the value for forecastAudit.citycode
     *
     * @mbggenerated
     */
    public void setCitycode(Integer citycode) {
        this.citycode = citycode;
    }
}