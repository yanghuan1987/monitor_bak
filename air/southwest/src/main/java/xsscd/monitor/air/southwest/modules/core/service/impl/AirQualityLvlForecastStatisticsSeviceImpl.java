package xsscd.monitor.air.southwest.modules.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AirQualityLvlForecastStatistics;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.AirQualityLvlForecastStatisticsMapper;
import xsscd.monitor.air.southwest.modules.core.service.AirQualityLvlForecastStatisticsSevice;

import java.util.Date;
import java.util.List;

@Service
public class AirQualityLvlForecastStatisticsSeviceImpl implements AirQualityLvlForecastStatisticsSevice {
	@Autowired
	AirQualityLvlForecastStatisticsMapper mapper;
	@Override
	@EasymisDataSource(DataSourceType.Master)
	public List<AreaProvince> getList(Date date) {
		return mapper.getList(date);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void save(AirQualityLvlForecastStatistics bean) {
		mapper.save(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void saveBatch(List<AirQualityLvlForecastStatistics> beans) {
		mapper.saveBatch(beans);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void update(AirQualityLvlForecastStatistics bean) {
		mapper.update(bean);
	}

	@Override
	@EasymisDataSource(DataSourceType.Master)
	public void delete(Date date) {
		mapper.delete(date);
	}

	@Override
	public void removeBatch(List<String> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restoreBatch(List<String> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBatch(List<String> list) {
		// TODO Auto-generated method stub
		
	}
}
