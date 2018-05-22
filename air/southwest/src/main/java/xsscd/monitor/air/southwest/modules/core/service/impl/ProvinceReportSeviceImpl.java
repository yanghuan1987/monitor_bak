package xsscd.monitor.air.southwest.modules.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ProvinceReportDto;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ProvinceReportVo;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.ProvinceReportMapper;
import xsscd.monitor.air.southwest.modules.core.service.ProvinceReportSevice;

import java.util.List;

@Service
public class ProvinceReportSeviceImpl implements ProvinceReportSevice {
	@Autowired
	ProvinceReportMapper mapper;
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public ProvinceReportDto getList(ProvinceReportVo province) {
		return mapper.getList(province);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public List<ProvinceReportDto> getLists(ProvinceReportVo province) {
		return mapper.getLists(province);
	}
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void save(ProvinceReportDto bean) {
		mapper.save(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void update(ProvinceReportDto bean) {
		mapper.update(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void delete(Integer provinceCode) {
		mapper.delete(provinceCode);
	}

}
