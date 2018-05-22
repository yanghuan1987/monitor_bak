package xsscd.monitor.air.southwest.modules.system.service;

import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Role;

public interface RolePageService {


    /**
     * 获取所有的菜单信息并分页显示
     * @param pageNo
     * 			当前页面数
     * @param pageSize
     * 			每一页面的页数
     * @return
     */
    public List<Role> findAll(int pageNo, int pageSize, String str);

    public List<Role> findAllRole();

    /**
     * 根据角色id查找角色信息
     * @param roleId
     * @return
     */
    public Role findByRoleId(String roleId);

    /**
     * 保存角色信息
     * @param role
     */
    public void doSave(Role role);

}
