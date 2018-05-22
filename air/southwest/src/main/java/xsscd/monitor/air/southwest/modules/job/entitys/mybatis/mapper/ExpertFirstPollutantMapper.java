package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertFirstPollutant;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface ExpertFirstPollutantMapper {
	public List<ExpertFirstPollutant> select(ExpertFirstPollutant expertFirstPollutantView);

	public void insert(ExpertFirstPollutant expertFirstPollutantView);

	public void insertSelective(ExpertFirstPollutant expertFirstPollutantView);

	public List<ExpertFirstPollutant> selectByPrimaryKey(ExpertFirstPollutant expertFirstPollutantView);

	public void saveBatch(List<ExpertFirstPollutant> beans);

	public void updateByPrimaryKeySelective(ExpertFirstPollutant expertFirstPollutantView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}