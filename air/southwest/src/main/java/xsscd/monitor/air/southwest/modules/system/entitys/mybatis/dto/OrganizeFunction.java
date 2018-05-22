package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//@GenTable(name = "organize_function")
//@GenModel(packageName = "org.easymis.biplatform.designer.entitys.mybatis", modelName = "OrganizeFunction")
public class OrganizeFunction implements Serializable {
	//@GenField(labelname = "菜单ID", column = "function_id", id = true, length = 40, isnull = false)
	private String functionId;
	//@GenField(labelname = "", column = "org_id", length = 40, isnull = false)
	private String orgId;
	//@GenField(labelname = "系统编码", column = "sys_code", length = 40, isnull = false)
	private String sysCode;
	//@GenField(labelname = "别名", column = "alias", length = 255, isnull = true)
	private String alias;
	//@GenField(labelname = "", column = "function_name", length = 1032, isnull = false)
	private String functionName;
	//@GenField(labelname = "", column = "parent_id", length = 40, isnull = false)
	private String parentId;
	//@GenField(labelname = "", column = "level", isnull = false)
	private Integer level;
	//@GenField(labelname = "类别1目录2菜单3按钮", column = "category", isnull = true)
	private Integer category;
	//@GenField(labelname = "应用分组", column = "group_id", length = 40, isnull = true)
	private String groupId;
	//@GenField(labelname = "应用分组名", column = "group_name", length = 50, isnull = true)
	private String groupName;
	//@GenField(labelname = "", column = "display_name", length = 1032, isnull = true)
	private String displayName;
	//@GenField(labelname = "", column = "detail", length = 200, isnull = true)
	private String detail;
	//@GenField(labelname = "", column = "url", length = 64, isnull = true)
	private String url;
	//@GenField(labelname = "", column = "priority", isnull = true)
	private Integer priority;
	//@GenField(labelname = "备注", column = "depict", length = 200, isnull = true)
	private String depict;
	//@GenField(labelname = "", column = "icon", length = 200, isnull = true)
	private String icon;
	//@GenField(labelname = "免费状态1开启2关闭", column = "free_status", isnull = false)
	private Integer freeStatus;
	//@GenField(labelname = "付费应用到期日", column = "due_date", isnull = true)
	private Date dueDate;
	//@GenField(labelname = "状态1启用2禁用", column = "status", isnull = false)
	private Integer status;

	private List<OrganizeFunction> subFunction;


	public List<OrganizeFunction> getSubFunction() {
		return subFunction;
	}

	public void setSubFunction(List<OrganizeFunction> subFunction) {
		this.subFunction = subFunction;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getFreeStatus() {
		return freeStatus;
	}

	public void setFreeStatus(Integer freeStatus) {
		this.freeStatus = freeStatus;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}