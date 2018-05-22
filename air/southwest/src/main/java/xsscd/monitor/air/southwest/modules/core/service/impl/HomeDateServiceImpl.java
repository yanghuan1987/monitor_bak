package xsscd.monitor.air.southwest.modules.core.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.HomeDataMapper;
import xsscd.monitor.air.southwest.modules.core.service.HomeDateService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
@Service
public class HomeDateServiceImpl implements HomeDateService {
    @Autowired
    HomeDataMapper mapper;

    /**
     *首页圆点实测数据
     * @param date
     * @param type
     * @return
     */
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  List<Map<String, Object>>  getAllCityRealData(Date date, int type) {
        if(type==1){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
            String time = format.format(date);
            List<Map<String, Object>> data=mapper.getAllCityRealDataDay(time); //天数据
            return data;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String time = format.format(date);
        List<Map<String, Object>> data=mapper.getAllCityRealDataHour(time);//小时数据
        return data;
    }

    /**
     *首页圆点实测数据（数据库最新时间数据）
     * @param type
     * @return
     */
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public   List<Map<String, Object>>  getAllCityRealNewData(int type) {
        if(type==1){
            List<Map<String, Object>> data=mapper.getAllCityRealDataNewDay(); //天数据
            return data;
        }
        List<Map<String, Object>> data=mapper.getAllCityRealDataNewHour();//小时数据
        return data;
    }

    /**
     *首页圆点预测数据
     * @param date
     * @param type
     * @return
     */
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  List<Map<String, Object>>  getAllCityForcastData(Date date, int type) {
        List<Map<String, Object>> data=new ArrayList<>();
        Date nowTime=mapper.getAllCityForcastDataDayTime();
        int day= timeDay(nowTime,date);//计算时间差查找时间
        if(type==1){
            data=mapper.getAllCityForcastDataDay(day); //天数据
            return data;
        }
        String time = addTimeDay(date,-day);
        data=mapper.getAllCityForcastDataHour(time,day);//小时数据
        return data;
    }

    /**
     *首页--圆点点击弹框数据（实测数据：前14天、预测数据：后14天）
     * @param cityName
     * @return
     */
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  List<Map<String, Object>>  getBouncedData(String cityName) {
        Date newTimer=mapper.getAllCityForcastDataDayTimeReal();//查出数据库中的最新实测数据时间
        String time = (new SimpleDateFormat("yyyy-MM-dd")).format(newTimer);
        List<Map<String, Object>> real=mapper.getBouncedRealData(cityName,time);//查询出前14天实测数据

        Date newTimef=mapper.getAllCityForcastDataDayTime();//查出数据库中的最新预测数据时间
        int day= timeDay(newTimef,newTimer);//计算时间差查找时间
        String timef = (new SimpleDateFormat("yyyy-MM-dd")).format(newTimef);
        Map<String, Object> forcastMap=mapper.getBouncedForcastData(cityName,timef);//查询出前14天预测测数据
        List<Map<String, Object>> forcast=new ArrayList<>();
        if(forcastMap!=null &&  forcastMap.size()>0){
            forcast=getList(forcastMap,newTimer,14, day+1);
        }
        List<Map<String, Object>> all=new ArrayList<>();
        all.addAll(real);
        all.addAll(forcast);
        return all;
    }



    //-------------------------工具类

    /**
     * 计算天数差
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    private  int timeDay(Date startTime,Date endTime)  {
        long from = startTime.getTime();
        long to =endTime.getTime();
        int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
        return days;
    }

    private  String addTimeDay(Date date,int day)  {
        String startdate="";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,day);
        date =cal.getTime();
        startdate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
        return startdate;
    }

    private  List<Map<String, Object>> getList(Map<String, Object> map,Date date,int num,int day)  {
        List<Map<String, Object>> list=new ArrayList<>();
        String[] AQI=(map.get("AQI")+"").split("\\|");
        String[] SO2=(map.get("SO2")+"").split("\\|");
        String[] CO=(map.get("CO")+"").split("\\|");
        String[] NO2=(map.get("NO2")+"").split("\\|");
        String[] O3=(map.get("O3")+"").split("\\|");
        String[] PM10=(map.get("PM10")+"").split("\\|");
        String[] PM25=(map.get("PM25")+"").split("\\|");
        String cityName=(map.get("CityName")+"");

        int j=1;
        for(int i=(day-1);i<num;i++){
            String time=addTimeDay(date,j);
            Map<String,Object> value=new HashMap<>();
            value.put("time",time);
            value.put("cityName",cityName);
            value.put("AQI",AQI[i]);
            value.put("SO2",SO2[i]);
            value.put("CO",CO[i]);
            value.put("NO2",NO2[i]);
            value.put("O3",O3[i]);
            value.put("PM10",PM10[i]);
            value.put("PM25",PM25[i]);
            list.add(value);
            j++;
        }

        return list;
    }

}
