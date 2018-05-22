package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo;

import java.util.Date;

public class SelectPiontVO {
	private static final long serialVersionUID = 1L;

    private Integer iDST;

    private Integer iDED;
    //开始时间
    private Date dateST;
    //结束时间
    private Date dateED;


    //开始时间-副职
    private Date dateSTTmp;
    //结束时间-副职
    private Date dateEDTmp;

    public Date getDateSTTmp() {
        return dateSTTmp;
    }

    public void setDateSTTmp(Date dateSTTmp) {
        this.dateSTTmp = dateSTTmp;
    }

    public Date getDateEDTmp() {
        return dateEDTmp;
    }

    public void setDateEDTmp(Date dateEDTmp) {
        this.dateEDTmp = dateEDTmp;
    }
    private String cityCode;

    private String city;

    private String station;
    //日均，小时
    private String forcastType;
    private Integer forcastDay;
    //污染物
    private String pollutant;
    //模式
    private String model;

    public Integer getiDST() {
        return iDST;
    }

    public void setiDST(Integer iDST) {
        this.iDST = iDST;
    }

    public Integer getiDED() {
        return iDED;
    }

    public void setiDED(Integer iDED) {
        this.iDED = iDED;
    }

    public Date getDateST() {
        return dateST;
    }

    public void setDateST(Date dateST) {
        this.dateST = dateST;
    }

    public Date getDateED() {
        return dateED;
    }

    public void setDateED(Date dateED) {
        this.dateED = dateED;
    }

    public String getPollutant() {
        return pollutant;
    }

    public void setPollutant(String pollutant) {
        this.pollutant = pollutant;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getForcastDay() {
        return forcastDay;
    }

    public void setForcastDay(Integer forcastDay) {
        this.forcastDay = forcastDay;
    }

    public String getForcastType() {
        return forcastType;
    }

    public void setForcastType(String forcastType) {
        this.forcastType = forcastType;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}