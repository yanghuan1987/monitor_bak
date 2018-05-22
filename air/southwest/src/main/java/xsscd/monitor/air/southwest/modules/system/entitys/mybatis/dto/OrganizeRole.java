package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set; 
 
  
  
// @GenTable(name="organize_role") 
//@GenModel(packageName="org.easymis.biplatform.designer.entitys.mybatis",modelName="OrganizeRole")  
public class OrganizeRole implements Serializable {
	////@GenField(labelname = "", column = "role_id", id = true, length = 40, isnull = false)
	private String roleId;
	////@GenField(labelname = "", column = "org_id", length = 40, isnull = false)
	private String orgId;
	////@GenField(labelname = "角色名|管理组名称", column = "role_name", length = 50, isnull = false)
	private String roleName;
	////@GenField(labelname = "是否系统管理员1是2否", column = "is_super", isnull = false)
	private Integer isSuper;
	////@GenField(labelname = "级别", column = "level", isnull = false)
	private Integer level;
	////@GenField(labelname = "所属管理组|父类组织", column = "parent_id", length = 40, isnull = false)
	private String parentId;
	////@GenField(labelname = "管理员", column = "manager", length = 50, isnull = true)
	private String manager;
	////@GenField(labelname = "管理员", column = "manager_id", length = 40, isnull = true)
	private String managerId;
	////@GenField(labelname = "排序", column = "priority", isnull = false)
	private Integer priority;
	////@GenField(labelname = "", column = "depict", length = 255, isnull = true)
	private String depict;

	Set<OrganizePermission> organizePermissions;
	private Set<OrganizePermission> operations = new HashSet<OrganizePermission>();

	public Set<OrganizePermission> getOperations() {
		return operations;
	}

	public void setOperations(Set<OrganizePermission> operations) {
		this.operations = operations;
	}

	public Set<OrganizePermission> getOrganizePermissions() {
		return organizePermissions;
	}

	public void setOrganizePermissions(Set<OrganizePermission> organizePermissions) {
		this.organizePermissions = organizePermissions;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDepict() {
		return depict;
	}

	public void setDepict(String depict) {
		this.depict = depict;
	}
}