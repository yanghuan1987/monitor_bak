package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastAudit;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ForecastAuditMapper;
import xsscd.monitor.air.southwest.modules.job.service.ForecastAuditService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ForecastAuditServiceImpl implements ForecastAuditService {
    @Autowired
    private ForecastAuditMapper mapper;
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public List<ForecastAudit> select(ForecastAudit forecastAuditView) {
        return mapper.select(forecastAuditView);
    }

    @Override
    public void insert(ForecastAudit forecastAuditView) {
        mapper.insert(forecastAuditView);
    }

    @Override
    public void insertSelective(ForecastAudit forecastAuditView) {
        mapper.insertSelective(forecastAuditView);
    }

    @Override
    public List<ForecastAudit> selectByPrimaryKey(ForecastAudit forecastAuditView) {
        return mapper.selectByPrimaryKey(forecastAuditView);
    }

    @Override
    public void updateByPrimaryKeySelective(ForecastAudit forecastAuditView) {
        mapper.updateByPrimaryKeySelective(forecastAuditView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
