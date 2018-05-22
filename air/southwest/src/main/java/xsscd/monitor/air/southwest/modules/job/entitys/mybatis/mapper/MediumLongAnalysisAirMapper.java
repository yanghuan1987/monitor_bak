package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisAir;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface MediumLongAnalysisAirMapper {
	public List<MediumLongAnalysisAir> select(MediumLongAnalysisAir mediumLongAnalysisAirView);

	public List<MediumLongAnalysisAir> selectExport(MediumLongVO mediumLongVO);

	public void insert(MediumLongAnalysisAir mediumLongAnalysisAirView);

	public void insertSelective(MediumLongAnalysisAir mediumLongAnalysisAirView);

	public List<MediumLongAnalysisAir> selectByPrimaryKey(MediumLongAnalysisAir mediumLongAnalysisAirView);

	public void saveBatch(List<MediumLongAnalysisAir> beans);

	public void updateByPrimaryKeySelective(MediumLongAnalysisAir mediumLongAnalysisAirView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}