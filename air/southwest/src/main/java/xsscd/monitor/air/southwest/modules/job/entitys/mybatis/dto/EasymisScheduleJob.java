package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto;
import java.io.Serializable;
import java.util.Date; 
 
  
  
 //@GenTable(name="easymis_schedule_job") 
//@GenModel(packageName="org.easymis.biplatform.designer.entitys.mybatis",modelName="EasymisScheduleJob")  
 public class EasymisScheduleJob implements Serializable{
		/**
		 * 任务调度参数key
		 */
	  public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
      //@GenField(labelname="任务id",column="job_id",id=true,length=40,isnull=false) 
      private String jobId; 
      //@GenField(labelname="",column="org_id",length=40,isnull=true) 
      private String orgId; 
      //@GenField(labelname="spring bean名称",column="bean_name",length=200,isnull=true) 
      private String beanName; 
      //@GenField(labelname="方法名",column="method_name",length=100,isnull=true) 
      private String methodName; 
      //@GenField(labelname="参数",column="params",length=2000,isnull=true) 
      private String params; 
      //@GenField(labelname="cron表达式",column="cron_expression",length=100,isnull=true) 
      private String cronExpression; 
      //@GenField(labelname="任务状态  0：正常  1：暂停",column="status",isnull=true) 
      private Integer status; 
      //@GenField(labelname="备注",column="remark",length=255,isnull=true) 
      private String remark; 
      //@GenField(labelname="",column="creator_id",length=40,isnull=true) 
      private String creatorId; 
      //@GenField(labelname="创建者",column="creator_name",length=50,isnull=true) 
      private String creatorName; 
      //@GenField(labelname="创建时间",column="create_time",isnull=true) 
      private Date createTime; 
      //@GenField(labelname="",column="update_id",length=40,isnull=true) 
      private String updateId; 
      //@GenField(labelname="修改者",column="update_name",length=50,isnull=true) 
      private String updateName; 
      //@GenField(labelname="修改时间",column="update_time",isnull=true) 
      private Date updateTime; 
      public String getJobId(){ 
      	   return jobId;
      }
      public void setJobId(String jobId){ 
      	   this.jobId=jobId; 
      } 
      public String getOrgId(){ 
      	   return orgId;
      }
      public void setOrgId(String orgId){ 
      	   this.orgId=orgId; 
      } 
      public String getBeanName(){ 
      	   return beanName;
      }
      public void setBeanName(String beanName){ 
      	   this.beanName=beanName; 
      } 
      public String getMethodName(){ 
      	   return methodName;
      }
      public void setMethodName(String methodName){ 
      	   this.methodName=methodName; 
      } 
      public String getParams(){ 
      	   return params;
      }
      public void setParams(String params){ 
      	   this.params=params; 
      } 
      public String getCronExpression(){ 
      	   return cronExpression;
      }
      public void setCronExpression(String cronExpression){ 
      	   this.cronExpression=cronExpression; 
      } 

      public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark(){ 
      	   return remark;
      }
      public void setRemark(String remark){ 
      	   this.remark=remark; 
      } 
      public String getCreatorId(){ 
      	   return creatorId;
      }
      public void setCreatorId(String creatorId){ 
      	   this.creatorId=creatorId; 
      } 
      public String getCreatorName(){ 
      	   return creatorName;
      }
      public void setCreatorName(String creatorName){ 
      	   this.creatorName=creatorName; 
      } 
      public Date getCreateTime(){ 
      	   return createTime;
      }
      public void setCreateTime(Date createTime){ 
      	   this.createTime=createTime; 
      } 
      public String getUpdateId(){ 
      	   return updateId;
      }
      public void setUpdateId(String updateId){ 
      	   this.updateId=updateId; 
      } 
      public String getUpdateName(){ 
      	   return updateName;
      }
      public void setUpdateName(String updateName){ 
      	   this.updateName=updateName; 
      } 
      public Date getUpdateTime(){ 
      	   return updateTime;
      }
      public void setUpdateTime(Date updateTime){ 
      	   this.updateTime=updateTime; 
      } 
}