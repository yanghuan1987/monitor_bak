package xsscd.monitor.air.southwest.modules.system.service;

import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Permission;

public interface PermissionPageService {

	    /**
	     * 获取所有的权限信息并分页显示
	     * @param pageNo
	     * 			当前页面数
	     * @param pageSize
	     * 			每一页面的页数
	     * @return
	     */
	    public List<Permission> findAll(int pageNo, int pageSize, String str);
}
