package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeRole;

public interface OrganizeRoleMapper {
	public List<OrganizeRole> getList(HashMap<String, Object> params);

	public void save(OrganizeRole bean);

	public void saveBatch(List<OrganizeRole> beans);

	public void update(OrganizeRole bean);

	public void delete(String roleId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public OrganizeRole findById(String roleId);

	public List<OrganizeRole> findByIds(List<String> list);

	public List<OrganizeRole> findByMemberId(String memberId);
	public List<OrganizeRole> getList(String memberId);
}