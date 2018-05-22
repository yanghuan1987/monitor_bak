package xsscd.monitor.air.southwest.modules.core.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.CityDataMapper;
import xsscd.monitor.air.southwest.modules.core.service.CityDataService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dcx on 2017/8/22 0022.
 */


@Service
public class CityDataServiceImpl  implements CityDataService{
    private int day=14;//模式预测数据为15天（后期如有天数更改修改此处即可）
    private static String[] southwestCode = new String[5];
    private static String[] southwestName = new String[5];
    static {
        southwestCode[0]="50%";//重庆
        southwestCode[1]="51%";//四川
        southwestCode[2]="52%";//贵州
        southwestCode[3]="53%";//云南
        southwestCode[4]="54%";//西藏

        southwestName[0]="重庆市";//重庆
        southwestName[1]="四川省";//四川
        southwestName[2]="贵州省";//贵州
        southwestName[3]="云南省";//云南
        southwestName[4]="西藏自治区";//西藏
    }
    @Autowired
    CityDataMapper cityDataDao;

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public Object getCity() {
        return cityDataDao.getCity();
    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public Object getStation() {
        return cityDataDao.getStation();
    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  Map<String,Object> getCityStation() {

        Map<String,Object> citys= new HashMap<>();
        for(int i =0;i<southwestCode.length;i++){
            citys.put(southwestName[i],cityDataDao.getSouthwestCity(southwestCode[i]));
        }

        for(int i=0;i<citys.size();i++){
            List<Map<String,Object>> city= (List<Map<String, Object>>) citys.get(southwestName[i]);
            for(Map<String,Object> stations:city){
                String CityCode=stations.get("CityCode")+"%";
                List<String> station=new ArrayList<>();
                station.add("主城区");
                if("四川省".equals(southwestName[i])){//暂时只查询四川站点数据，待其他4个省数据增加后再行更改
                    station.addAll(cityDataDao.getSouthwestStation(CityCode));
                }
                stations.put("station",station);
            }
        }
        return citys;
    }

    //获取面板实时数据（小时）
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public Object getPanelData(String cityname) {
        try {
            Map<String,Object> cityData=cityDataDao.getcity(cityname);//获取城市数据
            String code=(cityData.get("citycode")+"").substring(0, 2);//获取城市对应省的code
            String startTime=cityData.get("time")+"";
            List<Map<String,Object>> allCityData=cityDataDao.getcityAll(code+"%",startTime);//获取到当前城市的AQI数据

            String primaryPollutant=(cityData.get("primaryPollutant")+"");
            String pollutant= "PM2.5".equals(primaryPollutant)?"PM25":primaryPollutant;
            //String concentration=cityData.get(pollutant)+"";
            cityData.put("concentration",cityData.get(pollutant)==null?"-":cityData.get(pollutant)); //判断首要污染物浓度
            //计算城市排名及排名率
            if("—".equals(cityData.get("AQI")+"")){
                cityData.put("ranking","—");
                cityData.put("rate","—");
                return "{\"state\":true,\"cityData\":" + JSON.toJSONString(cityData)+",\"allCityData\":"+ JSON.toJSONString(allCityData)+ "}";
            }
            Integer AQI=Integer.parseInt(cityData.get("AQI")+"");
            double ranking1=1.0;
            int ranking=1;
            for(Map<String,Object> map:allCityData){
                if(cityname.equals(map.get("cityname")+"")){
                    continue;
                }
                if("—".equals(map.get("AQI")+"")){
                    ranking1++;
                    continue;
                }
                Integer AQI_L=Integer.parseInt(map.get("AQI")+"");
                if(AQI_L>AQI){
                    ranking1++;//打败城市数
                }
                if(AQI_L<AQI){
                    ranking++;//被打败城市数
                }
            }
            int cityNum=allCityData.size();
            double rate=ranking1/cityNum;
            DecimalFormat df   =new   DecimalFormat("#.00");
            cityData.put("ranking",ranking);
            cityData.put("rate",df.format(rate*100)+"%");
            return "{\"state\":true,\"cityData\":" + JSON.toJSONString(cityData)+",\"allCityData\":"+ JSON.toJSONString(allCityData)+ "}";
        }catch (Exception e) {
            return "{\"state\":false,\"message\":\"对不起，系统繁忙，请稍后重试！\"}";
        }

    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public Object getAllCityAQIData1(Date time, int type, int timeType) {
        //获取当前省的所有数据
        try {
            List<Map<String,Object>> Data=new ArrayList<>();
            String startTime;
            if(type==1){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
                startTime = format.format(time);
                if(timeType==1){
                    //timeType=1时代表预测数据，读取数据中最新的预测时间，获取模式预报手动填写数据
                    Data=cityDataDao.getAllCityAQIModleRealDay();
                    String sqlTime=Data.get(0).get("time")+"";
                    if(daysBetween(sqlTime,startTime)==0){
                        Data=cityDataDao.getAllCityAQIModleRealLDay(beforeTime(time,-1));//当选择时间为当前时间时则取前一天的预测数据
                    }
                }else{
                    Data=cityDataDao.getAllCityAQIDay(startTime);
                }
            }else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH" );
                startTime = format.format(time)+":00:00";
                Data=cityDataDao.getAllCityAQIHour(startTime);
            }
            List<Map<String,Object>> CityAQIData=new ArrayList<>();
            if(Data.size()==0){
                return "{\"state\":true,\"data\":" + JSON.toJSONString(CityAQIData)+ "}";
            }
            if(timeType==1){
                //模式预测时数据处理
                String time1=  Data.get(0).get("time")+"";//获得数据库的最新数据时间
                int num=daysBetween(time1,startTime);//获得预测时间和实际时间的天数差
                for(Map<String,Object> map:Data){
                    Map<String,Object> m=new HashMap<>();
                    m.put("cityName",map.get("cityName"));
                    m.put("time1",time);
                    m.put("time",map.get("time")+"");
                    m.put("longitude",map.get("longitude")==null?"":map.get("longitude"));
                    m.put("latitude",map.get("latitude")==null?"":map.get("latitude"));
                    m.put("AQI",sepStr(map.get("AQI")+"")[num-1]);
                    m.put("PrimaryPollutant",sepStr(map.get("PrimaryPollutant")+"")[num-1]);
                    m.put("quality",sepStr(map.get("quality")+"")[num-1]);
                    CityAQIData.add(m);
                }
            }else {
                for(Map<String,Object> map:Data){
                    SimpleDateFormat sdf =new SimpleDateFormat("H");//只有时分秒

                    Map<String,Object> m=new HashMap<>();
                    m.put("cityName",map.get("cityName"));
                    m.put("time1",time);
                    m.put("time2",sdf.format(time));
                    m.put("time",map.get("time")+"");
                    m.put("longitude",map.get("longitude")==null?"-":map.get("longitude"));
                    m.put("latitude",map.get("latitude")==null?"-":map.get("latitude"));
                    m.put("SO2",map.get("SO2")+"");
                    m.put("NO2",map.get("NO2")+"");
                    m.put("PM25",map.get("PM2_5")+"");
                    m.put("PM10",map.get("PM10")+"");
                    m.put("CO",map.get("CO")+"");
                    m.put("O3",map.get("O3")+"");
                    m.put("AQI",map.get("AQI")+"");
                    CityAQIData.add(m);
                }
            }

            return "{\"state\":true,\"data\":" + JSON.toJSONString(CityAQIData)+ "}";
        } catch (Exception e) {
            return "{\"state\":false,\"message\":\"对不起，系统繁忙，请稍后重试！\"}";
        }

    }

 @Override
    @EasymisDataSource(DataSourceType.Slave)
    public Object getAllCityAQIData(Date time, int type, int timeType) {
        //获取当前省的所有数据
        try {
            List<Map<String,Object>> Data=new ArrayList<>();
            String startTime;
            if(type==1){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
                startTime = format.format(time);
                if(timeType==1){
                    //timeType=1时代表预测数据，读取数据中最新的预测时间，获取模式预报手动填写数据
                    Data=cityDataDao.getAllCityAQIModleRealDay();
                    String sqlTime=Data.get(0).get("time")+"";
                    if(daysBetween(sqlTime,startTime)==0){
                        Data=cityDataDao.getAllCityAQIModleRealLDay(beforeTime(time,-1));//当选择时间为当前时间时则取前一天的预测数据
                    }
                }else{
                    Data=cityDataDao.getAllCityAQIDay(startTime);
                }
            }else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH" );
                startTime = format.format(time)+":00:00";
                Data=cityDataDao.getAllCityAQIHour(startTime);
            }
            List<Map<String,Object>> CityAQIData=new ArrayList<>();
            if(Data.size()==0){
                return "{\"state\":true,\"data\":" + JSON.toJSONString(CityAQIData)+ "}";
            }
            if(timeType==1){
                //模式预测时数据处理
                String time1=  Data.get(0).get("time")+"";//获得数据库的最新数据时间
                int num=daysBetween(time1,startTime);//获得预测时间和实际时间的天数差
                for(Map<String,Object> map:Data){
                    Map<String,Object> m=new HashMap<>();
                    m.put("cityName",map.get("cityName"));
                    m.put("time1",time);
                    m.put("time",map.get("time")+"");
                    m.put("longitude",map.get("longitude")==null?"":map.get("longitude"));
                    m.put("latitude",map.get("latitude")==null?"":map.get("latitude"));
                    m.put("AQI",sepStr(map.get("AQI")+"")[num-1]);
                    m.put("PrimaryPollutant",sepStr(map.get("PrimaryPollutant")+"")[num-1]);
                    m.put("quality",sepStr(map.get("quality")+"")[num-1]);
                    CityAQIData.add(m);
                }
            }else {
                for(Map<String,Object> map:Data){
                    SimpleDateFormat sdf =new SimpleDateFormat("H");//只有时分秒

                    Map<String,Object> m=new HashMap<>();
                    m.put("cityName",map.get("cityName"));
                    m.put("time1",time);
                    m.put("time2",sdf.format(time));
                    m.put("time",map.get("time")+"");
                    m.put("longitude",map.get("longitude")==null?"-":map.get("longitude"));
                    m.put("latitude",map.get("latitude")==null?"-":map.get("latitude"));
                    m.put("SO2",map.get("SO2")+"");
                    m.put("NO2",map.get("NO2")+"");
                    m.put("PM25",map.get("PM2_5")+"");
                    m.put("PM10",map.get("PM10")+"");
                    m.put("CO",map.get("CO")+"");
                    m.put("O3",map.get("O3")+"");
                    m.put("AQI",map.get("AQI")+"");
                    CityAQIData.add(m);
                }
            }

            return "{\"state\":true,\"data\":" + JSON.toJSONString(CityAQIData)+ "}";
        } catch (Exception e) {
            return "{\"state\":false,\"message\":\"对不起，系统繁忙，请稍后重试！\"}";
        }

    }



    /**
     * 获取模式预报天数据
     * @param start
     * @param end
     * @param cityName
     * @param timeType :24h,48h....(依次对应：0,1,2.......)
     * @param stationName
     * @return  mapAll  "real":实测值
     */
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public Object getModelReal(Date start,Date end, String cityName,String gasAll,int timeType,String stationName,int type) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
        String startTime = format.format(start);
        String endTime = format.format(end);
        Map<String,Object> mapAll=new HashMap<>();
        Date nEnd=beforeDate(end,timeType+1);
        List<String> litTime=new ArrayList<>();//获得选择时间（加上动态添加预测的时间）
        if(type==0){
           litTime=getHourList(start,nEnd);//获得选择时间（加上动态添加预测的时间）-->小时数据
           startTime=startTime+" 00:00:000";
           endTime=endTime+" 23:00:000";
        }else {
           litTime=getTimeList(start,nEnd);//获得选择时间（加上动态添加预测的时间）
        }
        List<Map<String,Object>> realList=cityDataDao.getCityReal( cityName,startTime, endTime, stationName,gasAll,type); //实测值
        String p[]=gasAll.split("-");
        for(int j=0;j<p.length;j++){
            List<String> reals=new ArrayList<>();
            for(Map<String,Object> real:realList){
                if(!meteorological(p[j])){
                    reals.add("-");
                    continue;
                }
                reals.add(real.get(p[j])+"");
            }
            mapAll.put(p[j]+"Real",reals);//实测值
        }
        Date st =beforeDate(start,-(timeType+1));
        String nStartTime = format.format(st);
        if(type==0){
            nStartTime=nStartTime+" 00:00:000";
        }
        List<Map<String,Object>> modleDayData= cityDataDao.getModelData( cityName,  nStartTime,  endTime,  stationName,  gasAll,type);//预测数据
        for(int j=0;j<p.length;j++){
            List<String> modles=new ArrayList<>();
            for(Map<String,Object> modle:modleDayData){
                String[] modleV=sepStr(modle.get(p[j])+"");
                modles.add(modleV[timeType]);
            }
            mapAll.put(p[j],modles);//模式预测值
        }
        mapAll.put("times",litTime);
        return mapAll;
    }

    //------------------------------------------------------------------工具类--------------------------------------------------------------------------

    private String[] sepStr(String str) {
        int num=day+1;
        String[] strings = str.split("\\|", num);
        return strings;
    }

    private int getHour(String str)  {
        String h= str.substring(11, 13);
        int hour=Integer.parseInt(h);
        return hour;
    }
    /**
     *
     * @param smdate 大
     * @param bdate  小
     * @return 时间差（天数）
     * @throws ParseException
     */
    private static int daysBetween(String smdate,String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    private String[] sepStrGas(String str) {
        String[] strings = str.split("\\-", 15);
        return strings;
    }

    /**
     * 加减时间的方法
     * @param data
     * @return
     */
    private String beforeTime(Date data,int day){
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(data);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, day);  //设置为前一天
        data = calendar.getTime(); //得到前一天的时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
        String time = format.format(data);
        return time;
    }

    private Date beforeDate(Date data,int day){
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(data);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, day);  //设置为前一天
        data = calendar.getTime(); //得到前一天的时间
        return data;
    }

    /**
     *
     * @param end
     * @return 时间段内每天的时间
     */
    private List<String> getTimeList(Date start,Date end) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
        String startTime = format.format(start);
        String endTime = format.format(end);
        int day =daysBetween(startTime,endTime);
        List<String> timeList=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DAY_OF_MONTH, -1);//获取前一天的时间
        start = calendar.getTime();
        // timeList.add(format.format(start)+" 00:00:00.0");
        for(int i=0;i<day+1;i++){
            calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
            start = calendar.getTime();
            String time = format.format(start)+" 00:00:00.0";
            timeList.add(time);
        }
        return timeList;
    }

    /**
     *
     * @param end
     * @return 时间段内每小时的时间
     */
    private List<String> getHourList(Date start,Date end) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
        String startTime = format.format(start);
        String endTime = format.format(end);
        int day =daysBetween(startTime,endTime);
        List<String> timeList=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        //calendar.add(Calendar.DAY_OF_MONTH, -1);//获取前一天的时间
        for(int i=0;i<day+1;i++){
            start = calendar.getTime();
            String time = format.format(start);
            for(int j=0;j<24;j++){
                String  time1=time+" ";
                if(j<10){
                    time1+="0"+j+":00:00.0";
                }else {
                    time1+=j+":00:00.0";
                }
                timeList.add(time1);
            }
            calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
        }
        return timeList;
    }

    private Boolean meteorological(String gas){
        if("AQI".equals(gas)||"O3".equals(gas)||"PM25".equals(gas)||"PM10".equals(gas)||"NO2".equals(gas)||"SO2".equals(gas)||"CO".equals(gas)){
            return true;
        }
        return false;
    }


}
