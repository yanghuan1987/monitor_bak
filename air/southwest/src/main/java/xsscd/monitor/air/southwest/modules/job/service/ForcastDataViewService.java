package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

public interface ForcastDataViewService {
	public List<ForcastDataView> select(ForcastDataView forcastDataViewView);

	public List<ForcastDataView> selectExport(MediumLongVO mediumLongVO);

	public void insert(ForcastDataView forcastDataViewView);

	public void insertSelective(ForcastDataView forcastDataViewView);

	public List<ForcastDataView> selectByPrimaryKey(SelectPiontVO selectPiontVO);

	public void saveBatch(List<ForcastDataView> beans);

	public void updateByPrimaryKeySelective(ForcastDataView forcastDataViewView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}