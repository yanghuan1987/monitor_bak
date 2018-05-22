package xsscd.monitor.air.southwest.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeLog;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper.OrganizeLogMapper;
import xsscd.monitor.air.southwest.modules.system.service.OrganizeLogMng;
@Service
public class OrganizeLogMngImpl implements OrganizeLogMng {
	@Autowired
	OrganizeLogMapper mapper;
	@Override
	public List<OrganizeLog> getList(Map<String, Object> params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return mapper.getList(map);
	}

	public void save(OrganizeLog bean) {
		// TODO Auto-generated method stub
		//mapper.save(bean);
	}

	@Override
	public void saveBatch(List<OrganizeLog> beans) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(OrganizeLog bean) {
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
	public OrganizeLog findById(String staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizeLog findByStaffId(String staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizeLog findByStaffAndOrganize(String staffId, String organizeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganizeLog> findByIds(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}}
