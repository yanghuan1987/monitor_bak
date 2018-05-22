package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.GWFactCityDay;

import java.util.List;

public interface GWFactCityDayService {
	public GWFactCityDay select(GWFactCityDay gWFactCityDayView);

	public void insert(GWFactCityDay gWFactCityDayView);

	public void insertSelective(GWFactCityDay gWFactCityDayView);

	public List<GWFactCityDay> selectByPrimaryKey(GWFactCityDay gWFactCityDayView);

	public void updateByPrimaryKeySelective(GWFactCityDay gWFactCityDayView);

	public void deleteByPrimaryKey(Long id);

}