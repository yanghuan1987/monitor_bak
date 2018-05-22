package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisAir;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.MediumLongAnalysisAirMapper;
import xsscd.monitor.air.southwest.modules.job.service.MediumLongAnalysisAirService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class MediumLongAnalysisAirServiceImpl implements MediumLongAnalysisAirService {
    @Autowired
    private MediumLongAnalysisAirMapper mapper;
    @Override
    public List<MediumLongAnalysisAir> select(MediumLongAnalysisAir mediumLongAnalysisAirView) {
        return mapper.select(mediumLongAnalysisAirView);
    }

    @Override
	public List<MediumLongAnalysisAir> selectExport(MediumLongVO mediumLongVO) {
		return mapper.selectExport(mediumLongVO);
	}

	@Override
    public void insert(MediumLongAnalysisAir mediumLongAnalysisAirView) {
        mapper.insert(mediumLongAnalysisAirView);
    }

    @Override
    public void insertSelective(MediumLongAnalysisAir mediumLongAnalysisAirView) {
        mapper.insertSelective(mediumLongAnalysisAirView);
    }

    @Override
    public List<MediumLongAnalysisAir> selectByPrimaryKey(MediumLongAnalysisAir mediumLongAnalysisAirView) {
        return mapper.selectByPrimaryKey(mediumLongAnalysisAirView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<MediumLongAnalysisAir> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(MediumLongAnalysisAir mediumLongAnalysisAirView) {
        mapper.updateByPrimaryKeySelective(mediumLongAnalysisAirView);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO selectPiontVO) {
        mapper.deleteByPrimaryKey(selectPiontVO);
    }
}
