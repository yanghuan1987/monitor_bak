package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaCity;

import java.util.List;

@Repository
public interface AreaCityMapper {
	public AreaCity getList(AreaCity areaCity);

	public List<AreaCity> getLists(AreaCity areaCity);

	public void save(AreaCity bean);

	public void update(AreaCity bean);

	public void delete(Integer areaCityCode);

}