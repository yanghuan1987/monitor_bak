package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.Date;

public class EasymisScheduleJobLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// @GenField(labelname="任务日志id",column="log_id",id=true,length=40,isnull=false)
	private String logId;
	// @GenField(labelname="任务id",column="job_id",length=40,isnull=false)
	private String jobId;
	// @GenField(labelname="保留字段",column="org_id",length=40,isnull=true)
	private String orgId;
	// @GenField(labelname="spring
	// bean名称",column="bean_name",length=200,isnull=true)
	private String beanName;
	// @GenField(labelname="方法名",column="method_name",length=100,isnull=true)
	private String methodName;
	// @GenField(labelname="参数",column="params",length=2000,isnull=true)
	private String params;
	// @GenField(labelname="任务状态 0：成功 1：失败",column="status",isnull=false)
	private Integer status;
	// @GenField(labelname="失败信息",column="error",length=2000,isnull=true)
	private String error;
	// @GenField(labelname="耗时(单位：毫秒)",column="run_time",isnull=false)
	private Integer runTime;
	// @GenField(labelname="创建时间",column="create_time",isnull=true)
	private Date createTime;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getRunTime() {
		return runTime;
	}

	public void setRunTime(Integer runTime) {
		this.runTime = runTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}