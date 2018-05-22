package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

public interface ForcastDataHourService {
	public List<ForcastDataHour> select(ForcastDataHour forcastDataHour);

	public void insert(ForcastDataHour forcastDataHour);

	public void insertSelective(ForcastDataHour forcastDataHour);

	public List<ForcastDataHour> selectByPrimaryKey(SelectPiontVO forcastDataHour);

	public void saveBatch(List<ForcastDataHour> beans);

	public void updateByPrimaryKeySelective(ForcastDataHour forcastDataHour);

	public void deleteByPrimaryKey(SelectPiontVO forcastDataHour);

}