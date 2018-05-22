package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastAudit;

import java.util.List;

@Repository
public interface ForecastAuditMapper {
	public List<ForecastAudit> select(ForecastAudit forecastAudit);

	public void insert(ForecastAudit forecastAudit);

	public void insertSelective(ForecastAudit forecastAudit);

	public List<ForecastAudit> selectByPrimaryKey(ForecastAudit forecastAudit);

	public void updateByPrimaryKeySelective(ForecastAudit forecastAudit);

	public void deleteByPrimaryKey(Long id);

}