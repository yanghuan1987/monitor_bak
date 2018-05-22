package xsscd.monitor.air.southwest.modules.core.service;

import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;

import java.util.List;

public interface AreaProvinceSevice {
	public AreaProvince getList(AreaProvince areaProvince);

	public List<AreaProvince> getLists(AreaProvince areaProvince);

	public void save(AreaProvince bean);

	public void update(AreaProvince bean);

	public void delete(Integer areaProvinceCode);


}
