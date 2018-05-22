package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;

import java.util.HashSet;
import java.util.Set;

public class Role {
	/** 角色Id**/
	private int roleId;

	/** 角色描述**/
	private String roleDesc;

	/** 角色名称**/
	private String roleName;

	/** 角色标志**/
	private String role;

private Set<Permission> permissions = new HashSet<Permission>();

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

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public Set<Permission> getPermissions() {
	return permissions;
}

public void setPermissions(Set<Permission> permissions) {
	this.permissions = permissions;
}

}
