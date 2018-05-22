package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.List;

public class TimeType implements Serializable {

    private static final long serialVersionUID = 1L;

    private String time;
    private List<EvaluationSummaryData> dataList;//评估汇总统计数据

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<EvaluationSummaryData> getDataList() {
        return dataList;
    }

    public void setDataList(List<EvaluationSummaryData> dataList) {
        this.dataList = dataList;
    }
}