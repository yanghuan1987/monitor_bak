package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisPollutant;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface MediumLongAnalysisPollutantMapper {
	public List<MediumLongAnalysisPollutant> select(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView);
	
	public List<MediumLongAnalysisPollutant> selectExport(MediumLongVO mediumLongVO);

	public void insert(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView);

	public void insertSelective(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView);

	public List<MediumLongAnalysisPollutant> selectByPrimaryKey(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView);

	public void saveBatch(List<MediumLongAnalysisPollutant> beans);

	public void updateByPrimaryKeySelective(MediumLongAnalysisPollutant mediumLongAnalysisPollutantView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}