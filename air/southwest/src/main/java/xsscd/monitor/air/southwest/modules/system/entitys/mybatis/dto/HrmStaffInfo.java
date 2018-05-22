package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import xsscd.monitor.air.southwest.core.excel.ExcelResources;

//@GenTable(name = "hrm_staff_info")
//@GenModel(packageName = "org.easymis.biplatform.designer.entitys.mybatis", modelName = "HrmStaffInfo")
public class HrmStaffInfo implements Serializable {
	//@GenField(labelname = "员工编号", column = "staff_id", id = true, length = 40, isnull = false)
	private String staffId;
	//@GenField(labelname = "员工姓名", column = "name", length = 50, isnull = false)
	private String name;
	//@GenField(labelname = "组织机构代码|注册公司id", column = "org_id", length = 40, isnull = false)
	private String orgId;
	//@GenField(labelname = "注册公司名称", column = "org_name", length = 255, isnull = true)
	private String orgName;
	//@GenField(labelname = "注册分子公司id", column = "filiale_org_id", length = 40, isnull = true)
	private String filialeOrgId;
	//@GenField(labelname = "注册分子公司名称", column = "filiale_org_name", length = 255, isnull = true)
	private String filialeOrgName;
	//@GenField(labelname = "角色ID", column = "role_id", length = 2000, isnull = true)
	private String roleId;
	//@GenField(labelname = "部门", column = "department_id", length = 40, isnull = true)
	private String departmentId;
	//@GenField(labelname = "", column = "department_name", length = 50, isnull = true)
	private String departmentName;
	//@GenField(labelname = "职务|岗位id", column = "position_id", length = 40, isnull = true)
	private String positionId;
	//@GenField(labelname = "", column = "position_name", length = 255, isnull = true)
	private String positionName;
	//@GenField(labelname = "职称|职级级别id", column = "grade_id", length = 40, isnull = true)
	private String gradeId;
	//@GenField(labelname = "会员ID", column = "member_id", length = 40, isnull = true)
	private String memberId;
	//@GenField(labelname = "员工工号", column = "work_no", length = 20, isnull = true)
	private String workNo;
	//@GenField(labelname = "照片文件名", column = "photo_name", length = 254, isnull = true)
	private String photoName;
	//@GenField(labelname = "工种", column = "work_type", length = 254, isnull = true)
	private String workType;
	//@GenField(labelname = "英文名", column = "ename", length = 254, isnull = true)
	private String ename;
	//@GenField(labelname = "身份证号码", column = "card_no", length = 254, isnull = true)
	private String cardNo;
	//@GenField(labelname = "性别(1-男,2-女)", column = "sex", length = 4, isnull = true)
	private String sex;
	//@GenField(labelname = "出生日期", column = "birth", isnull = true)
	private Date birth;
	//@GenField(labelname = "出生日期是否是农历(0-否,1-是)", column = "is_lunar", isnull = true)
	private Integer isLunar;
	//@GenField(labelname = "年龄", column = "age", length = 8, isnull = true)
	private String age;
	//@GenField(labelname = "籍贯", column = "native_place", length = 254, isnull = true)
	private String nativePlace;
	//@GenField(labelname = "户口所在地", column = "domicile_place", length = 254, isnull = true)
	private String domicilePlace;
	//@GenField(labelname = "是否异地户口", column = "is_other_place", isnull = true)
	private Integer isOtherPlace;
	//@GenField(labelname = "民族", column = "nationality", length = 16, isnull = true)
	private String nationality;
	//@GenField(labelname = "婚姻状况", column = "marital_status", isnull = true)
	private Integer maritalStatus;
	//@GenField(labelname = "政治面貌", column = "political_status", isnull = true)
	private Integer politicalStatus;
	//@GenField(labelname = "入党团时间", column = "join_party_time", isnull = true)
	private Date joinPartyTime;
	//@GenField(labelname = "联系电话", column = "phone", length = 254, isnull = true)
	private String phone;
	//@GenField(labelname = "手机号码", column = "mobile", length = 254, isnull = true)
	private String mobile;
	//@GenField(labelname = "电子邮箱1", column = "email", length = 254, isnull = true)
	private String email;
	//@GenField(labelname = "电子邮箱2", column = "email2", length = 254, isnull = true)
	private String email2;
	//@GenField(labelname = "QQ", column = "qq", length = 254, isnull = true)
	private String qq;
	//@GenField(labelname = "家庭地址", column = "home_address", length = 254, isnull = true)
	private String homeAddress;
	//@GenField(labelname = "其它联系方式", column = "other_contact", length = 254, isnull = true)
	private String otherContact;
	//@GenField(labelname = "参加工作时间", column = "job_start_date", isnull = true)
	private Date jobStartDate;
	//@GenField(labelname = "总工龄", column = "job_age", length = 16, isnull = true)
	private String jobAge;
	//@GenField(labelname = "健康状况", column = "health", length = 16, isnull = true)
	private String health;
	//@GenField(labelname = "最高学历", column = "highest_school", length = 32, isnull = true)
	private String highestSchool;
	//@GenField(labelname = "最高学位", column = "highest_degree", length = 32, isnull = true)
	private String highestDegree;
	//@GenField(labelname = "毕业时间", column = "graduation_date", isnull = true)
	private Date graduationDate;
	//@GenField(labelname = "毕业学校", column = "graduation_school", length = 254, isnull = true)
	private String graduationSchool;
	//@GenField(labelname = "专业", column = "major", length = 32, isnull = true)
	private String major;
	//@GenField(labelname = "计算机水平", column = "computer_level", length = 32, isnull = true)
	private String computerLevel;
	//@GenField(labelname = "外语语种1", column = "foreign_language1", length = 32, isnull = true)
	private String foreignLanguage1;
	//@GenField(labelname = "外语水平1", column = "foreign_level1", length = 32, isnull = true)
	private String foreignLevel1;
	//@GenField(labelname = "外语语种2", column = "foreign_language2", length = 32, isnull = true)
	private String foreignLanguage2;
	//@GenField(labelname = "外语水平2", column = "foreign_level2", length = 32, isnull = true)
	private String foreignLevel2;
	//@GenField(labelname = "外语语种3", column = "foreign_language3", length = 32, isnull = true)
	private String foreignLanguage3;
	//@GenField(labelname = "外语水平3", column = "foreign_level3", length = 32, isnull = true)
	private String foreignLevel3;
	//@GenField(labelname = "特长", column = "skills", length = 254, isnull = true)
	private String skills;
	//@GenField(labelname = "员工类型", column = "occupation", length = 32, isnull = true)
	private String occupation;
	//@GenField(labelname = "行政等级", column = "administration_level", length = 32, isnull = true)
	private String administrationLevel;
	//@GenField(labelname = "职称", column = "present_position", length = 32, isnull = true)
	private String presentPosition;
	//@GenField(labelname = "入职时间", column = "work_begin_date", isnull = true)
	private Date workBeginDate;
	//@GenField(labelname = "本单位工龄", column = "work_age", length = 32, isnull = true)
	private String workAge;
	//@GenField(labelname = "起薪时间", column = "begin_salary_date", isnull = true)
	private Date beginSalaryDate;
	//@GenField(labelname = "合同签订时间", column = "contract_begin_date", isnull = true)
	private Date contractBeginDate;
	//@GenField(labelname = "合同到期时间", column = "contract_end_date", isnull = true)
	private Date contractEndDate;
	//@GenField(labelname = "所在单位", column = "company", length = 254, isnull = true)
	private String company;
	//@GenField(labelname = "简历", column = "resume", length = 65535, isnull = true)
	private String resume;
	//@GenField(labelname = "附件编号", column = "attachment_id", length = 65535, isnull = true)
	private String attachmentId;
	//@GenField(labelname = "附件名称", column = "attachment_name", length = 65535, isnull = true)
	private String attachmentName;
	//@GenField(labelname = "", column = "creator_id", length = 40, isnull = true)
	private String creatorId;
	//@GenField(labelname = "", column = "creator_name", length = 50, isnull = true)
	private String creatorName;
	//@GenField(labelname = "建档时间", column = "create_time", isnull = true)
	private Date createTime;
	//@GenField(labelname = "", column = "update_id", length = 255, isnull = true)
	private String updateId;
	//@GenField(labelname = "", column = "update_name", length = 255, isnull = true)
	private String updateName;
	//@GenField(labelname = "最后修改时间", column = "update_time", isnull = true)
	private Date updateTime;
	//@GenField(labelname = "年休假", column = "leave_type", isnull = true)
	private Integer leaveType;
	//@GenField(labelname = "户口类别", column = "staff_type", isnull = true)
	private Integer staffType;
	//@GenField(labelname = "OA登录权限(1-允许登录,0-不允许登录)", column = "oa_status", isnull = true)
	private Integer oaStatus;
	//@GenField(labelname = "是否管理员1是管理员", column = "is_admin", isnull = true)
	private Integer isAdmin;
	//@GenField(labelname = "职务情况", column = "certificate", length = 0, isnull = true)
	private String certificate;
	//@GenField(labelname = "担保记录", column = "surety", length = 0, isnull = true)
	private String surety;
	//@GenField(labelname = "体检记录", column = "body_examine", length = 0, isnull = true)
	private String bodyExamine;
	//@GenField(labelname = "社保缴纳情况", column = "insure", length = 0, isnull = true)
	private String insure;
	//@GenField(labelname = "自定义字段1", column = "userdef1", length = 0, isnull = true)
	private String userdef1;
	//@GenField(labelname = "自定义字段2", column = "userdef2", length = 0, isnull = true)
	private String userdef2;
	//@GenField(labelname = "自定义字段3", column = "userdef3", length = 0, isnull = true)
	private String userdef3;
	//@GenField(labelname = "自定义字段4", column = "userdef4", length = 0, isnull = true)
	private String userdef4;
	//@GenField(labelname = "自定义字段5", column = "userdef5", length = 0, isnull = true)
	private String userdef5;
	//@GenField(labelname = "记录日期", column = "record_date", isnull = true)
	private Date recordDate;
	//@GenField(labelname = "职称|职级级别", column = "grade_name", length = 50, isnull = true)
	private String gradeName;
	//@GenField(labelname = "籍贯", column = "native_place2", length = 254, isnull = true)
	private String nativePlace2;
	//@GenField(labelname = "岗位", column = "work_job", length = 200, isnull = true)
	private String workJob;
	//@GenField(labelname = "曾用名", column = "before_name", length = 200, isnull = true)
	private String beforeName;
	//@GenField(labelname = "生日提醒日期", column = "birth_remind_date", length = 10, isnull = true)
	private String birthRemindDate;
	//@GenField(labelname = "开户行名1", column = "bank1", length = 254, isnull = true)
	private String bank1;
	//@GenField(labelname = "开户行的账号1", column = "bank_account1", length = 254, isnull = true)
	private String bankAccount1;
	//@GenField(labelname = "开户行名2", column = "bank2", length = 254, isnull = true)
	private String bank2;
	//@GenField(labelname = "开户行的账号2", column = "bank_account2", length = 254, isnull = true)
	private String bankAccount2;
	//@GenField(labelname = "血型(A-A型血,B-B型血,O-O型血,AB-AB型血)", column = "blood_type", length = 5, isnull = true)
	private String bloodType;
	//@GenField(labelname = "是否为专家", column = "is_experts", isnull = true)
	private Integer isExperts;
	//@GenField(labelname = "专家特长信息", column = "experts_info", length = 65535, isnull = true)
	private String expertsInfo;
	//@GenField(labelname = "直属下级", column = "directly_under", length = 200, isnull = true)
	private String directlyUnder;
	//@GenField(labelname = "直属上级", column = "directly_superior", length = 200, isnull = true)
	private String directlySuperior;
	//@GenField(labelname = "兼职", column = "part_time", length = 200, isnull = true)
	private String partTime;
	//@GenField(labelname = "研究成果", column = "research_results", length = 65535, isnull = true)
	private String researchResults;
	//@GenField(labelname = "工作电话", column = "work_phone", length = 20, isnull = true)
	private String workPhone;
	//@GenField(labelname = "在职状态1在职2离职", column = "work_status", isnull = true)
	private Integer workStatus;
	//@GenField(labelname = "备注", column = "remark", length = 65535, isnull = true)
	private String remark;
	//@GenField(labelname = "语言设置1简体中文2English3繁體中文", column = "set_language", isnull = false)
	private Integer setLanguage;

	private Set<OrganizeRole> organizeRoles;
	private List<OrganizeFunction> organizeFunctions;

	public Set<OrganizeRole> getOrganizeRoles() {
		return organizeRoles;
	}

	public void setOrganizeRoles(Set<OrganizeRole> organizeRoles) {
		this.organizeRoles = organizeRoles;
	}

	public List<OrganizeFunction> getOrganizeFunctions() {
		return organizeFunctions;
	}

	public void setOrganizeFunctions(List<OrganizeFunction> organizeFunctions) {
		this.organizeFunctions = organizeFunctions;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	@ExcelResources(title="用户名",order=3)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getFilialeOrgId() {
		return filialeOrgId;
	}

	public void setFilialeOrgId(String filialeOrgId) {
		this.filialeOrgId = filialeOrgId;
	}

	public String getFilialeOrgName() {
		return filialeOrgName;
	}

	public void setFilialeOrgName(String filialeOrgName) {
		this.filialeOrgName = filialeOrgName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getIsLunar() {
		return isLunar;
	}

	public void setIsLunar(Integer isLunar) {
		this.isLunar = isLunar;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getDomicilePlace() {
		return domicilePlace;
	}

	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}

	public Integer getIsOtherPlace() {
		return isOtherPlace;
	}

	public void setIsOtherPlace(Integer isOtherPlace) {
		this.isOtherPlace = isOtherPlace;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public Date getJoinPartyTime() {
		return joinPartyTime;
	}

	public void setJoinPartyTime(Date joinPartyTime) {
		this.joinPartyTime = joinPartyTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getOtherContact() {
		return otherContact;
	}

	public void setOtherContact(String otherContact) {
		this.otherContact = otherContact;
	}

	public Date getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(Date jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public String getJobAge() {
		return jobAge;
	}

	public void setJobAge(String jobAge) {
		this.jobAge = jobAge;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getHighestSchool() {
		return highestSchool;
	}

	public void setHighestSchool(String highestSchool) {
		this.highestSchool = highestSchool;
	}

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getGraduationSchool() {
		return graduationSchool;
	}

	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getComputerLevel() {
		return computerLevel;
	}

	public void setComputerLevel(String computerLevel) {
		this.computerLevel = computerLevel;
	}

	public String getForeignLanguage1() {
		return foreignLanguage1;
	}

	public void setForeignLanguage1(String foreignLanguage1) {
		this.foreignLanguage1 = foreignLanguage1;
	}

	public String getForeignLevel1() {
		return foreignLevel1;
	}

	public void setForeignLevel1(String foreignLevel1) {
		this.foreignLevel1 = foreignLevel1;
	}

	public String getForeignLanguage2() {
		return foreignLanguage2;
	}

	public void setForeignLanguage2(String foreignLanguage2) {
		this.foreignLanguage2 = foreignLanguage2;
	}

	public String getForeignLevel2() {
		return foreignLevel2;
	}

	public void setForeignLevel2(String foreignLevel2) {
		this.foreignLevel2 = foreignLevel2;
	}

	public String getForeignLanguage3() {
		return foreignLanguage3;
	}

	public void setForeignLanguage3(String foreignLanguage3) {
		this.foreignLanguage3 = foreignLanguage3;
	}

	public String getForeignLevel3() {
		return foreignLevel3;
	}

	public void setForeignLevel3(String foreignLevel3) {
		this.foreignLevel3 = foreignLevel3;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAdministrationLevel() {
		return administrationLevel;
	}

	public void setAdministrationLevel(String administrationLevel) {
		this.administrationLevel = administrationLevel;
	}

	public String getPresentPosition() {
		return presentPosition;
	}

	public void setPresentPosition(String presentPosition) {
		this.presentPosition = presentPosition;
	}

	public Date getWorkBeginDate() {
		return workBeginDate;
	}

	public void setWorkBeginDate(Date workBeginDate) {
		this.workBeginDate = workBeginDate;
	}

	public String getWorkAge() {
		return workAge;
	}

	public void setWorkAge(String workAge) {
		this.workAge = workAge;
	}

	public Date getBeginSalaryDate() {
		return beginSalaryDate;
	}

	public void setBeginSalaryDate(Date beginSalaryDate) {
		this.beginSalaryDate = beginSalaryDate;
	}

	public Date getContractBeginDate() {
		return contractBeginDate;
	}

	public void setContractBeginDate(Date contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Integer leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getStaffType() {
		return staffType;
	}

	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}

	public Integer getOaStatus() {
		return oaStatus;
	}

	public void setOaStatus(Integer oaStatus) {
		this.oaStatus = oaStatus;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getSurety() {
		return surety;
	}

	public void setSurety(String surety) {
		this.surety = surety;
	}

	public String getBodyExamine() {
		return bodyExamine;
	}

	public void setBodyExamine(String bodyExamine) {
		this.bodyExamine = bodyExamine;
	}

	public String getInsure() {
		return insure;
	}

	public void setInsure(String insure) {
		this.insure = insure;
	}

	public String getUserdef1() {
		return userdef1;
	}

	public void setUserdef1(String userdef1) {
		this.userdef1 = userdef1;
	}

	public String getUserdef2() {
		return userdef2;
	}

	public void setUserdef2(String userdef2) {
		this.userdef2 = userdef2;
	}

	public String getUserdef3() {
		return userdef3;
	}

	public void setUserdef3(String userdef3) {
		this.userdef3 = userdef3;
	}

	public String getUserdef4() {
		return userdef4;
	}

	public void setUserdef4(String userdef4) {
		this.userdef4 = userdef4;
	}

	public String getUserdef5() {
		return userdef5;
	}

	public void setUserdef5(String userdef5) {
		this.userdef5 = userdef5;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getNativePlace2() {
		return nativePlace2;
	}

	public void setNativePlace2(String nativePlace2) {
		this.nativePlace2 = nativePlace2;
	}

	public String getWorkJob() {
		return workJob;
	}

	public void setWorkJob(String workJob) {
		this.workJob = workJob;
	}

	public String getBeforeName() {
		return beforeName;
	}

	public void setBeforeName(String beforeName) {
		this.beforeName = beforeName;
	}

	public String getBirthRemindDate() {
		return birthRemindDate;
	}

	public void setBirthRemindDate(String birthRemindDate) {
		this.birthRemindDate = birthRemindDate;
	}

	public String getBank1() {
		return bank1;
	}

	public void setBank1(String bank1) {
		this.bank1 = bank1;
	}

	public String getBankAccount1() {
		return bankAccount1;
	}

	public void setBankAccount1(String bankAccount1) {
		this.bankAccount1 = bankAccount1;
	}

	public String getBank2() {
		return bank2;
	}

	public void setBank2(String bank2) {
		this.bank2 = bank2;
	}

	public String getBankAccount2() {
		return bankAccount2;
	}

	public void setBankAccount2(String bankAccount2) {
		this.bankAccount2 = bankAccount2;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Integer getIsExperts() {
		return isExperts;
	}

	public void setIsExperts(Integer isExperts) {
		this.isExperts = isExperts;
	}

	public String getExpertsInfo() {
		return expertsInfo;
	}

	public void setExpertsInfo(String expertsInfo) {
		this.expertsInfo = expertsInfo;
	}

	public String getDirectlyUnder() {
		return directlyUnder;
	}

	public void setDirectlyUnder(String directlyUnder) {
		this.directlyUnder = directlyUnder;
	}

	public String getDirectlySuperior() {
		return directlySuperior;
	}

	public void setDirectlySuperior(String directlySuperior) {
		this.directlySuperior = directlySuperior;
	}

	public String getPartTime() {
		return partTime;
	}

	public void setPartTime(String partTime) {
		this.partTime = partTime;
	}

	public String getResearchResults() {
		return researchResults;
	}

	public void setResearchResults(String researchResults) {
		this.researchResults = researchResults;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSetLanguage() {
		return setLanguage;
	}

	public void setSetLanguage(Integer setLanguage) {
		this.setLanguage = setLanguage;
	}
}