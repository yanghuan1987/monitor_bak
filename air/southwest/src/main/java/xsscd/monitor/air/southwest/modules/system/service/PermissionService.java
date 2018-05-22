package xsscd.monitor.air.southwest.modules.system.service;

import java.util.Set;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Permission;

public interface PermissionService {
;

	    /**
	     * 保存权限表信息
	     * @param permission
	     */
	    public void doSave(Permission permission);
	    public Set<Permission> findAllP();
}
