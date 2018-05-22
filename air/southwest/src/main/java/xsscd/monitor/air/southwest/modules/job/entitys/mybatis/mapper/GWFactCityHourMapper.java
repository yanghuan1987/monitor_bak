package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.GWFactCityHour;

import java.util.List;

@Repository
public interface GWFactCityHourMapper {
	public GWFactCityHour select(GWFactCityHour gWFactCityHourView);

	public void insert(GWFactCityHour gWFactCityHourView);

	public void insertSelective(GWFactCityHour gWFactCityHourView);

	public List<GWFactCityHour> selectByPrimaryKey(GWFactCityHour gWFactCityHourView);

	public void updateByPrimaryKeySelective(GWFactCityHour gWFactCityHourView);

	public void deleteByPrimaryKey(Long id);

}