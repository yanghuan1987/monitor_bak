package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelDay;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ForecastModelDayMapper;
import xsscd.monitor.air.southwest.modules.job.service.ForecastModelDayService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ForecastModelDayServiceImpl implements ForecastModelDayService {
    @Autowired
    private ForecastModelDayMapper mapper;
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public List<ForecastModelDay> select(ForecastModelDay forecastModelDayView) {
        return mapper.select(forecastModelDayView);
    }

    @Override
    public void insert(ForecastModelDay forecastModelDayView) {
        mapper.insert(forecastModelDayView);
    }

    @Override
    public void insertSelective(ForecastModelDay forecastModelDayView) {
        mapper.insertSelective(forecastModelDayView);
    }

    @Override
    public List<ForecastModelDay> selectByPrimaryKey(ForecastModelDay forecastModelDayView) {
        return mapper.selectByPrimaryKey(forecastModelDayView);
    }

    @Override
    public void updateByPrimaryKeySelective(ForecastModelDay forecastModelDayView) {
        mapper.updateByPrimaryKeySelective(forecastModelDayView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
