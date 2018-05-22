package xsscd.monitor.air.southwest.modules.core.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ForcastDataConditions;
import xsscd.monitor.air.southwest.modules.core.service.ForcastDataService;

/**
 * 西南-公共信息模块
 * Created by lijie on 2018/4/11
 */
@Api(tags = "公共信息模块")
@Controller
public class PublicInformationController {

    @Autowired
    private ForcastDataService service;

    @ApiOperation(value = "城市预报结果--AQI/PM2.5/03_8h/日报综合表格 子模块", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/cityForecast" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R cityForecast(@RequestParam(value="provinces[]") String[] provinces, String time,@RequestParam(value="gas[]") String[] gas, int day) {
        String timepoint=time;
        List<Map<String, Object>>  data=new ArrayList<>();
        if("0".equals(provinces[0]) || "0".equals(gas[0])){//接收为0时则直接返回空数据
            return new R().put("data", data);
        }
        String provinces1="(";
        for(int i=0;i<provinces.length;i++){
            provinces1+=provinces[i];
            if(i<provinces.length-1){
                provinces1+=",";
            }
        }
        provinces1=provinces1+")";

        data= service.getForcastAudateCity(provinces1,time,gas,day);
        return new R().put("data", data);
    }


    @RequestMapping(value = { "/core/cityForecastDay" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R cityForecastDay(String provinces,String time) {
         //String provinces1="'"+provinces.replace(",","','")+"'";
         List<String> provinces1 = Arrays.asList(provinces.split(","));
         ForcastDataConditions conditions=new ForcastDataConditions();
         long lt = new Long(time);
         Date date = new Date(lt);//时间戳转化为时间
         conditions.setForecastDate(date);
         conditions.setAid(provinces1);
         List<AreaProvince> data=service.getForcastAudateCity1(conditions);
        return new R().put("data", data);
    }

    @ApiOperation(value = "城市预报结果--评估表 子模块", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/getForcastAudateCityDay" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R getForcastAudateCityDay(@RequestParam(value="provinces[]") String[] provinces, String time) {
        String timepoint=time;
        Map<String, Object>  data=new HashMap<>();
        if("0".equals(provinces[0]) ){//接收为0时则直接返回空数据
            return new R().put("data", data);
        }
        String provinces1="(";
        for(int i=0;i<provinces.length;i++){
            provinces1+=provinces[i];
            if(i<provinces.length-1){
                provinces1+=",";
            }
        }
        provinces1=provinces1+")";
        data= service.getForcastAudateCityDay(provinces1,time);
        return new R().put("data", data);
    }


}
