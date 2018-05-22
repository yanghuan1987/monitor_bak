package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastAudit;

import java.util.List;

public interface ForecastAuditService {
	public List<ForecastAudit> select(ForecastAudit forecastAudit);

	public void insert(ForecastAudit forecastAudit);

	public void insertSelective(ForecastAudit forecastAudit);

	public List<ForecastAudit> selectByPrimaryKey(ForecastAudit forecastAudit);

	public void updateByPrimaryKeySelective(ForecastAudit forecastAudit);

	public void deleteByPrimaryKey(Long id);

}