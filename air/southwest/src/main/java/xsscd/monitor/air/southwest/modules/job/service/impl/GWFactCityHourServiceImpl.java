package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.GWFactCityHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.GWFactCityHourMapper;
import xsscd.monitor.air.southwest.modules.job.service.GWFactCityHourService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class GWFactCityHourServiceImpl implements GWFactCityHourService {
    @Autowired
    private GWFactCityHourMapper mapper;
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public GWFactCityHour select(GWFactCityHour gWFactCityHourView) {
        return mapper.select(gWFactCityHourView);
    }

    @Override
    public void insert(GWFactCityHour gWFactCityHourView) {
        mapper.insert(gWFactCityHourView);
    }

    @Override
    public void insertSelective(GWFactCityHour gWFactCityHourView) {
        mapper.insertSelective(gWFactCityHourView);
    }

    @Override
    public List<GWFactCityHour> selectByPrimaryKey(GWFactCityHour gWFactCityHourView) {
        return mapper.selectByPrimaryKey(gWFactCityHourView);
    }

    @Override
    public void updateByPrimaryKeySelective(GWFactCityHour gWFactCityHourView) {
        mapper.updateByPrimaryKeySelective(gWFactCityHourView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
