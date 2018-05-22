package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.List;

public class TimeType1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String time;
    private List<Province> provinceList;//评估汇总统计数据

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Province> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }
}