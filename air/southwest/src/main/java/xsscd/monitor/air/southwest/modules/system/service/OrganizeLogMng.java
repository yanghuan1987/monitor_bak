package xsscd.monitor.air.southwest.modules.system.service;

import java.util.List;
import java.util.Map;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeLog;

public interface OrganizeLogMng {
	public List<OrganizeLog> getList(Map<String, Object> params);

	public void save(OrganizeLog sysLog);

	public void saveBatch(List<OrganizeLog> beans);

	public void update(OrganizeLog bean);

	public void delete(String staffId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public OrganizeLog findById(String staffId);

	public OrganizeLog findByStaffId(String staffId);

	public OrganizeLog findByStaffAndOrganize(String staffId, String organizeId);

	public List<OrganizeLog> findByIds(List<String> list);
}
