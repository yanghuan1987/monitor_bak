package xsscd.monitor.air.southwest.modules.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.SupersetDashboardsAssembly;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.SupersetDashboardsAssemblyMapper;
import xsscd.monitor.air.southwest.modules.core.service.SupersetDashboardsAssemblyService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class SupersetDashboardsAssemblyServiceImpl implements SupersetDashboardsAssemblyService {
    @Autowired
    private SupersetDashboardsAssemblyMapper mapper;
    @Override
    @EasymisDataSource(DataSourceType.Master)
    public List<SupersetDashboardsAssembly> select(SupersetDashboardsAssembly supersetDashboardsAssemblyView) {
        return mapper.select(supersetDashboardsAssemblyView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void insert(SupersetDashboardsAssembly supersetDashboardsAssemblyView) {
        mapper.insert(supersetDashboardsAssemblyView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void insertSelective(SupersetDashboardsAssembly supersetDashboardsAssemblyView) {
        mapper.insertSelective(supersetDashboardsAssemblyView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public List<SupersetDashboardsAssembly> selectByPrimaryKey(Long dashboards_id) {
        return mapper.selectByPrimaryKey(dashboards_id);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void updateByPrimaryKeySelective(SupersetDashboardsAssembly supersetDashboardsAssemblyView) {
        mapper.updateByPrimaryKeySelective(supersetDashboardsAssemblyView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
