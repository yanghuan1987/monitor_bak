package xsscd.monitor.air.southwest.modules.core.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
public interface ForcastModleService {
    public List<Map<String, Object>> getCityForcastModle(String startTime, String endTime, String city, String station, String gas, String forcastType, int day);
}
