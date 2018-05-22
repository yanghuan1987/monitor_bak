package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.List;

public class Area1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaName;
    private List<EvaluationSummaryData> dataList;//评估汇总统计数据

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<EvaluationSummaryData> getDataList() {
        return dataList;
    }

    public void setDataList(List<EvaluationSummaryData> dataList) {
        this.dataList = dataList;
    }
}