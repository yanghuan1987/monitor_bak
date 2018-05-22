package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisParam;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.MediumLongAnalysisParamMapper;
import xsscd.monitor.air.southwest.modules.job.service.MediumLongAnalysisParamService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class MediumLongAnalysisParamServiceImpl implements MediumLongAnalysisParamService {
    @Autowired
    private MediumLongAnalysisParamMapper mapper;
    @Override
    public List<MediumLongAnalysisParam> select(MediumLongAnalysisParam mediumLongAnalysisParamView) {
        return mapper.select(mediumLongAnalysisParamView);
    }

    @Override
	public List<MediumLongAnalysisParam> selectExport(MediumLongVO mediumLongVO) {
		return mapper.selectExport(mediumLongVO);
	}

	@Override
    public void insert(MediumLongAnalysisParam mediumLongAnalysisParamView) {
        mapper.insert(mediumLongAnalysisParamView);
    }

    @Override
    public void insertSelective(MediumLongAnalysisParam mediumLongAnalysisParamView) {
        mapper.insertSelective(mediumLongAnalysisParamView);
    }

    @Override
    public List<MediumLongAnalysisParam> selectByPrimaryKey(MediumLongAnalysisParam mediumLongAnalysisParamView) {
        return mapper.selectByPrimaryKey(mediumLongAnalysisParamView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<MediumLongAnalysisParam> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(MediumLongAnalysisParam mediumLongAnalysisParamView) {
        mapper.updateByPrimaryKeySelective(mediumLongAnalysisParamView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
