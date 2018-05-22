package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.vo;

import java.io.Serializable;

public class RoleVO implements Serializable{

    /** 角色Id**/
    private int roleId;

    /** 角色描述**/
    private String roleDesc;

    /** 角色名称**/
    private String roleName;

    private boolean checked;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
