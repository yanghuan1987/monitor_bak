package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisParam;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;

import java.util.List;

@Repository
public interface MediumLongAnalysisParamMapper {
	public List<MediumLongAnalysisParam> select(MediumLongAnalysisParam mediumLongAnalysisParamView);
	
	public List<MediumLongAnalysisParam> selectExport(MediumLongVO mediumLongVO);

	public void insert(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public void insertSelective(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public List<MediumLongAnalysisParam> selectByPrimaryKey(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public boolean saveBatch(List<MediumLongAnalysisParam> beans);

	public void updateByPrimaryKeySelective(MediumLongAnalysisParam mediumLongAnalysisParamView);

	public void deleteByPrimaryKey(Long id);

}