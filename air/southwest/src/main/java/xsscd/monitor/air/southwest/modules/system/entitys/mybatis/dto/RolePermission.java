package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;

public class RolePermission {
	   //表Id
    private String rpId;

    //角色Id
    private int roleId;

    //权限Id
private int permissionId;

public String getRpId() {
	return rpId;
}

public void setRpId(String rpId) {
	this.rpId = rpId;
}

public int getRoleId() {
	return roleId;
}

public void setRoleId(int roleId) {
	this.roleId = roleId;
}

public int getPermissionId() {
	return permissionId;
}

public void setPermissionId(int permissionId) {
	this.permissionId = permissionId;
}
}
