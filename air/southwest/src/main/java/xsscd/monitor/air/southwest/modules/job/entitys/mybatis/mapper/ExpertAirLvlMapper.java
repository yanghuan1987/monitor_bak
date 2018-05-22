package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertAirLvl;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface ExpertAirLvlMapper {
	public List<ExpertAirLvl> select(ExpertAirLvl expertAirLvlView);

	public void insert(ExpertAirLvl expertAirLvlView);

	public void insertSelective(ExpertAirLvl expertAirLvlView);

	public List<ExpertAirLvl> selectByPrimaryKey(ExpertAirLvl expertAirLvlView);

	public void saveBatch(List<ExpertAirLvl> beans);

	public void updateByPrimaryKeySelective(ExpertAirLvl expertAirLvlView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}