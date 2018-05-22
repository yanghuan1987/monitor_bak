package xsscd.monitor.air.southwest.modules.core.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.ForcastAssessmentMapper;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.ForcastModleMapper;
import xsscd.monitor.air.southwest.modules.core.service.ForcastModleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
@Service
public class ForcastModleServiceImpl implements ForcastModleService {
    @Autowired
    ForcastModleMapper mapper;

    @Override
    public  List<Map<String, Object>>  getCityForcastModle( String startTime,String endTime,String city,String station,String gas,String forcastType,int day) {
        List<Map<String, Object>> data=mapper.getCityForcastModle( startTime, endTime, city, station,day);//获取对应时间数据

        return data;
    }







}
