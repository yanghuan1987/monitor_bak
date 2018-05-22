package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.PollutantAnalysis;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface PollutantAnalysisMapper {
	public List<PollutantAnalysis> select(PollutantAnalysis pollutantAnalysisView);

	public void insert(PollutantAnalysis pollutantAnalysisView);

	public void insertSelective(PollutantAnalysis pollutantAnalysisView);

	public List<PollutantAnalysis> selectByPrimaryKey(PollutantAnalysis pollutantAnalysisView);

	public void saveBatch(List<PollutantAnalysis> beans);

	public void updateByPrimaryKeySelective(PollutantAnalysis pollutantAnalysisView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}