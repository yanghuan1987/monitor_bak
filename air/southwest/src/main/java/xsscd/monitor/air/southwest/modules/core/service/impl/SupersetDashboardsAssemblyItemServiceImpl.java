package xsscd.monitor.air.southwest.modules.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.SupersetDashboardsAssemblyItem;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.SupersetDashboardsAssemblyItemMapper;
import xsscd.monitor.air.southwest.modules.core.service.SupersetDashboardsAssemblyItemService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class SupersetDashboardsAssemblyItemServiceImpl implements SupersetDashboardsAssemblyItemService {
    @Autowired
    private SupersetDashboardsAssemblyItemMapper mapper;
    @Override
    @EasymisDataSource(DataSourceType.Master)
    public List<SupersetDashboardsAssemblyItem> select(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView) {
        return mapper.select(supersetDashboardsAssemblyItemView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void insert(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView) {
        mapper.insert(supersetDashboardsAssemblyItemView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void insertSelective(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView) {
        mapper.insertSelective(supersetDashboardsAssemblyItemView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public List<SupersetDashboardsAssemblyItem> selectByPrimaryKey(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView) {
        return mapper.selectByPrimaryKey(supersetDashboardsAssemblyItemView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void updateByPrimaryKeySelective(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView) {
        mapper.updateByPrimaryKeySelective(supersetDashboardsAssemblyItemView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
