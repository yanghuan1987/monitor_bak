package xsscd.monitor.air.southwest.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeRole;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper.OrganizeRoleMapper;
import xsscd.monitor.air.southwest.modules.system.service.OrganizeRoleService;
@Service
public class OrganizeRoleServiceImpl implements OrganizeRoleService {
	@Autowired
	OrganizeRoleMapper mapper;
	@Override
	public List<OrganizeRole> getList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.getList(params);
	}

	@Override
	public void save(OrganizeRole bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveBatch(List<OrganizeRole> beans) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(OrganizeRole bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String staffId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBatch(List<String> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void restoreBatch(List<String> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBatch(List<String> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrganizeRole findById(String staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizeRole findByMemberId(String staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizeRole findByMemberAndOrganize(String memberId, String organizeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganizeRole> findByIds(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveBbatchImport(String fileName, MultipartFile mfile) {
		// TODO Auto-generated method stub
		return null;
	}

}
