package xsscd.monitor.air.southwest.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.modules.core.service.impl.ForcastAssessmentServiceImpl;
import xsscd.monitor.air.southwest.modules.core.service.impl.ForcastModleServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * 西南-公共信息模块
 * Created by lijie on 2018/4/20
 */
@Api(tags = "模式预报模块")
@Controller
public class ForecastModleController {

    @Autowired
    private ForcastModleServiceImpl service;

    @ApiOperation(value = "模式预报模块--城市（折线柱状图：CM模型）", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/cityForcastModle" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R getCityForcastModle( String startTime,String endTime,String city,String station,String gas,String forcastType,int day) {
        List<Map<String, Object>> data=service.getCityForcastModle( startTime, endTime, city, station, gas, forcastType, day);
        return new R().put("data", data);
    }





}
