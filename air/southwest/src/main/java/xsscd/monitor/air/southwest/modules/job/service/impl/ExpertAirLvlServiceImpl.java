package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertAirLvl;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ExpertAirLvlMapper;
import xsscd.monitor.air.southwest.modules.job.service.ExpertAirLvlService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ExpertAirLvlServiceImpl implements ExpertAirLvlService {
    @Autowired
    private ExpertAirLvlMapper mapper;
    @Override
    public List<ExpertAirLvl> select(ExpertAirLvl expertAirLvlView) {
        return mapper.select(expertAirLvlView);
    }

    @Override
    public void insert(ExpertAirLvl expertAirLvlView) {
        mapper.insert(expertAirLvlView);
    }

    @Override
    public void insertSelective(ExpertAirLvl expertAirLvlView) {
        mapper.insertSelective(expertAirLvlView);
    }

    @Override
    public List<ExpertAirLvl> selectByPrimaryKey(ExpertAirLvl expertAirLvlView) {
        return mapper.selectByPrimaryKey(expertAirLvlView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<ExpertAirLvl> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(ExpertAirLvl expertAirLvlView) {
        mapper.updateByPrimaryKeySelective(expertAirLvlView);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO selectPiontVO) {
        mapper.deleteByPrimaryKey(selectPiontVO);
    }
}
