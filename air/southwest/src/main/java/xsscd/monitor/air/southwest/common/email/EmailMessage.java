package xsscd.monitor.air.southwest.common.email;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/***
 * @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1
 */
public class EmailMessage {
    
    private MimeMessage mimeMessage = null;
    /** 附件下载后的存放目录 **/
    private String saveAttachPath = "";
    /** 存放邮件内容 **/
    private StringBuffer bodytext = new StringBuffer();
    /** 默认的日前显示格式 **/
    private String dateformat = "yy-MM-dd HH:mm";
    String mimetypeMutipart = "multipart/*";
    String mimetypeMessage = "message/rfc822";
    
    public EmailMessage(MimeMessage mimeMessage){
        this.mimeMessage = mimeMessage;
    }
    
    public void setMimeMessage(MimeMessage mimeMessage){
        this.mimeMessage = mimeMessage;
    }
    
    /** 获得发件人的地址和姓名 */
    public String getFrom() throws Exception{
        String reply = mimeMessage.getReplyTo()[0].toString();
        try {
            reply = reply.substring(reply.indexOf("<") + 1, reply.indexOf(">"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reply;
    }
    
    /** 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同 "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址 */
    public String getMailAddress(String type) throws Exception{
        String mailaddr = "";
        String addtype = type.toUpperCase();
        InternetAddress[] address = null;
        String to = "TO";
        String cc = "CC";
        String bcc = "BCC";
        if (addtype.equals(to) || addtype.equals(cc) || addtype.equals(bcc)) {
            if (addtype.equals(to)) {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);
            }
            else if (addtype.equals(cc)) {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);
            }
            else {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC);
            }
            if (address != null) {
                for (int i = 0; i < address.length; i++) {
                    String email = address[i].getAddress();
                    if (email == null)
                        email = "";
                    else {
                        email = MimeUtility.decodeText(email);
                    }
                    String personal = address[i].getPersonal();
                    if (personal == null)
                        personal = "";
                    else {
                        personal = MimeUtility.decodeText(personal);
                    }
                    String compositeto = personal + "<" + email + ">";
                    mailaddr += "," + compositeto;
                }
                mailaddr = mailaddr.substring(1);
            }
        }
        else {
            throw new Exception("Error emailaddr type!");
        }
        return mailaddr;
    }
    
    /** 获得邮件主题 */
    public String getSubject() throws MessagingException{
        String subject = "";
        try {
            subject = MimeUtility.decodeText(mimeMessage.getSubject());
            if (subject == null) {
                subject = "";
            }
        } catch (Exception exce) {
            exce.printStackTrace();
        }
        return subject;
    }
    
    /** 获得邮件发送日期 */
    public String getSentDate() throws Exception{
        Date sentdate = mimeMessage.getSentDate();
        SimpleDateFormat format = new SimpleDateFormat(dateformat);
        return format.format(sentdate);
    }
    
    /** 获得邮件正文内容 */
    public String getBodyText(){
        return bodytext.toString();
    }
    
    /** 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析 */
    public void getMailContent(Part part) throws Exception{
        String contenttype = part.getContentType();
        int nameindex = contenttype.indexOf("name");
        boolean conname = false;
        if (nameindex != -1) {
            conname = true;
        }
        String textType = "text/plain";
        if (part.isMimeType(textType) && !conname) {
            bodytext.append((String) part.getContent());
        }
    }
    
    /** 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false" */
    public boolean getReplySign() throws MessagingException{
        boolean replysign = false;
        String[] needreply = mimeMessage.getHeader("Disposition-Notification-To");
        if (needreply != null) {
            replysign = true;
        }
        return replysign;
    }
    
    /** 获得此邮件的Message-ID */
    public String getMessageId() throws MessagingException{
        return mimeMessage.getMessageID();
    }
    
    /** 【判断此邮件是否已读，如果未读返回返回false,反之返回true】 */
    public boolean isNew() throws MessagingException{
        boolean isnew = false;
        Flags flags = ((Message) mimeMessage).getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == Flags.Flag.SEEN) {
                isnew = true;
                break;
            }
        }
        return isnew;
    }
    
    /** 判断此邮件是否包含附件 */
    public boolean isContainAttach(Part part) throws Exception{
        boolean attachflag = false;
        if (part.isMimeType(mimetypeMutipart)) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                boolean flag = disposition != null && (disposition.equals(Part.ATTACHMENT) || disposition.equals(Part.INLINE));
                if (flag) {
                    attachflag = true;
                }
                else if (mpart.isMimeType(mimetypeMutipart)) {
                    attachflag = isContainAttach((Part) mpart);
                }
                else {
                    String contype = mpart.getContentType();
                    if (contype.toLowerCase().indexOf("application") != -1) {
                        attachflag = true;
                    }
                    if (contype.toLowerCase().indexOf("name") != -1) {
                        attachflag = true;
                    }
                }
            }
        }
        else if (part.isMimeType(mimetypeMessage)) {
            attachflag = isContainAttach((Part) part.getContent());
        }
        return attachflag;
    }
    
    /** 【保存附件】 */
    public void saveAttachMent(Part part) throws Exception{
        String fileName = "";
        if (part.isMimeType(mimetypeMutipart)) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                boolean flag = disposition != null && (disposition.equals(Part.ATTACHMENT) || disposition.equals(Part.INLINE));
                if (flag) {
                    fileName = mpart.getFileName();
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                    }
                    saveFile(fileName, mpart.getInputStream());
                }
                else if (mpart.isMimeType(mimetypeMutipart)) {
                    saveAttachMent(mpart);
                }
                else {
                    fileName = mpart.getFileName();
                    if (fileName != null && fileName.toLowerCase().indexOf("GB2312") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                        saveFile(fileName, mpart.getInputStream());
                    }
                }
            }
        }
        else if (part.isMimeType(mimetypeMessage)) {
            saveAttachMent((Part) part.getContent());
        }
    }
    
    /** 【设置附件存放路径】 */
    public void setAttachPath(String attachpath){
        this.saveAttachPath = attachpath;
    }
    
    /** 【设置日期显示格式】 */
    public void setDateFormat(String format) throws Exception{
        this.dateformat = format;
    }
    
    /** 【获得附件存放路径】 */
    public String getAttachPath(){
        return saveAttachPath;
    }
    
    /** 【真正的保存附件到指定目录里】 */
    private void saveFile(String fileName,InputStream in) throws Exception{
        String osName = System.getProperty("os.name");
        String storedir = getAttachPath();
        String separator = "";
        if (osName == null) {
            osName = "";
        }
        String windows = "win";
        if (osName.toLowerCase().indexOf(windows) != -1) {
            separator = "\\";
            if (storedir == null || storedir.equals("")) {
                storedir = "c:\\tmp";
            }
        }
        else {
            separator = "/";
            storedir = "/tmp";
        }
        File storefile = new File(storedir + separator + fileName);
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(storefile));
            bis = new BufferedInputStream(in);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
                bos.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("文件保存失败!");
        } finally {
            bos.close();
            bis.close();
        }
    }
}
