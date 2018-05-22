package xsscd.monitor.air.southwest.common.utils;

public class SimplePage {
	public static int cpn(Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}
	public static int cps(Integer pageSize) {
		return (pageSize == null || pageSize < 1) ? 10 : pageSize;
	}
}
