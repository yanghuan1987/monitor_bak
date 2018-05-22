package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.List;

public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaName;
    private List<TimeType> timeList;//评估汇总统计数据

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<TimeType> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<TimeType> timeList) {
        this.timeList = timeList;
    }
}