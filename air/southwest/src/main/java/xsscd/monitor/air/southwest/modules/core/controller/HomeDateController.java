package xsscd.monitor.air.southwest.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.modules.core.service.impl.HomeDateServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 西南-公共信息模块
 * Created by lijie on 2018/4/20
 */
@Api(tags = "首页")
@Controller
public class HomeDateController {

    @Autowired
    private HomeDateServiceImpl service;

    @ApiOperation(value = "首页--圆点数据（实测数据、预测数据：CM模型）", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/home/getAllCityAQIData" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R getAllCityAQIData( String time, int type,int timeType) {
        if(time==null || "".equals(time)){
            //实测数据(当传输时间为空时)
            List<Map<String,Object>> data=service.getAllCityRealNewData(type);
            return new R().put("data", data);
        }

        long lt = new Long(time);
        Date date = new Date(lt);//时间戳转化为时间
        //预测数据
        if(timeType==1){
            List<Map<String,Object>> data=service.getAllCityForcastData(date,type);
            return new R().put("data", data);
        }
        //实测数据
        List<Map<String,Object>> data=service.getAllCityRealData(date,type);
        return new R().put("data", data);
    }

    /**
     * 西南预警首页数据（面板数据）
     */
    @ApiOperation(value = "首页--圆点点击弹框数据（实测数据：前14天、预测数据：后14天）", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/home/getBouncedData" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R getBouncedData( String cityName) {
        List<Map<String,Object>> data=service.getBouncedData(cityName);
        return new R().put("data", data);
    }





}
