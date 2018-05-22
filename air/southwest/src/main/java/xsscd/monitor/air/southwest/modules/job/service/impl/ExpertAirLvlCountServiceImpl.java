package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertAirLvlCount;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ExpertAirLvlCountMapper;
import xsscd.monitor.air.southwest.modules.job.service.ExpertAirLvlCountService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ExpertAirLvlCountServiceImpl implements ExpertAirLvlCountService {
    @Autowired
    private ExpertAirLvlCountMapper mapper;
    @Override
    public List<ExpertAirLvlCount> select(ExpertAirLvlCount expertAirLvlCountView) {
        return mapper.select(expertAirLvlCountView);
    }

    @Override
    public void insert(ExpertAirLvlCount expertAirLvlCountView) {
        mapper.insert(expertAirLvlCountView);
    }

    @Override
    public void insertSelective(ExpertAirLvlCount expertAirLvlCountView) {
        mapper.insertSelective(expertAirLvlCountView);
    }

    @Override
    public List<ExpertAirLvlCount> selectByPrimaryKey(ExpertAirLvlCount expertAirLvlCountView) {
        return mapper.selectByPrimaryKey(expertAirLvlCountView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<ExpertAirLvlCount> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(ExpertAirLvlCount expertAirLvlCountView) {
        mapper.updateByPrimaryKeySelective(expertAirLvlCountView);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO selectPiontVO) {
        mapper.deleteByPrimaryKey(selectPiontVO);
    }
}
