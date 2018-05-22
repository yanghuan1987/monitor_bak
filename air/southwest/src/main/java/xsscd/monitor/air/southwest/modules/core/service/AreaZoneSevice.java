package xsscd.monitor.air.southwest.modules.core.service;

import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaZone;

import java.util.List;

public interface AreaZoneSevice {
	public AreaZone getList(AreaZone areaZone);

	public List<AreaZone> getLists(AreaZone areaZone);

	public void save(AreaZone bean);

	public void update(AreaZone bean);

	public void delete(Integer areaZoneCode);


}
