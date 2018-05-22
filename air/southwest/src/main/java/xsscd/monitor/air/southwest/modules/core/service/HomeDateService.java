package xsscd.monitor.air.southwest.modules.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public interface HomeDateService {
    public  List<Map<String, Object>>  getAllCityRealData(Date time, int type);
    public  List<Map<String, Object>>  getAllCityRealNewData(int type);
    public  List<Map<String, Object>>  getAllCityForcastData(Date time, int type);
    List<Map<String, Object>>  getBouncedData(String cityName);


}
