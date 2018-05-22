package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;

import java.util.List;

@Repository
public interface AreaProvinceMapper {
	public AreaProvince getList(AreaProvince areaProvince);

	public List<AreaProvince> getLists(AreaProvince areaProvince);

	public void save(AreaProvince bean);

	public void update(AreaProvince bean);

	public void delete(Integer areaProvinceCode);

}