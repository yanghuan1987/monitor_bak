package xsscd.monitor.air.southwest.modules.core.service;

import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public interface CityDataService {


    @EasymisDataSource(DataSourceType.Slave)
    Object getCity();

    @EasymisDataSource(DataSourceType.Slave)
    Object getStation();

    @EasymisDataSource(DataSourceType.Slave)
    Map<String,Object> getCityStation();

    //获取面板实时数据（小时）
    @EasymisDataSource(DataSourceType.Slave)
    Object getPanelData(String cityname);

    @EasymisDataSource(DataSourceType.Slave)
    Object getAllCityAQIData1(Date time, int type, int timeType);

    @EasymisDataSource(DataSourceType.Slave)
    Object getAllCityAQIData(Date time, int type, int timeType);

    @EasymisDataSource(DataSourceType.Slave)
    Object getModelReal(Date start, Date end, String cityName, String gasAll, int timeType, String stationName, int type) throws ParseException;
}
