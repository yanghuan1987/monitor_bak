package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;
import java.io.Serializable; 
 
  
  
// @GenTable(name="organize_permission") 
//@GenModel(packageName="org.easymis.biplatform.designer.entitys.mybatis",modelName="OrganizePermission")  
 public class OrganizePermission implements Serializable{  
      //@GenField(labelname="权限Id",column="permission_id",id=true,length=40,isnull=false) 
      private String permissionId; 
      //@GenField(labelname="",column="org_id",length=40,isnull=false) 
      private String orgId; 
      //@GenField(labelname="权限描述",column="pdesc",length=100,isnull=false) 
      private String pdesc; 
      //@GenField(labelname="权限名称",column="name",length=100,isnull=false) 
      private String name; 
      //@GenField(labelname="菜单Id",column="function_id",length=40,isnull=false) 
      private String functionId; 
      
      private OrganizeFunction organizeFunction;
      
      public OrganizeFunction getOrganizeFunction() {
		return organizeFunction;
	}
	public void setOrganizeFunction(OrganizeFunction organizeFunction) {
		this.organizeFunction = organizeFunction;
	}
	public String getPermissionId(){ 
      	   return permissionId;
      }
      public void setPermissionId(String permissionId){ 
      	   this.permissionId=permissionId; 
      } 
      public String getOrgId(){ 
      	   return orgId;
      }
      public void setOrgId(String orgId){ 
      	   this.orgId=orgId; 
      } 
      public String getPdesc(){ 
      	   return pdesc;
      }
      public void setPdesc(String pdesc){ 
      	   this.pdesc=pdesc; 
      } 
      public String getName(){ 
      	   return name;
      }
      public void setName(String name){ 
      	   this.name=name; 
      } 
      public String getFunctionId(){ 
      	   return functionId;
      }
      public void setFunctionId(String functionId){ 
      	   this.functionId=functionId; 
      } 
}