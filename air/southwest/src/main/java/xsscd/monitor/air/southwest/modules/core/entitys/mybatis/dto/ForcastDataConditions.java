package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ForcastDataConditions implements Serializable {

    private static final long serialVersionUID = 1L;

    //省ID
    private List<String> aid;
    //城市编码
    private String cityCode;
    //预测日期
    private Date forecastDate;
    //需要查询的参数
    private List<String> gas;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<String> getAid() {
        return aid;
    }

    public void setAid(List<String> aid) {
        this.aid = aid;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public List<String> getGas() {
        return gas;
    }

    public void setGas(List<String> gas) {
        this.gas = gas;
    }
}