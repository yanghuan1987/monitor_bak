package xsscd.monitor.air.southwest.modules.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaStation;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.AreaStationMapper;
import xsscd.monitor.air.southwest.modules.core.service.AreaStationSevice;

import java.util.List;

@Service
public class AreaStationSeviceImpl implements AreaStationSevice {
	@Autowired
	AreaStationMapper mapper;
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public AreaStation getList(AreaStation areaStation) {
		return mapper.getList(areaStation);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public List<AreaStation> getLists(AreaStation areaStation) {
		return mapper.getLists(areaStation);
	}
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void save(AreaStation bean) {
		mapper.save(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void update(AreaStation bean) {
		mapper.update(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void delete(Integer areaStationCode) {
		mapper.delete(areaStationCode);
	}

}
