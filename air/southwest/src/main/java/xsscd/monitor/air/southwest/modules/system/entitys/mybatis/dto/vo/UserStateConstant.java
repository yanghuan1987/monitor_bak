package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.vo;
/***
 * 账号状态常量 @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1
 */
public class UserStateConstant {
    
    private UserStateConstant(){
        // 禁止实例化
    }
    
    /** 没有在限期内激活 **/
    public static Integer DIE = 1;
    /** 正常 **/
    public static Integer OK = 3;
    /** 封锁 **/
    public static Integer LOCK = 1;
}
