package xsscd.monitor.air.southwest.modules.core.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.CityDataMapper;
import xsscd.monitor.air.southwest.modules.core.service.impl.CityDataServiceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dcx on 2017/8/18 0018.
 */


@Api(tags = "公共信息模块")
@Controller
public class XnairController {

    @Autowired
    CityDataServiceImpl cityDataService;

    @Autowired
    CityDataMapper cityDataDao;


    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/xnair/getCity" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object getCity() {
        return cityDataService.getCity();
    }

    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/xnair/getStation" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object getStation() {
        return cityDataService.getStation();
    }


    /**
     * 西南五省城市站点查询
     */
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/xnair/getCityStation" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object getCityStation() {

        return cityDataService.getCityStation();
    }

    /**
     * 西南预警首页数据（圆点数据）
     */
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/xnair/getAllCityAQIData" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @EasymisDataSource(DataSourceType.Slave)
    public Object getAllCityAQIData(String time, int type,int timeType) throws ParseException {
        if(time==null || "".equals(time)){
            Date day=new Date();
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String Data=cityDataDao.getAllCityAQIHour1();
            time=Data;
            Date date =df1.parse(time);
            return cityDataService.getAllCityAQIData(date,type,timeType);
        }
        long lt = new Long(time);
        Date date = new Date(lt);//时间戳转化为时间
        return cityDataService.getAllCityAQIData(date,type,timeType);
    }


    /**
     * 西南预警首页数据（面板数据）
     */
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/xnair/getPanelData" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object getPanelData(String cityName){
        return cityDataService.getPanelData(cityName);
    }


    /**
     * 西南预警模式预报（折线图数据加实测值：时间段）
     * @param cityName
     * @param startTime
     * @param endTime
     * @param type
     * @param gas
     * @param timeType
     * @param stationName
     * @return
     * @throws ParseException
     */
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/xnair/getModelRealData" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object getModelRealData(String cityName,String startTime,String endTime,int type,String gas,int timeType,String stationName) throws ParseException {
        long lt = new Long(startTime);
        long lt1 = new Long(endTime);
        Date start = new Date(lt);//时间戳转化为时间
        Date end = new Date(lt1);//时间戳转化为时间
        return cityDataService.getModelReal(start,end,cityName,gas,timeType,stationName,type);
    }


}
