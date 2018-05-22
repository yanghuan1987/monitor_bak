package xsscd.monitor.air.southwest.common.jdbc.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期的实用方法类.
 * 
 * @author Deament
 */
public class FormatUtil {

    /**
     * 去除-，—
     * @param param
     * @return
     */
    public final static String replic(String param){
        if (param == null)
            return null;
        if ("—".equals(param) || "-".equals(param))
            return null;
        else
            return param;
    }
    /**
     * 后面补0
     * @param param
     * @param size
     * @return
     */
    public final static String appendZero(String param, int size){
        if (null == param){
            return param;
        }
        if (param.length() < size){
            StringBuffer sb = new StringBuffer();
            sb.append(param);
            for (int i=param.length();i<size;i++){
                sb.append("0");
            }
            param = sb.toString();
        }
        return param;
    }


    /**
     * 罗马数字转阿拉伯
     * @param romal
     * @return
     */
    public final static Integer romalToNumber(String romal){
        if (null == romal){
            return 0;
        }
        String[] romalArray = {"I","II","III","IV","V","VI"};
        Integer[] number = {1,2,3,4,5};
        for(int i=0; i<number.length; i++){
            if (romalArray[i].equals(romal)){
                return number[i];
            }
        }
        return 0;
    }
}
