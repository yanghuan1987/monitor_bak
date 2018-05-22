package xsscd.monitor.air.southwest.modules.core.service;

import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.SupersetDashboardsAssembly;

import java.util.List;

public interface SupersetDashboardsAssemblyService {
	public List<SupersetDashboardsAssembly> select(SupersetDashboardsAssembly supersetDashboardsAssemblyView);

	public void insert(SupersetDashboardsAssembly supersetDashboardsAssemblyView);

	public void insertSelective(SupersetDashboardsAssembly supersetDashboardsAssemblyView);

	public List<SupersetDashboardsAssembly> selectByPrimaryKey(Long dashboards_id);

	public void updateByPrimaryKeySelective(SupersetDashboardsAssembly supersetDashboardsAssemblyView);

	public void deleteByPrimaryKey(Long id);

}