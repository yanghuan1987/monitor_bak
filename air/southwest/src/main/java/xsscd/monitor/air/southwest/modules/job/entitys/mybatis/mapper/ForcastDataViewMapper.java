package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface ForcastDataViewMapper {
	public List<ForcastDataView> select(ForcastDataView forcastDataView);

	public List<ForcastDataView> selectExport(MediumLongVO mediumLongVO);
	
	public void insert(ForcastDataView forcastDataView);

	public void insertSelective(ForcastDataView forcastDataView);

	public List<ForcastDataView> selectByPrimaryKey(SelectPiontVO forcastDataView);

	public void saveBatch(List<ForcastDataView> beans);

	public void updateByPrimaryKeySelective(ForcastDataView forcastDataView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}