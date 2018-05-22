package xsscd.monitor.air.southwest.modules.core.service;

import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ProvinceReportDto;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ProvinceReportVo;

import java.util.List;

public interface ProvinceReportSevice {
	public ProvinceReportDto getList(ProvinceReportVo province);

	public List<ProvinceReportDto> getLists(ProvinceReportVo province);

	public void save(ProvinceReportDto bean);

	public void update(ProvinceReportDto bean);

	public void delete(Integer provinceCode);


}
