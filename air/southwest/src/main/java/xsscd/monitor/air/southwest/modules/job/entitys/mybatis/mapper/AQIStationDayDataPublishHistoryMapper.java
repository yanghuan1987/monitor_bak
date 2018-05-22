package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.AQIStationDayDataPublishHistory;

import java.util.List;

@Repository
public interface AQIStationDayDataPublishHistoryMapper {
	public AQIStationDayDataPublishHistory select(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public void insert(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public void insertSelective(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public List<AQIStationDayDataPublishHistory> selectByPrimaryKey(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public void saveBatch(List<AQIStationDayDataPublishHistory> beans);

	public void updateByPrimaryKeySelective(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView);

	public void deleteByPrimaryKey(Long id);

}