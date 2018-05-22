package xsscd.monitor.air.southwest.modules.core.service;

import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.SupersetDashboardsAssemblyItem;

import java.util.List;

public interface SupersetDashboardsAssemblyItemService {
	public List<SupersetDashboardsAssemblyItem> select(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView);

	public void insert(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView);

	public void insertSelective(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView);

	public List<SupersetDashboardsAssemblyItem> selectByPrimaryKey(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView);

	public void updateByPrimaryKeySelective(SupersetDashboardsAssemblyItem supersetDashboardsAssemblyItemView);

	public void deleteByPrimaryKey(Long id);

}