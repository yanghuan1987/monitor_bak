package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastSet;

import java.util.List;

public interface ForecastSetService {
	public List<ForecastSet> select(ForecastSet forecastSet);

	public void insert(ForecastSet forecastSet);

	public void insertSelective(ForecastSet forecastSet);

	public List<ForecastSet> selectByPrimaryKey(ForecastSet forecastSet);

	public void updateByPrimaryKeySelective(ForecastSet forecastSet);

	public void deleteByPrimaryKey(Long id);

}