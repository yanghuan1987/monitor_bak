package xsscd.monitor.air.southwest.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.EvaluationSummaryDto;
import xsscd.monitor.air.southwest.modules.core.service.impl.ForcastAssessmentServiceImpl;

import java.util.*;

/**
 * 西南-公共信息模块
 * Created by lijie on 2018/4/20
 */
@Api(tags = "预报评估模块")
@Controller
public class ForecastAssessmentController {

    @Autowired
    private ForcastAssessmentServiceImpl service;

    @ApiOperation(value = "预报评估模块--评估汇总", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/evaluationSummary" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R getEvaluationSummary( int startYear, int endYear,int startTime, int endTime,@RequestParam(value="provinces[]")String[] provinces,int timeType,int day ,int model) {
        List<Map<String, Object>> data=service.getEvaluationSummary(startYear,endYear,startTime,  endTime,  provinces, timeType,day);
        return new R().put("data", data);
    }

    @ApiOperation(value = "预报评估模块--专家评估（预报评估结果、综合评分、相关统计结果）", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/getExpertsAssess" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R getExpertsAssess( String startTime, String endTime, String cityCode ) {
        List<Map<String, Object>> data=service.getExpertsAssess(startTime,  endTime,  cityCode);
        return new R().put("data", data);
    }

    @ApiOperation(value = "预报评估模块--专家评估--首要污染物、等级的堆叠图(实测、预测：首要污染物、空气等级、预报人名)", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/getForcastAudateCityDayData" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R getForcastAudateCityDayData( String startTime , String endTime , String cityCode, int day) {
        Map<String, Object> data=service.getForcastAudateCityDayData(startTime,  endTime,  cityCode,day);
        return new R().put("data", data);
    }

    @ApiOperation(value = "预报评估模块--专家评估--AQI/PM25的折线柱状图(实测、预测：AQI/PM25、预报人名)", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/getForcastAudateCityData" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R getForcastAudateCityData( String startTime , String endTime , String cityCode, int day) {
        List<Map<String, Object>> data=service.getForcastAudateCityData(startTime,  endTime,  cityCode,day);
        return new R().put("data", data);
    }




}
