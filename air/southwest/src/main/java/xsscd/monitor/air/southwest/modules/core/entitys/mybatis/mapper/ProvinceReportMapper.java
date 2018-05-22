package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ProvinceReportDto;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ProvinceReportVo;

import java.util.List;

@Repository
public interface ProvinceReportMapper {
	public ProvinceReportDto getList(ProvinceReportVo province);

	public List<ProvinceReportDto> getLists(ProvinceReportVo province);

	public void save(ProvinceReportDto bean);

	public void update(ProvinceReportDto bean);

	public void delete(Integer provinceCode);

}