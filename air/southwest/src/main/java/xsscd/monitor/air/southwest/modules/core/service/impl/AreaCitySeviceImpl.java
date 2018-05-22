package xsscd.monitor.air.southwest.modules.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaCity;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.AreaCityMapper;
import xsscd.monitor.air.southwest.modules.core.service.AreaCitySevice;

import java.util.List;

@Service
public class AreaCitySeviceImpl implements AreaCitySevice {
	@Autowired
	AreaCityMapper mapper;
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public AreaCity getList(AreaCity areaCity) {
		return mapper.getList(areaCity);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public List<AreaCity> getLists(AreaCity areaCity) {
		return mapper.getLists(areaCity);
	}
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void save(AreaCity bean) {
		mapper.save(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void update(AreaCity bean) {
		mapper.update(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void delete(Integer areaCityCode) {
		mapper.delete(areaCityCode);
	}

}
