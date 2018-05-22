package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ModleRealSql;

/**
 * Created by dcx on 2017/8/22 0022.
 */
@Repository
public interface ForcastAssessmentMapper {

    /**
     * 查询查询人工预报数据（AQI、PM2.5）:暂时只有四川省数据
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getEvaluationSummary")
    @ResultMap("TimeType1")
    List<Map<String,Object>> getEvaluationSummary( int startYear, int endYear,int startTime, int endTime, String[] provinces,int timeType,int day);

    /**
     * 查询查询人工预报数据（AQI、PM2.5）:暂时只有四川省数据
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getExpertsAssess")
    Map<String,Object> getExpertsAssess(String startTime, String endTime, String cityCode,int day);

    /**
     * 查询查询专家评估表--首要污染物、等级的堆叠图数据(实测、预测：首要污染物、空气等级、预报人名)
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getForcastAudateCityDayData")
    List<Map<String,Object>> getForcastAudateCityDayData( String startTime , String endTime , String citycode, int day);


  /**
     * 查询查询专家评估表--首要污染物、等级的堆叠图数据(实测、预测：首要污染物、空气等级、预报人名)
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getForcastAudateCityData")
    List<Map<String,Object>> getForcastAudateCityData( String startTime , String endTime , String citycode, int day);




}
