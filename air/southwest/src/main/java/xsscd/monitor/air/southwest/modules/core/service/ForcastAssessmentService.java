package xsscd.monitor.air.southwest.modules.core.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public interface ForcastAssessmentService {
    public  List<Map<String, Object>>  getEvaluationSummary(  int startYear, int endYear,int startTime, int endTime, String[] provinces,int timeType,int day);
    public  Map<String, Object>  getForcastAudateCityDayData( String startTime , String endTime , String cityCode, int day);
    public  List<Map<String, Object>>  getExpertsAssess(  String startTime, String endTime, String cityCode);
    public  List<Map<String, Object>>  getForcastAudateCityData( String startTime , String endTime , String cityCode, int day);

}
