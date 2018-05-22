package xsscd.monitor.air.southwest.common.utils;
/**
 * 项目的工具类
 \* @author tanyujie
 *
 */
public class Tools {
	/**
	 * 检测字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str);
	}

	/**
	 * 检测字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}
}
