package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.AQIStationDayDataPublishHistory;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.AQIStationDayDataPublishHistoryMapper;
import xsscd.monitor.air.southwest.modules.job.service.AQIStationDayDataPublishHistoryService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class AQIStationDayDataPublishHistoryServiceImpl implements AQIStationDayDataPublishHistoryService {
    @Autowired
    private AQIStationDayDataPublishHistoryMapper mapper;
    @Override
    public AQIStationDayDataPublishHistory select(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView) {
        return mapper.select(aQIStationDayDataPublishHistoryView);
    }

    @Override
    public void insert(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView) {
        mapper.insert(aQIStationDayDataPublishHistoryView);
    }

    @Override
    public void insertSelective(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView) {
        mapper.insertSelective(aQIStationDayDataPublishHistoryView);
    }

    @Override
    public List<AQIStationDayDataPublishHistory> selectByPrimaryKey(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView) {
        return mapper.selectByPrimaryKey(aQIStationDayDataPublishHistoryView);
    }

    @Override
    public void updateByPrimaryKeySelective(AQIStationDayDataPublishHistory aQIStationDayDataPublishHistoryView) {
        mapper.updateByPrimaryKeySelective(aQIStationDayDataPublishHistoryView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
