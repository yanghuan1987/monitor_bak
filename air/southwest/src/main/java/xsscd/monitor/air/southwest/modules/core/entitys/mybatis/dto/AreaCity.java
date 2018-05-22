package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.List;

public class AreaCity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cid;
    //城市编码
    private Integer cityCode;
    //城市名称
    private String cityName;
    //区域ID
    private Long zoneId;
    //省编码
    private Integer provinceCode;
    //城市简称
    private String cityJC;
    //排序
    private Integer orderP;

    private List<AreaStation> stations;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityJC() {
        return cityJC;
    }

    public void setCityJC(String cityJC) {
        this.cityJC = cityJC;
    }

    public Integer getOrderP() {
        return orderP;
    }

    public void setOrderP(Integer orderP) {
        this.orderP = orderP;
    }

    public List<AreaStation> getStations() {
        return stations;
    }

    public void setStations(List<AreaStation> stations) {
        this.stations = stations;
    }
}