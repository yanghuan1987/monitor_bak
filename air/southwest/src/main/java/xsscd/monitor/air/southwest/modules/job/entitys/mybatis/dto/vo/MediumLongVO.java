package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MediumLongVO {
	private static final long serialVersionUID = 1L;

    private Integer iDST;

    private Integer iDED;
    //站点编码
    private String stationCode;
    //选择时间
    private Date reportTime;
    //开始时间
    private Date dateST;
    //结束时间
    private Date dateED;
    //参数
    private String param;
    //城市
    private String city;
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
    @JsonFormat(pattern="yyyy-MM-dd")
	public Date getReportTime() {
		return reportTime;
	}
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
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
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    
}