package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface ExpertForcastDataViewMapper {
	public List<ExpertForcastDataView> select(ExpertForcastDataView expertForcastDataView);

	public void insert(ExpertForcastDataView expertForcastDataView);

	public void insertSelective(ExpertForcastDataView expertForcastDataView);

	public List<ExpertForcastDataView> selectByPrimaryKey(SelectPiontVO expertForcastDataView);

	public void saveBatch(List<ExpertForcastDataView> beans);

	public void updateByPrimaryKeySelective(ExpertForcastDataView expertForcastDataView);

	public void deleteByPrimaryKey(SelectPiontVO selectPiontVO);

}