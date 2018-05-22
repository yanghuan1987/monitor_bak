package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

public interface ExpertForcastDataViewService {
	public List<ExpertForcastDataView> select(ExpertForcastDataView expertForcastDataViewView);

	public void insert(ExpertForcastDataView expertForcastDataViewView);

	public void insertSelective(ExpertForcastDataView expertForcastDataViewView);

	public List<ExpertForcastDataView> selectByPrimaryKey(SelectPiontVO expertForcastDataViewView);

	public void saveBatch(List<ExpertForcastDataView> beans);

	public void updateByPrimaryKeySelective(ExpertForcastDataView expertForcastDataViewView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}