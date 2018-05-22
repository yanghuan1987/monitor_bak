package xsscd.monitor.air.southwest.modules.core.service;

import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaStation;

import java.util.List;

public interface AreaStationSevice {
	public AreaStation getList(AreaStation areaStation);

	public List<AreaStation> getLists(AreaStation areaStation);

	public void save(AreaStation bean);

	public void update(AreaStation bean);

	public void delete(Integer areaStationCode);


}
