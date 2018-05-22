package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertAirLvlCount;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

public interface ExpertAirLvlCountService {
	public List<ExpertAirLvlCount> select(ExpertAirLvlCount expertAirLvlCountView);

	public void insert(ExpertAirLvlCount expertAirLvlCountView);

	public void insertSelective(ExpertAirLvlCount expertAirLvlCountView);

	public List<ExpertAirLvlCount> selectByPrimaryKey(ExpertAirLvlCount expertAirLvlCountView);

	public void saveBatch(List<ExpertAirLvlCount> beans);

	public void updateByPrimaryKeySelective(ExpertAirLvlCount expertAirLvlCountView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}