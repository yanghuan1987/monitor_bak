package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import xsscd.monitor.air.southwest.common.jdbc.BaseQueryAble;

/***
 * @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1
 */
@ApiModel(value = "用户查询条件",parent = UserVO.class,description = "")
public class UserQuery extends UserVO implements BaseQueryAble {
    
    private static final long serialVersionUID = 3000693783594458654L;
    @ApiModelProperty(name = "blurred",dataType = "java.lang.Boolean",value = "是否模糊查询")
    boolean blurred;
    
    @Override
    public boolean isBlurred(){
        return blurred;
    }
    
    public void setBlurred(boolean blurred){
        this.blurred = blurred;
    }
    
    @Override
    public String toString(){
        return "UserQvo [blurred=" + blurred + ", toString()=" + super.toString() + "]";
    }
}
