package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaStation;

import java.util.List;

@Repository
public interface AreaStationMapper {
	public AreaStation getList(AreaStation areaStation);

	public List<AreaStation> getLists(AreaStation areaStation);

	public void save(AreaStation bean);

	public void update(AreaStation bean);

	public void delete(Integer areaStationCode);

}