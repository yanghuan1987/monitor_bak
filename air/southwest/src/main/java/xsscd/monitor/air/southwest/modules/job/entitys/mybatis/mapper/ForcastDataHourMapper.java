package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface ForcastDataHourMapper {
	public List<ForcastDataHour> select(ForcastDataHour forcastDataHour);

	public void insert(ForcastDataHour forcastDataHour);

	public void insertSelective(ForcastDataHour forcastDataHour);

	public List<ForcastDataHour> selectByPrimaryKey(SelectPiontVO forcastDataHour);

	public void saveBatch(List<ForcastDataHour> beans);

	public void updateByPrimaryKeySelective(ForcastDataHour forcastDataHour);

	public void deleteByPrimaryKey(SelectPiontVO forcastDataHour);

}