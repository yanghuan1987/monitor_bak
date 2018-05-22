package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeFunction;

public interface OrganizeFunctionMapper {
	public List<OrganizeFunction> getList(HashMap<String, Object> params);

	public void save(OrganizeFunction bean);

	public void saveBatch(List<OrganizeFunction> beans);

	public void update(OrganizeFunction bean);

	public void delete(String functionId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public OrganizeFunction findById(String functionId);

	public List<OrganizeFunction> findByIds(List<String> list);

	public List<OrganizeFunction> findByStaffId(String staffId);
}