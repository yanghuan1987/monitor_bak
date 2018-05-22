package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelDay;

import java.util.List;

public interface ForecastModelDayService {
	public List<ForecastModelDay> select(ForecastModelDay forecastModelDay);

	public void insert(ForecastModelDay forecastModelDay);

	public void insertSelective(ForecastModelDay forecastModelDay);

	public List<ForecastModelDay> selectByPrimaryKey(ForecastModelDay forecastModelDay);

	public void updateByPrimaryKeySelective(ForecastModelDay forecastModelDay);

	public void deleteByPrimaryKey(Long id);

}