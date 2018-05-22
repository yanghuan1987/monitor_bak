package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertFirstPollutant;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ExpertFirstPollutantMapper;
import xsscd.monitor.air.southwest.modules.job.service.ExpertFirstPollutantService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ExpertFirstPollutantServiceImpl implements ExpertFirstPollutantService {
    @Autowired
    private ExpertFirstPollutantMapper mapper;
    @Override
    public List<ExpertFirstPollutant> select(ExpertFirstPollutant expertFirstPollutantView) {
        return mapper.select(expertFirstPollutantView);
    }

    @Override
    public void insert(ExpertFirstPollutant expertFirstPollutantView) {
        mapper.insert(expertFirstPollutantView);
    }

    @Override
    public void insertSelective(ExpertFirstPollutant expertFirstPollutantView) {
        mapper.insertSelective(expertFirstPollutantView);
    }

    @Override
    public List<ExpertFirstPollutant> selectByPrimaryKey(ExpertFirstPollutant expertFirstPollutantView) {
        return mapper.selectByPrimaryKey(expertFirstPollutantView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<ExpertFirstPollutant> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(ExpertFirstPollutant expertFirstPollutantView) {
        mapper.updateByPrimaryKeySelective(expertFirstPollutantView);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO selectPiontVO) {
        mapper.deleteByPrimaryKey(selectPiontVO);
    }
}
