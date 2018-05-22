package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.AQIStationDayDataPublishHistory;

import java.util.List;

public interface AQIStationDayDataPublishHistoryService {
	public AQIStationDayDataPublishHistory select(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public void insert(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public void insertSelective(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public List<AQIStationDayDataPublishHistory> selectByPrimaryKey(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public void updateByPrimaryKeySelective(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public void deleteByPrimaryKey(Long id);

}