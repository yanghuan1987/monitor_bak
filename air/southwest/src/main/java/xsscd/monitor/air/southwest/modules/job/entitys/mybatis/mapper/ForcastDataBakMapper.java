package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataBak;

import java.util.List;

@Repository
public interface ForcastDataBakMapper {
	public List<ForcastDataBak> select(ForcastDataBak forcastDataBakView);

	public void insert(ForcastDataBak forcastDataBakView);

	public void insertSelective(ForcastDataBak forcastDataBakView);

	public List<ForcastDataBak> selectByPrimaryKey(ForcastDataBak forcastDataBakView);

	public void saveBatch(List<ForcastDataBak> beans);

	public void updateByPrimaryKeySelective(ForcastDataBak forcastDataBakView);

	public void deleteByPrimaryKey(Long id);

}