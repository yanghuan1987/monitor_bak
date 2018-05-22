package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelDay;

import java.util.List;

@Repository
public interface ForecastModelDayMapper {
	public List<ForecastModelDay> select(ForecastModelDay forecastModelDay);

	public void insert(ForecastModelDay forecastModelDay);

	public void insertSelective(ForecastModelDay forecastModelDay);

	public List<ForecastModelDay> selectByPrimaryKey(ForecastModelDay forecastModelDay);

	public void updateByPrimaryKeySelective(ForecastModelDay forecastModelDay);

	public void deleteByPrimaryKey(Long id);

}