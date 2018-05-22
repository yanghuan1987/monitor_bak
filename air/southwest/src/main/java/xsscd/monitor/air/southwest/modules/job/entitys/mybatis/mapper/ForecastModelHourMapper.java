package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelHour;

import java.util.List;

@Repository
public interface ForecastModelHourMapper {
	public List<ForecastModelHour> select(ForecastModelHour forecastModelHour);

	public void insert(ForecastModelHour forecastModelHour);

	public void insertSelective(ForecastModelHour forecastModelHour);

	public List<ForecastModelHour> selectByPrimaryKey(ForecastModelHour forecastModelHour);

	public void updateByPrimaryKeySelective(ForecastModelHour forecastModelHour);

	public void deleteByPrimaryKey(Long id);

}