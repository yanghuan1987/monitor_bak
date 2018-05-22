package xsscd.monitor.air.southwest.modules.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaZone;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.AreaZoneMapper;
import xsscd.monitor.air.southwest.modules.core.service.AreaZoneSevice;

import java.util.List;

@Service
public class AreaZoneSeviceImpl implements AreaZoneSevice {
	@Autowired
	AreaZoneMapper mapper;
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public AreaZone getList(AreaZone areaZone) {
		return mapper.getList(areaZone);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public List<AreaZone> getLists(AreaZone areaZone) {
		return mapper.getLists(areaZone);
	}
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void save(AreaZone bean) {
		mapper.save(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void update(AreaZone bean) {
		mapper.update(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void delete(Integer areaZoneCode) {
		mapper.delete(areaZoneCode);
	}

}
