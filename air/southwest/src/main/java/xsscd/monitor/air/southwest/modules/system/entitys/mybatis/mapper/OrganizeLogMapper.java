package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeLog;

public interface OrganizeLogMapper {
	public List<OrganizeLog> getList(HashMap<String, Object> params);

	public void save(OrganizeLog bean);

	public void saveBatch(List<OrganizeLog> beans);

	public void update(OrganizeLog bean);

	public void delete(String logId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public OrganizeLog findById(String logId);

	public List<OrganizeLog> findByIds(List<String> list);
}