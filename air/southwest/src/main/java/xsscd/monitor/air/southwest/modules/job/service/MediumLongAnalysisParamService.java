package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisParam;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;

import java.util.List;

public interface MediumLongAnalysisParamService {
	public List<MediumLongAnalysisParam> select(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public List<MediumLongAnalysisParam> selectExport(MediumLongVO mediumLongVO);

	public void insert(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public void insertSelective(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public List<MediumLongAnalysisParam> selectByPrimaryKey(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public void saveBatch(List<MediumLongAnalysisParam> beans);

	public void updateByPrimaryKeySelective(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public void deleteByPrimaryKey(Long id);

}