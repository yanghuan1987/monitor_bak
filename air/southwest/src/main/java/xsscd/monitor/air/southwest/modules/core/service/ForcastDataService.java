package xsscd.monitor.air.southwest.modules.core.service;

import java.util.List;
import java.util.Map;

import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ForcastDataConditions;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public interface ForcastDataService {
    public  List<Map<String, Object>>  getForcastAudateCity(String provinces,String timePoint,String[] gas,int day);

    public  Map<String ,Object>  getForcastAudateCityDay(String provinces,String timePoint);

    public  List<AreaProvince>  getForcastAudateCity1(ForcastDataConditions conditions);

}
