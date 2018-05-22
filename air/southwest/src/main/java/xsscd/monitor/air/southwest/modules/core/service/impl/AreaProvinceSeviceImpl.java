package xsscd.monitor.air.southwest.modules.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.AreaProvinceMapper;
import xsscd.monitor.air.southwest.modules.core.service.AreaProvinceSevice;

import java.util.List;

@Service
public class AreaProvinceSeviceImpl implements AreaProvinceSevice {
	@Autowired
	AreaProvinceMapper mapper;
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public AreaProvince getList(AreaProvince areaProvince) {
		return mapper.getList(areaProvince);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public List<AreaProvince> getLists(AreaProvince areaProvince) {
		return mapper.getLists(areaProvince);
	}
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void save(AreaProvince bean) {
		mapper.save(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void update(AreaProvince bean) {
		mapper.update(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void delete(Integer areaProvinceCode) {
		mapper.delete(areaProvinceCode);
	}

}
