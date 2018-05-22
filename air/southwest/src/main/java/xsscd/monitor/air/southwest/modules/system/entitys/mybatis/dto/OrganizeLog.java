package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;
import java.io.Serializable;
import java.util.Date; 
 
  
  
 //@GenTable(name="organize_log") 
//@GenModel(packageName="org.easymis.biplatform.designer.entitys.mybatis",modelName="OrganizeLog")  
 public class OrganizeLog implements Serializable{  
      //@GenField(labelname="",column="log_id",id=true,isnull=false) 
      private Integer logId; 
      //@GenField(labelname="保留字段",column="org_id",length=40,isnull=true) 
      private String orgId; 
      //@GenField(labelname="保留字段",column="org_name",length=50,isnull=true) 
      private String orgName; 
      //@GenField(labelname="用户名|员工名",column="username",length=45,isnull=true) 
      private String username; 
      //@GenField(labelname="保留字段",column="site_id",length=40,isnull=true) 
      private String siteId; 
      //@GenField(labelname="日志类型|保留字段",column="category",length=40,isnull=true) 
      private String category; 
      //@GenField(labelname="日志时间|登录时间|创建时间",column="create_time",isnull=true) 
      private Date createTime; 
      //@GenField(labelname="IP地址",column="ip",length=50,isnull=true) 
      private String ip; 
      //@GenField(labelname="URL地址",column="url",length=255,isnull=true) 
      private String url; 
      //@GenField(labelname="",column="operation",length=50,isnull=true) 
      private String operation; 
      //@GenField(labelname="请求方法",column="method",length=255,isnull=true) 
      private String method; 
      //@GenField(labelname="请求参数",column="params",length=500,isnull=true) 
      private String params; 
      //@GenField(labelname="会话id",column="session_id",length=45,isnull=true) 
      private String sessionId; 
      //@GenField(labelname="登录失败次数|保留字段",column="login_failure_count",isnull=true) 
      private Integer loginFailureCount; 
      //@GenField(labelname="锁定时间|保留字段",column="lock_time",isnull=true) 
      private Date lockTime; 
      //@GenField(labelname="执行时长(毫秒)",column="run_time",isnull=true) 
      private Integer runTime; 
      //@GenField(labelname="日志状态|任务状态1成功 0失败",column="status",isnull=false) 
      private Integer status; 
      public Integer getLogId(){ 
      	   return logId;
      }
      public void setLogId(Integer logId){ 
      	   this.logId=logId; 
      } 
      public String getOrgId(){ 
      	   return orgId;
      }
      public void setOrgId(String orgId){ 
      	   this.orgId=orgId; 
      } 
      public String getOrgName(){ 
      	   return orgName;
      }
      public void setOrgName(String orgName){ 
      	   this.orgName=orgName; 
      } 
      public String getUsername(){ 
      	   return username;
      }
      public void setUsername(String username){ 
      	   this.username=username; 
      } 
      public String getSiteId(){ 
      	   return siteId;
      }
      public void setSiteId(String siteId){ 
      	   this.siteId=siteId; 
      } 
      public String getCategory(){ 
      	   return category;
      }
      public void setCategory(String category){ 
      	   this.category=category; 
      } 
      public Date getCreateTime(){ 
      	   return createTime;
      }
      public void setCreateTime(Date createTime){ 
      	   this.createTime=createTime; 
      } 
      public String getIp(){ 
      	   return ip;
      }
      public void setIp(String ip){ 
      	   this.ip=ip; 
      } 
      public String getUrl(){ 
      	   return url;
      }
      public void setUrl(String url){ 
      	   this.url=url; 
      } 
      public String getOperation(){ 
      	   return operation;
      }
      public void setOperation(String operation){ 
      	   this.operation=operation; 
      } 
      public String getMethod(){ 
      	   return method;
      }
      public void setMethod(String method){ 
      	   this.method=method; 
      } 
      public String getParams(){ 
      	   return params;
      }
      public void setParams(String params){ 
      	   this.params=params; 
      } 
      public String getSessionId(){ 
      	   return sessionId;
      }
      public void setSessionId(String sessionId){ 
      	   this.sessionId=sessionId; 
      } 
      public Integer getLoginFailureCount(){ 
      	   return loginFailureCount;
      }
      public void setLoginFailureCount(Integer loginFailureCount){ 
      	   this.loginFailureCount=loginFailureCount; 
      } 
      public Date getLockTime(){ 
      	   return lockTime;
      }
      public void setLockTime(Date lockTime){ 
      	   this.lockTime=lockTime; 
      } 
      public Integer getRunTime(){ 
      	   return runTime;
      }
      public void setRunTime(Integer runTime){ 
      	   this.runTime=runTime; 
      } 
      public Integer getStatus(){ 
      	   return status;
      }
      public void setStatus(Integer status){ 
      	   this.status=status; 
      } 
}