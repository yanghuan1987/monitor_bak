package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo;

import java.util.Date;

public class SelectVO {
	private static final long serialVersionUID = 1L;

    private Integer iDST;

    private Integer iDED;
    //站点编码
    private String stationCode;
    //开始时间
    private Date dateST;
    //结束时间
    private Date dateED;
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

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
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
}