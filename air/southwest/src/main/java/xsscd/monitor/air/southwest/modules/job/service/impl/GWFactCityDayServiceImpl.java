package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.GWFactCityDay;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.GWFactCityDayMapper;
import xsscd.monitor.air.southwest.modules.job.service.GWFactCityDayService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class GWFactCityDayServiceImpl implements GWFactCityDayService {
    @Autowired
    private GWFactCityDayMapper mapper;
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public GWFactCityDay select(GWFactCityDay gWFactCityDayView) {
        return mapper.select(gWFactCityDayView);
    }

    @Override
    public void insert(GWFactCityDay gWFactCityDayView) {
        mapper.insert(gWFactCityDayView);
    }

    @Override
    public void insertSelective(GWFactCityDay gWFactCityDayView) {
        mapper.insertSelective(gWFactCityDayView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public List<GWFactCityDay> selectByPrimaryKey(GWFactCityDay gWFactCityDayView) {
        return mapper.selectByPrimaryKey(gWFactCityDayView);
    }

    @Override
    public void updateByPrimaryKeySelective(GWFactCityDay gWFactCityDayView) {
        mapper.updateByPrimaryKeySelective(gWFactCityDayView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
