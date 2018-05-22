package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.vo;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1
 */
@ApiModel(value = "用户类",parent = UserDO.class)
public class UserVO extends UserDO  {
    
    private static final long serialVersionUID = 6514868907104830464L;
    @ApiModelProperty(name = "salt",dataType = "java.lang.String",value = "可解析加密盐",hidden = true)
    private String salt;

    List<SysRoleVO> rolelist;
    /** 角色ID 集合 **/
    List<String> roleids;
    /** 角色键 集合 **/
    List<String> rolekeys;
    
    public List<String> getRolekeys(){
        return rolekeys;
    }
    
    public void setRolekeys(List<String> rolekeys){
        this.rolekeys = rolekeys;
    }
    
    public List<String> getRoleids(){
        return roleids;
    }
    
    public void setRoleids(List<String> roleids){
        this.roleids = roleids;
    }
    
    public List<SysRoleVO> getRolelist(){
        return rolelist;
    }
    
    public void setRolelist(List<SysRoleVO> rolelist){
        this.rolelist = rolelist;
    }
    
    public String getSalt(){
        return salt;
    }
    
    public void setSalt(String salt){
        this.salt = salt;
    }
  

}
