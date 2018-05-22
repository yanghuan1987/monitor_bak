package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelHour;

import java.util.List;

public interface ForecastModelHourService {
	public List<ForecastModelHour> select(ForecastModelHour forecastModelHour);

	public void insert(ForecastModelHour forecastModelHour);

	public void insertSelective(ForecastModelHour forecastModelHour);

	public List<ForecastModelHour> selectByPrimaryKey(ForecastModelHour forecastModelHour);

	public void updateByPrimaryKeySelective(ForecastModelHour forecastModelHour);

	public void deleteByPrimaryKey(Long id);

}