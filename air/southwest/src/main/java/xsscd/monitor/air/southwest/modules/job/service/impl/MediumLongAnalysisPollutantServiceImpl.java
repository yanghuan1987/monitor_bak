package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisPollutant;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.MediumLongAnalysisPollutantMapper;
import xsscd.monitor.air.southwest.modules.job.service.MediumLongAnalysisPollutantService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class MediumLongAnalysisPollutantServiceImpl implements MediumLongAnalysisPollutantService {
    @Autowired
    private MediumLongAnalysisPollutantMapper mapper;
    @Override
    public List<MediumLongAnalysisPollutant> select(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView) {
        return mapper.select(mediumLongAnalysisPollutantView);
    }

    @Override
	public List<MediumLongAnalysisPollutant> selectExport(MediumLongVO mediumLongVO) {
		return mapper.selectExport(mediumLongVO);
	}

	@Override
    public void insert(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView) {
        mapper.insert(mediumLongAnalysisPollutantView);
    }

    @Override
    public void insertSelective(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView) {
        mapper.insertSelective(mediumLongAnalysisPollutantView);
    }

    @Override
    public List<MediumLongAnalysisPollutant> selectByPrimaryKey(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView) {
        return mapper.selectByPrimaryKey(mediumLongAnalysisPollutantView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<MediumLongAnalysisPollutant> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView) {
        mapper.updateByPrimaryKeySelective(mediumLongAnalysisPollutantView);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO selectPiontVO) {
        mapper.deleteByPrimaryKey(selectPiontVO);
    }
}
