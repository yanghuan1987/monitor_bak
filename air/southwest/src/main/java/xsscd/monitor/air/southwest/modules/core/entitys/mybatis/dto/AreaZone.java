package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.List;

public class AreaZone implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long zid;
    //区域名称
    private String zoneName;
    //区域ID
    private Integer zoneCode;
    //区域简称
    private String zoneJC;
    //所在省
    private Integer provinceCode;
    //排序
    private Integer orderP;
    //区域包含城市
    private List<AreaCity> zoneCity;
    //区域包含城市预测数据
    private List<ForcastData> forcastDataList;

    //预测结果
    private List<AirQualityLvlForecastStatistics> airQualityLvlForecastStatisticsList;
    public Long getZid() {
        return zid;
    }

    public List<AirQualityLvlForecastStatistics> getAirQualityLvlForecastStatisticsList() {
        return airQualityLvlForecastStatisticsList;
    }

    public void setAirQualityLvlForecastStatisticsList(List<AirQualityLvlForecastStatistics> airQualityLvlForecastStatisticsList) {
        this.airQualityLvlForecastStatisticsList = airQualityLvlForecastStatisticsList;
    }
    public void setZid(Long zid) {
        this.zid = zid;
    }

    public List<AreaCity> getZoneCity() {
        return zoneCity;
    }

    public void setZoneCity(List<AreaCity> zoneCity) {
        this.zoneCity = zoneCity;
    }


    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(Integer zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneJC() {
        return zoneJC;
    }

    public void setZoneJC(String zoneJC) {
        this.zoneJC = zoneJC;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getOrderP() {
        return orderP;
    }

    public void setOrderP(Integer orderP) {
        this.orderP = orderP;
    }

    public List<ForcastData> getForcastDataList() {
        return forcastDataList;
    }

    public void setForcastDataList(List<ForcastData> forcastDataList) {
        this.forcastDataList = forcastDataList;
    }
}