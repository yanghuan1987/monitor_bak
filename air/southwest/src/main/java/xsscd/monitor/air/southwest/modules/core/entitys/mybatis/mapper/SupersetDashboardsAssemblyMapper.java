package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.SupersetDashboardsAssembly;

import java.util.List;

@Repository
public interface SupersetDashboardsAssemblyMapper {
	public List<SupersetDashboardsAssembly> select(SupersetDashboardsAssembly supersetDashboardsAssemblyView);

	public void insert(SupersetDashboardsAssembly supersetDashboardsAssemblyView);

	public void insertSelective(SupersetDashboardsAssembly supersetDashboardsAssemblyView);

	public List<SupersetDashboardsAssembly> selectByPrimaryKey(Long dashboards_id);

	public void updateByPrimaryKeySelective(SupersetDashboardsAssembly supersetDashboardsAssemblyView);

	public void deleteByPrimaryKey(Long id);

}