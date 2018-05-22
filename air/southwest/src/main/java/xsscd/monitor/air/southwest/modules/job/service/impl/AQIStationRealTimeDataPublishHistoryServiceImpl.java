package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.AQIStationRealTimeDataPublishHistory;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.AQIStationRealTimeDataPublishHistoryMapper;
import xsscd.monitor.air.southwest.modules.job.service.AQIStationRealTimeDataPublishHistoryService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class AQIStationRealTimeDataPublishHistoryServiceImpl implements AQIStationRealTimeDataPublishHistoryService {
    @Autowired
    private AQIStationRealTimeDataPublishHistoryMapper mapper;
    @Override
    public AQIStationRealTimeDataPublishHistory select(AQIStationRealTimeDataPublishHistory View) {
        return mapper.select(View);
    }

    @Override
    public void insert(AQIStationRealTimeDataPublishHistory View) {
        mapper.insert(View);
    }

    @Override
    public void insertSelective(AQIStationRealTimeDataPublishHistory View) {
        mapper.insertSelective(View);
    }

    @Override
    public List<AQIStationRealTimeDataPublishHistory> selectByPrimaryKey(AQIStationRealTimeDataPublishHistory View) {
        return mapper.selectByPrimaryKey(View);
    }

    @Override
    public void updateByPrimaryKeySelective(AQIStationRealTimeDataPublishHistory View) {
        mapper.updateByPrimaryKeySelective(View);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
