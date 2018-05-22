package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ForecastModelHourMapper;
import xsscd.monitor.air.southwest.modules.job.service.ForecastModelHourService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ForecastModelHourServiceImpl implements ForecastModelHourService {
    @Autowired
    private ForecastModelHourMapper mapper;
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public List<ForecastModelHour> select(ForecastModelHour forecastModelHourView) {
        return mapper.select(forecastModelHourView);
    }

    @Override
    public void insert(ForecastModelHour forecastModelHourView) {
        mapper.insert(forecastModelHourView);
    }

    @Override
    public void insertSelective(ForecastModelHour forecastModelHourView) {
        mapper.insertSelective(forecastModelHourView);
    }

    @Override
    public List<ForecastModelHour> selectByPrimaryKey(ForecastModelHour forecastModelHourView) {
        return mapper.selectByPrimaryKey(forecastModelHourView);
    }

    @Override
    public void updateByPrimaryKeySelective(ForecastModelHour forecastModelHourView) {
        mapper.updateByPrimaryKeySelective(forecastModelHourView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
