package xsscd.monitor.air.southwest.common.email.receivemail;
import javax.mail.Message;

/***
 * @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1
 */
public interface ReceiveMailInter {
    
    /**
     * 接受邮件
     * 
     * @return
     * @throws Exception
     */
    Message[] receiveMail() throws Exception;
}
