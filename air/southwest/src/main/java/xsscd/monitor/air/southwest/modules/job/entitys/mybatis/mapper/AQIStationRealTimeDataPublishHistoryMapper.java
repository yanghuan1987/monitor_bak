package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.AQIStationRealTimeDataPublishHistory;

import java.util.List;

@Repository
public interface AQIStationRealTimeDataPublishHistoryMapper {
	public AQIStationRealTimeDataPublishHistory select(AQIStationRealTimeDataPublishHistory View);

	public void insert(AQIStationRealTimeDataPublishHistory View);

	public void insertSelective(AQIStationRealTimeDataPublishHistory View);

	public List<AQIStationRealTimeDataPublishHistory> selectByPrimaryKey(AQIStationRealTimeDataPublishHistory View);

	public void updateByPrimaryKeySelective(AQIStationRealTimeDataPublishHistory View);

	public void deleteByPrimaryKey(Long id);

}