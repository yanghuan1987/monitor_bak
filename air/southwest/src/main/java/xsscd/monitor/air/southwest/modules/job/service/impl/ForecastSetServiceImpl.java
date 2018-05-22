package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastSet;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ForecastSetMapper;
import xsscd.monitor.air.southwest.modules.job.service.ForecastSetService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ForecastSetServiceImpl implements ForecastSetService {
    @Autowired
    private ForecastSetMapper mapper;
    @Override
    public List<ForecastSet> select(ForecastSet forecastSetView) {
        return mapper.select(forecastSetView);
    }

    @Override
    public void insert(ForecastSet forecastSetView) {
        mapper.insert(forecastSetView);
    }

    @Override
    public void insertSelective(ForecastSet forecastSetView) {
        mapper.insertSelective(forecastSetView);
    }

    @Override
    public List<ForecastSet> selectByPrimaryKey(ForecastSet forecastSetView) {
        return mapper.selectByPrimaryKey(forecastSetView);
    }

    @Override
    public void updateByPrimaryKeySelective(ForecastSet forecastSetView) {
        mapper.updateByPrimaryKeySelective(forecastSetView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
