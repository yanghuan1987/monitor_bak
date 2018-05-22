package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.PollutantAnalysis;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.PollutantAnalysisMapper;
import xsscd.monitor.air.southwest.modules.job.service.PollutantAnalysisService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class PollutantAnalysisServiceImpl implements PollutantAnalysisService {
    @Autowired
    private PollutantAnalysisMapper mapper;
    @Override
    public List<PollutantAnalysis> select(PollutantAnalysis pollutantAnalysisView) {
        return mapper.select(pollutantAnalysisView);
    }

    @Override
    public void insert(PollutantAnalysis pollutantAnalysisView) {
        mapper.insert(pollutantAnalysisView);
    }

    @Override
    public void insertSelective(PollutantAnalysis pollutantAnalysisView) {
        mapper.insertSelective(pollutantAnalysisView);
    }

    @Override
    public List<PollutantAnalysis> selectByPrimaryKey(PollutantAnalysis pollutantAnalysisView) {
        return mapper.selectByPrimaryKey(pollutantAnalysisView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<PollutantAnalysis> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(PollutantAnalysis pollutantAnalysisView) {
        mapper.updateByPrimaryKeySelective(pollutantAnalysisView);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO selectPiontVO) {
        mapper.deleteByPrimaryKey(selectPiontVO);
    }
}
