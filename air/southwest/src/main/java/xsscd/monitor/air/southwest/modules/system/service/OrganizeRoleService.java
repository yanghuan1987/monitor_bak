package xsscd.monitor.air.southwest.modules.system.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeRole;

public interface OrganizeRoleService {
	public List<OrganizeRole> getList(HashMap<String, Object> params);

	public void save(OrganizeRole bean);

	public void saveBatch(List<OrganizeRole> beans);

	public void update(OrganizeRole bean);

	public void delete(String staffId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public OrganizeRole findById(String roleId);

	public OrganizeRole findByMemberId(String staffId);

	public OrganizeRole findByMemberAndOrganize(String memberId, String organizeId);

	public List<OrganizeRole> findByIds(List<String> list);

	public String saveBbatchImport(String fileName, MultipartFile mfile);
}
