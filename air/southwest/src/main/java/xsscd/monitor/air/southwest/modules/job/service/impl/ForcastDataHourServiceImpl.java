package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ForcastDataHourMapper;
import xsscd.monitor.air.southwest.modules.job.service.ForcastDataHourService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ForcastDataHourServiceImpl implements ForcastDataHourService {
    @Autowired
    private ForcastDataHourMapper mapper;
    @Override
    public List<ForcastDataHour> select(ForcastDataHour forcastDataHour) {
        return mapper.select(forcastDataHour);
    }

    @Override
    public void insert(ForcastDataHour forcastDataHour) {
        mapper.insert(forcastDataHour);
    }

    @Override
    public void insertSelective(ForcastDataHour forcastDataHour) {
        mapper.insertSelective(forcastDataHour);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public List<ForcastDataHour> selectByPrimaryKey(SelectPiontVO forcastDataHour) {
        return mapper.selectByPrimaryKey(forcastDataHour);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<ForcastDataHour> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(ForcastDataHour forcastDataHour) {
        mapper.updateByPrimaryKeySelective(forcastDataHour);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO forcastDataHour) {
        mapper.deleteByPrimaryKey(forcastDataHour);
    }
}
