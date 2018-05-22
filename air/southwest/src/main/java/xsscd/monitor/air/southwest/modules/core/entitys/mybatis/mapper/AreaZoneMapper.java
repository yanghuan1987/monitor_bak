package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaZone;

import java.util.List;

@Repository
public interface AreaZoneMapper {
	public AreaZone getList(AreaZone areaZone);

	public List<AreaZone> getLists(AreaZone areaZone);

	public void save(AreaZone bean);

	public void update(AreaZone bean);

	public void delete(Integer areaZoneCode);

}