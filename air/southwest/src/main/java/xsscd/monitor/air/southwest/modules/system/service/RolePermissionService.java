package xsscd.monitor.air.southwest.modules.system.service;

import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.RolePermission;

public interface RolePermissionService {

    /**
     * 保存数据
     * @param rp
     * @return
     */
    public boolean doSave(RolePermission rp);

    /**
     * 删除数据
     * @param rp
     * @return
     */
    public boolean doDel(RolePermission rp);

    /**
     * 通过角色Id获取数据
     * @param roleId
     * @return
     */
    public List<RolePermission> findById(int roleId);

}
