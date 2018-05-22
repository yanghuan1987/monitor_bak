package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AirQualityLvlForecastStatistics;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;

import java.util.Date;
import java.util.List;

@Repository
public interface AirQualityLvlForecastStatisticsMapper {
	public List<AreaProvince> getList(Date date);

	public void save(AirQualityLvlForecastStatistics bean);

	public void saveBatch(List<AirQualityLvlForecastStatistics> beans);

	public void update(AirQualityLvlForecastStatistics bean);

	public void delete(Date date);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

}