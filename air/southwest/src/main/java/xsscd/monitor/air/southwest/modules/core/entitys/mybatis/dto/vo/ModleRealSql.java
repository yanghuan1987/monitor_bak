package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by train on 17/2/16.
 */
public class ModleRealSql {
    /**
     * 实测数据查询（真实数据）
     * @param cityName
     * @param startTime
     * @param endTime
     * @param stationName
     * @return
     */
    public String getCityReal(final String cityName,final String startTime,final String endTime,final String stationName,final String gas,final int type){
        String table;
        int tableType=2;
        String p[]=gas.split("-");
        StringBuilder sql = new StringBuilder("DECLARE @days INT, @date_start DATETIME, @date_end DATETIME,@datetime DATETIME " +
                " set @date_start=  '" +startTime+"'"+
                " set @date_end = '  " +endTime+"'");
        if(stationName==null||"".equals(stationName)||"主城区".equals(stationName)){
            if(type==1){
                //日数据查询
                table="GW_Fact_CityDay";
                tableType=2;
                sql.append(" SET @days = DATEDIFF(DAY, @DATE_START, @DATE_END)   create table #T (timepoint   DATETIME ) " );
                sql.append(" INSERT INTO #T SELECT  DATEADD(dd, number, @DATE_START) AS timepoint  FROM    master.dbo.spt_values  WHERE   type = 'p'  AND number <= @days  ");
                sql.append(" select  datetime as timepoint,*  into #B from "+table+" where cityName like'"+cityName+"%'");
            }
            if(type==0){
                //小时数据
                table="GW_Fact_CityHour";
                tableType=3;
                sql.append(" SET @days = DATEDIFF(HOUR, @DATE_START, @DATE_END)   create table #T (timepoint   DATETIME ) " );
                sql.append(" INSERT INTO #T SELECT  DATEADD(hh, number, @DATE_START) AS timepoint  FROM    master.dbo.spt_values  WHERE   type = 'p'  AND number <= @days  ");
                sql.append(" select  datetime as timepoint,*  into #B from "+table+" where cityName like'"+cityName+"%'");
            }

        }else {
            if(type==1){
                //日数据查询
                table="AQIStationDayDataPublishHistory";
                tableType=4;
                sql.append(" SET @days = DATEDIFF(DAY, @DATE_START, @DATE_END)   create table #T (timepoint   DATETIME ) " );
                sql.append(" INSERT INTO #T SELECT  DATEADD(dd, number, @DATE_START) AS timepoint  FROM    master.dbo.spt_values  WHERE   type = 'p'  AND number <= @days  ");
                sql.append(" select * into #B from "+table+" where StationCode=(SELECT StationCode FROM  Station WHERE PositionName='"+stationName+"')  and  timepoint >=@date_start and timepoint <=@date_end");
            }
            if(type==0){
                //小时数据
                table="AQIStationRealTimeDataPublishHistory";
                tableType=5;
                sql.append(" SET @days = DATEDIFF(HOUR, @DATE_START, @DATE_END)   create table #T (timepoint   DATETIME ) " );
                sql.append(" INSERT INTO #T SELECT  DATEADD(hh, number, @DATE_START) AS timepoint  FROM    master.dbo.spt_values  WHERE   type = 'p'  AND number <= @days  ");
                sql.append(" select * into #B from "+table+" where StationCode=(SELECT StationCode FROM  Station WHERE PositionName='"+stationName+"')  and  timepoint >=@date_start and timepoint <=@date_end");
            }
        }
        sql.append(" SELECT  b.timepoint");
        for(int i=0;i<p.length;i++){
            if(!meteorological(p[i])){
                continue;
            }
            sql.append(" ,(case when a."+conversion(p[i],tableType)+" is null then '-' else a."+conversion(p[i],tableType)+" end) as "+p[i]+" ");
        }
        sql.append("  FROM #B a RIGHT JOIN  #T b ON a.timepoint=b.timepoint  ORDER BY  TimePoint ASC " );
        sql.append(" DROP TABLE #T");
        sql.append(" DROP TABLE #B");
        return sql.toString();
    }

    /**
     * 模式预测数据查询
     * @param cityName
     * @param startTime
     * @param endTime
     * @param stationName
     * @return
     */
    public String getModleData(final String cityName,final String startTime,final String endTime,final String stationName,final String gas,final int type){
       String table="";
       int tableType=0;

        StringBuilder sql = new StringBuilder("DECLARE @days INT, @date_start DATETIME, @date_end DATETIME,@datetime DATETIME " +
                " set @date_start=  '" +startTime+"'"+
                " set @date_end = '  " +endTime+"'");
        if(type==1){
            //日数据查询
            table="XN_forecastModelDay";
            tableType=0;
            sql.append(" SET @days = DATEDIFF(DAY, @DATE_START, @DATE_END)   create table #T (timepoint   DATETIME ) " );
            sql.append(" INSERT INTO #T SELECT  DATEADD(dd, number, @DATE_START) AS timepoint  FROM    master.dbo.spt_values  WHERE   type = 'p'  AND number <= @days  ");
        }
        if(type==0){
            //小时数据
            table="XN_forecastModelHour";
            tableType=1;
            sql.append(" SET @days = DATEDIFF(HOUR, @DATE_START, @DATE_END)   create table #T (timepoint   DATETIME ) " );
            sql.append(" INSERT INTO #T SELECT  DATEADD(hh, number, @DATE_START) AS timepoint  FROM    master.dbo.spt_values  WHERE   type = 'p'  AND number <= @days  ");
        }
        sql.append(" select * into #B from "+table+" where cityName like'"+cityName+"%' and stationName='"+stationName+"'  and  timepoint >=@date_start and timepoint <=@date_end");
        sql.append(" SELECT  b.timepoint");
        String p[]=gas.split("-");
        for(int i=0;i<p.length;i++){
            sql.append(" ,(case when a."+conversion(p[i],tableType)+" is null then '-|-|-|-|-|-|-|-|-|-|-|-|-|-|-' else a."+conversion(p[i],tableType)+" end) as "+p[i]+" ");
        }
        sql.append("  FROM #B a RIGHT JOIN  #T b ON a.timepoint=b.timepoint  ORDER BY  TimePoint ASC " );
        sql.append(" DROP TABLE #T");
        sql.append(" DROP TABLE #B");

        return sql.toString();
    }

    public String getForcastAudateCity(final String timepoint,final String provinces){
       String sql="select c.ProvinceName,b.areaName,a.citycode,a.cityname,forecastaudit.timepoint,forecastaudit.* from southwestCity a   " +
               "    left join CityArea b ON b.id=a.areaId  " +
               "    left join Province1 c ON c.id=b.provincesId " +
               "    left join (select forecastaudit.citycode,forecastaudit.timepoint,forecastaudit.aqi,forecastaudit.content, REPLACE(forecastaudit.primarypollutant , '臭氧8小时', '臭氧') primarypollutant,REPLACE(forecastaudit.quality, '至', '或') quality,forecastset.pm2_5_dr as pm2_5 from forecastaudit    " +
               "    left join forecastset on forecastaudit.citycode=forecastset.citycode and forecastaudit.timepoint=forecastset.timepoint where forecastaudit.timepoint='"+timepoint+"')forecastaudit on a.citycode=forecastaudit.citycode  " +
               "    WHERE c.id in "+provinces+" ORDER BY  c.ProvinceName,b.areaName";
        return sql;
    }

//评估表
  public String getForcastAudateCityDay(final String timepoint,final String provinces){
       String sql="SELECT p.ProvinceName,ca.areaName,a.CityName,a.CityCode, " +
               " (case when b.GradeDescription is null then '-' else b.GradeDescription end)  quality_real, " +
               " (case when f.quality is null then '-或-|-或-|-或-|-或-|-或-|-或-|-或-' else REPLACE(f.quality, '至', '或') end) as quality from southwestCity a   " +
               " LEFT JOIN CityArea ca ON ca.id=a.areaId   " +
               " LEFT JOIN Province1 p ON p.id=ca.provincesId  " +
               " LEFT JOIN GW_Fact_CityDay b on a.CityCode1=b.citycode  " +
               " LEFT JOIN forecastaudit f on a.CityCode1/100 =f.citycode and f.timepoint=b.datetime  " +
               " WHERE b.datetime='"+timepoint+"'  and a.behalf=1  and   p.id in " +provinces+
               " ORDER BY  p.ProvinceName,ca.areaName ";
        return sql;
    }

//专家评估表--首要污染物、等级的堆叠图数据
  public String getForcastAudateCityDayData(final String startTime ,final String endTime ,final String citycode,final int day){
      int fday=-day;
       String sql="  SELECT  b.datetime,p.ProvinceName,ca.areaName,a.CityName,f.username,a.CityCode, " +
               " case when b.Chiefly_Infectant='—' then REPLACE(b.Chiefly_Infectant , '—', '无')  " +
               "                when b.Chiefly_Infectant='PM2.5' then REPLACE(b.Chiefly_Infectant , 'PM2.5', '细颗粒物(PM2.5)') " +
               "                when b.Chiefly_Infectant='PM10' then REPLACE(b.Chiefly_Infectant , 'PM10', '细颗粒物(PM10)') " +
               "                when b.Chiefly_Infectant='NO2' then REPLACE(b.Chiefly_Infectant , 'NO2', '二氧化氮') " +
               "                when b.Chiefly_Infectant='NO2' then REPLACE(b.Chiefly_Infectant , 'SO2', '二氧化硫') " +
               "                when b.Chiefly_Infectant='NO2' then REPLACE(b.Chiefly_Infectant , 'CO', '一氧化碳') " +
               "                else REPLACE(b.Chiefly_Infectant , 'O3_8', '臭氧8小时') end primarypollutant_real,   " +
               " b.GradeDescription quality_real, " +
               " dbo.GetStrArrayStrOfIndex(f.quality ,'|',"+day+") quality, " +
               " dbo.GetStrArrayStrOfIndex(f.primarypollutant ,'|',"+day+") primarypollutant " +
               "  from southwestCity a  " +
               "  LEFT JOIN CityArea ca ON ca.id=a.areaId   " +
               "  LEFT JOIN Province1 p ON p.id=ca.provincesId  " +
               "  LEFT JOIN (select * from GW_Fact_CityDay where datetime>='"+startTime+"' and datetime<='"+endTime+"') b on a.CityCode1=b.citycode   " +
               "  LEFT JOIN  " +
               "  (select forecastaudit.citycode,forecastaudit.username,DATEADD(DAY,"+day+",forecastaudit.timepoint) timepoint,forecastaudit.aqi,forecastaudit.content, forecastaudit.primarypollutant primarypollutant,REPLACE(forecastaudit.quality, '至', '或') quality,forecastset.pm2_5_dr as pm2_5 from forecastaudit    " +
               "  left join forecastset on forecastaudit.citycode=forecastset.citycode and forecastaudit.timepoint=forecastset.timepoint  " +
               "  where forecastaudit.timepoint>=DATEADD(DAY,"+fday+",'"+startTime+"') and forecastaudit.timepoint<=DATEADD(DAY,"+fday+",'"+endTime+"') " +
               "  ) f   " +
               "   on a.CityCode1/100 =f.citycode and f.timepoint=b.datetime   " +
               "    WHERE  f.citycode*100 like '"+citycode+"%'  ";//四川的code只有4位（其他4省的有6位）
        return sql;
    }
//预报评估模块--专家评估--AQI/PM25的折线柱状图
  public String getForcastAudateCityData(final String startTime ,final String endTime ,final String citycode,final int day){
      int fday=-day;
       String sql="  SELECT  b.datetime,p.ProvinceName,ca.areaName,a.CityName,f.username,a.CityCode,b.AQI AQI_R,b.PM25 PM25_R," +
               " dbo.GetStrArrayStrOfIndex(f.aqi ,'|',1) AQI, " +
               " dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'|',1) PM25 " +
               "  from southwestCity a  " +
               "  LEFT JOIN CityArea ca ON ca.id=a.areaId   " +
               "  LEFT JOIN Province1 p ON p.id=ca.provincesId  " +
               "  LEFT JOIN (select * from GW_Fact_CityDay where datetime>='"+startTime+"' and datetime<='"+endTime+"') b on a.CityCode1=b.citycode   " +
               "  LEFT JOIN  " +
               "  (select forecastaudit.citycode,forecastaudit.username,DATEADD(DAY,"+day+",forecastaudit.timepoint) timepoint,forecastaudit.aqi,forecastaudit.content, forecastset.pm2_5_dr as pm2_5 from forecastaudit    " +
               "  left join forecastset on forecastaudit.citycode=forecastset.citycode and forecastaudit.timepoint=forecastset.timepoint  " +
               "  where forecastaudit.timepoint>=DATEADD(DAY,"+fday+",'"+startTime+"') and forecastaudit.timepoint<=DATEADD(DAY,"+fday+",'"+endTime+"') " +
               "  ) f   " +
               "   on a.CityCode1/100 =f.citycode and f.timepoint=b.datetime   " +
               "    WHERE  f.citycode*100 like '"+citycode+"%'  ";//四川的code只有4位（其他4省的有6位）
        return sql;
    }

//模式预报模块--城市（折线柱状图：CM模型）
  public String getCityForcastModle(String startTime,String endTime,String city,String station,int day){
     String sql="SELECT DATE_FORMAT(predict_time,'%Y.%m.%d') time1,f.* from forcast_data_view f where predict_time<'"+endTime+"' " +
                "and predict_time>'"+startTime+"' and forcast_day="+day+" and city='"+city+"' and station='"+station+"'";
        return sql;
    }


//预报评估--评估汇总
  public String getEvaluationSummary(final int startYear, final int endYear,final int startTime1,final int endTime1,final String[] provinces,final int timeType,final int day){
           //DATENAME(quarter,AddDate)

      String time="";
      String time1="";
      String time2="";
      String startTime=startYear+"-01-01";//当选择年统计时
      String endTime=endYear+"-12-31";
       if(timeType==1){
           //月度数据统计
           startTime=timeUtil(startYear,startTime1,1);
           endTime=timeUtil(endYear,endTime1,2);
           time="month(b.datetime) monthtime,";
           time1="z.monthtime,";
           time2="+cast(z.monthtime as varchar(30))";
       }

      if(timeType==2){
       /*   startTime=startYear+"-"+(startTime1+3*(startTime1-1))+"-01";
          endTime=startYear+"-"+(3*endTime1)+"-31";*/
          startTime=timeUtil(startYear,(startTime1+3*(startTime1-1)),1);
          endTime=timeUtil(endYear,(3*endTime1),2);

           //季度数据统计
          time="DATENAME(quarter,b.datetime) quartertime,";
          time1="z.quartertime,";
          time2="+cast(z.quartertime as varchar(30))";
      }
      String provinces1="(";
      for(int i=0;i<provinces.length;i++){
          provinces1+=provinces[i];
          if(i<provinces.length-1){
              provinces1+=",";
          }
      }
      provinces1=provinces1+")";
       int fday=-day;
       String sql="SELECT cast(z.yeartime as varchar(30))"+time2+" time, z.yeartime,"+time1+" z.provinceName,z.id provinceId ,z.areaName,z.cityName,z.cityCode," +
               " (case when cast(sum(quality_day) as float)=0 then -1 else cast(sum(quality_le) as float)/cast(sum(quality_day) as float) end ) quality, " +
               " (case when cast(sum(primarypollutant_day) as float)=0 then -1 else cast(sum(primarypollutant_le) as float)/cast(sum(primarypollutant_day) as float) end ) primarypollutant, " +
               " (case when cast(sum(PM25_day) as float)=0 then -1 else cast(sum(PM25_le) as float)/cast(sum(PM25_day) as float) end ) PM25, " +
               " (case when cast(sum(aqi_day) as float)=0 then -1 else cast(sum(aqi_le) as float)/cast(sum(aqi_day) as float) end ) aqi " +
               " from ( " +
               " SELECT  year(b.datetime) yeartime,"+time+"p.ProvinceName,p.id,ca.areaName,a.CityName,a.CityCode, " +
               " (case when CHARINDEX(b.GradeDescription,f.quality)>0 and b.GradeDescription is not null and f.quality  is not null  then 1 else 0 end) quality_le, " +
               " (case when b.GradeDescription is  null or f.quality is  null then 0 else 1 end) quality_day, " +
               " (case when b.Chiefly_Infectant is  null or f.primarypollutant is  null then 0 else 1 end) primarypollutant_day, " +
               " (case when CHARINDEX(case when b.Chiefly_Infectant='—' then REPLACE(b.Chiefly_Infectant , '—', '无') else REPLACE(b.Chiefly_Infectant , 'O3_8', '臭氧') end ,f.primarypollutant)>0 and b.Chiefly_Infectant is not null and f.primarypollutant is not null  then 1 else 0 end) primarypollutant_le " +
               " ,(case when b.aqi is  null or f.aqi is  null then 0 else 1 end) aqi_day " +
               " ,(case when PATINDEX('%[^0-9|.|-|+]%',dbo.GetStrArrayStrOfIndex(f.aqi ,'~',1))<> 0 or PATINDEX('%[^0-9|.|-|+]%',dbo.GetStrArrayStrOfIndex(f.aqi ,'~',2))<>0 then 0   when cast(b.aqi as int)>=cast(dbo.GetStrArrayStrOfIndex(f.aqi ,'~',1) as int) and  cast(b.aqi as int)<=cast(dbo.GetStrArrayStrOfIndex(f.aqi ,'~',2) as int)   then 1 else 0 end) aqi_le " +
               " ,(case when b.PM25 is  null or f.pm2_5 is  null then 0 else 1 end) PM25_day " +
               " ,(case when PATINDEX('%[^0-9|.|-|+]%',dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'~',1))<> 0 or PATINDEX('%[^0-9|.|-|+]%',dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'~',2))<>0 then 0   when cast(b.PM25 as int)>=cast(dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'~',1) as int) and  cast(b.PM25 as int)<=cast(dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'~',2) as int)   then 1 else 0 end) PM25_le " +
               " from southwestCity a  " +
               " LEFT JOIN CityArea ca ON ca.id=a.areaId   " +
               " LEFT JOIN Province1 p ON p.id=ca.provincesId  " +
               " LEFT JOIN GW_Fact_CityDay b on a.CityCode1=b.citycode  " +
               " LEFT JOIN " +
               " (select forecastaudit.citycode,DATEADD(DAY,"+day+",forecastaudit.timepoint) timepoint,dbo.GetStrArrayStrOfIndex(forecastaudit.aqi ,'|',"+day+") aqi, " +
               "        REPLACE(dbo.GetStrArrayStrOfIndex(forecastaudit.primarypollutant ,'|',"+day+") , '臭氧8小时', '臭氧') primarypollutant, " +
               "        REPLACE(dbo.GetStrArrayStrOfIndex(forecastaudit.quality ,'|',"+day+"), '至', '或') quality, " +
               "        dbo.GetStrArrayStrOfIndex(forecastset.pm2_5_dr ,'|',"+day+") as pm2_5 from forecastaudit    " +
               " left join forecastset on forecastaudit.citycode=forecastset.citycode and forecastaudit.timepoint=forecastset.timepoint " +
               "  where forecastaudit.timepoint>=DATEADD(DAY,"+fday+",'"+startTime+"') and forecastaudit.timepoint<=DATEADD(DAY,"+fday+",'"+endTime+"') " +
               "   ) f " +
               " on a.CityCode1/100 =f.citycode and f.timepoint=b.datetime  " +
               " WHERE b.datetime>='"+startTime+"'  and b.datetime<'"+endTime+"' and p.id in "+provinces1+" )z " +
               " GROUP BY z.yeartime,"+time1+" z.ProvinceName,z.id,z.areaName,z.CityName,z.CityCode";
        return sql;
    }

    //预报评估--专家评估表
    public String getExpertsAssess(final String startTime, final String endTime, final String cityCode,final int day){
      int fday=-day;
      String sql=" select ProvinceName,areaName,CityName,CityCode,SUM(quality_f_day) quality_f_day," +
                "     cast(sum(primarypollutant1_day) as float)/cast(sum(quality_f_day) as float) primarypollutant1, " +
                "     cast(sum(quality_accurate) as float)/cast(sum(quality_f_day) as float)quality_accurate , " +
                "     cast(AVG(quality_hit) as float) quality_hit , " +
                "     case when sum(quality_heavy)=0 then -1 ELSE cast(sum(quality_heavy_accurate) as float)/cast(sum(quality_heavy) as float) end quality_heavy_accurate , " +
                "     case when sum(quality_heavy)=0 then -1 ELSE cast(sum(quality_heavy_false) as float)/cast(sum(quality_heavy) as float) end quality_heavy_false , " +
                "     case when sum(quality_heavy)=0 then -1 ELSE cast(sum(quality_heavy_empty) as float)/cast(sum(quality_f_heavy) as float) end quality_heavy_empty , " +
                "     AVG(quality_score) quality_score, " +
                "     AVG(pm2_5_score) pm2_5_score, " +
                "     AVG(aqi_score) aqi_score, " +
                "     AVG(primarypollutant1_score) primarypollutant1_score, " +
                "    (AVG(pm2_5_score)*0.1+AVG(aqi_score)*0.1+AVG(quality_score)*0.6+AVG(primarypollutant1_score)*0.2) score, " +
                "      " +
                "     STDEV ( pm2_5) pm2_5, " +
                "     STDEV ( pm2_5_real) pm2_5_real, " +
                "     STDEV ( aqi) aqi, " +
                "     STDEV ( aqi_real) aqi_real," +
                "     sum(abs(pm2_5-pm2_5_real))/SUM(quality_f_day) pm2_5_A, " +
                "     sum(abs(aqi-aqi_real))/SUM(quality_f_day) aqi_A, " +
                "     sum(pm2_5-pm2_5_real)/SUM(quality_f_day) pm2_5_B, " +
                "     sum(aqi-aqi_real)/SUM(quality_f_day) aqi_B, " +
                "     cast(sum(PM25_le) as float)/cast(sum(quality_f_day) as float)  PM25_accurate, " +
                "     cast(sum(aqi_le) as float)/cast(sum(quality_f_day) as float)  aqi_accurate, " +
                "     AVG(quality_f_day) m " +

                " from " +
                "(SELECT datetime,ProvinceName,areaName,CityName,CityCode,quality_real,quality1,primarypollutant_real,primarypollutant1,pm2_5,cast(pm2_5_real as float) pm2_5_real,aqi,cast(aqi_real as float)aqi_real,PM25_le,aqi_le, " +
                "        case when aqi=aqi_real then 100  when aqi<aqi_real then (1-(aqi_real-aqi)/aqi_real)*100  when aqi>aqi_real then (1-(aqi-aqi_real)/aqi)*100 ELSE 0 end aqi_score, " +
                "        case when pm2_5=pm2_5_real then 100  when pm2_5<pm2_5_real then (1-(pm2_5_real-pm2_5)/pm2_5_real)*100  when pm2_5>pm2_5_real then (1-(pm2_5-pm2_5_real)/pm2_5)*100 ELSE 0 end pm2_5_score, " +
                "        case when CHARINDEX(primarypollutant_real,primarypollutant1)>0 or ( primarypollutant_real='无' and  CHARINDEX(quality_real,quality1)>0) then 100 else 0 end primarypollutant1_score, " +
                "        case when CHARINDEX(quality_real,quality1)>0 then 100 else 0 end quality_score, " +
                "        case when quality_real is not null then 1 else 0 end quality_day, " +
                "        case when quality1 is not null then 1 else 0 end quality_f_day, " +
                "        case when CHARINDEX(primarypollutant_real,primarypollutant1)>0 or ( primarypollutant_real='无' and  CHARINDEX(quality_real,quality1)>0) then 1 else 0 end primarypollutant1_day, " +
                "        case when CHARINDEX(quality_real,quality1)>0 then 1 else 0 end quality_accurate, " +
                "        case when quality_real=quality1 then 1 when CHARINDEX(quality_real,quality1)>0 then 0.5 when quality_real=quality1 then 1 else 0 end quality_hit, " +
                "        case when quality_real='重度污染' or quality_real='严重污染'  then 1 else 0 end quality_heavy, " +
                "        case when CHARINDEX('重度污染',quality1)>0 or CHARINDEX('严重污染',quality1)>0  then 1 else 0 end quality_f_heavy, " +
                "        case when (quality_real='重度污染' or quality_real='严重污染') and (CHARINDEX('重度污染',quality1)>0 or CHARINDEX('严重污染',quality1)>0) then 1 else 0 end quality_heavy_accurate, " +
                "        case when (quality_real='重度污染' and  CHARINDEX('重度污染',quality1)=0 ) or (quality_real='严重污染' and CHARINDEX('严重污染',quality1)=0) then 1 else 0 end quality_heavy_false , " +
                "        case when (quality_real<>'重度污染' and quality_real<>'严重污染') and (CHARINDEX('重度污染',quality1)>0 or CHARINDEX('严重污染',quality1)>0) then 1 else 0 end quality_heavy_empty " +
                "        from  " +
                "     (SELECT  b.datetime,p.ProvinceName,ca.areaName,a.CityName,a.CityCode, " +
                "   b.AQI as aqi_real, " +
                "   case when b.Chiefly_Infectant='—' then REPLACE(b.Chiefly_Infectant , '—', '无') else REPLACE(b.Chiefly_Infectant , 'O3_8', '臭氧') end primarypollutant_real, " +
                "   b.GradeDescription quality_real, " +
                "   b.PM25 as pm2_5_real, " +
                "    dbo.GetStrArrayStrOfIndex(f.quality ,'|',"+day+") quality1, " +
                "                (cast(dbo.GetStrArrayStrOfIndex(dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'|',"+day+") ,'~',1) as FLOAT)+cast(dbo.GetStrArrayStrOfIndex(dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'|',"+day+") ,'~',2) as FLOAT))/2 pm2_5, " +
                "    (cast(dbo.GetStrArrayStrOfIndex(dbo.GetStrArrayStrOfIndex(f.aqi ,'|',"+day+") ,'~',1) as FLOAT)+cast(dbo.GetStrArrayStrOfIndex(dbo.GetStrArrayStrOfIndex(f.aqi ,'|',"+day+") ,'~',2) as FLOAT))/2 aqi, " +
                "     (case when cast(b.PM25 as int)>=cast(dbo.GetStrArrayStrOfIndex(dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'|',"+day+") ,'~',1) as int) and  cast(b.PM25 as int)<=cast(dbo.GetStrArrayStrOfIndex(dbo.GetStrArrayStrOfIndex(f.pm2_5 ,'|',"+day+") ,'~',2) as int)   then 1 else 0 end) PM25_le, " +
                "     (case when cast(b.aqi as int)>=cast(dbo.GetStrArrayStrOfIndex(dbo.GetStrArrayStrOfIndex(f.aqi ,'|',"+day+") ,'~',1) as int) and  cast(b.aqi as int)<=cast(dbo.GetStrArrayStrOfIndex(dbo.GetStrArrayStrOfIndex(f.aqi ,'|',"+day+") ,'~',2) as int)   then 1 else 0 end) aqi_le, " +
                "               dbo.GetStrArrayStrOfIndex(f.primarypollutant ,'|',"+day+") primarypollutant1 " +
                "                from southwestCity a  " +
                "                LEFT JOIN CityArea ca ON ca.id=a.areaId   " +
                "                LEFT JOIN Province1 p ON p.id=ca.provincesId  " +
                "                LEFT JOIN (select * from GW_Fact_CityDay where datetime>='"+startTime+"' and datetime<='"+endTime+"') b on a.CityCode1=b.citycode   " +
                "                LEFT JOIN  " +
                "                (select forecastaudit.citycode,DATEADD(DAY,"+day+",forecastaudit.timepoint) timepoint,forecastaudit.aqi,forecastaudit.content, REPLACE(forecastaudit.primarypollutant , '臭氧8小时', '臭氧') primarypollutant,REPLACE(forecastaudit.quality, '至', '或') quality,forecastset.pm2_5_dr as pm2_5 from forecastaudit     " +
                "                 left join forecastset on forecastaudit.citycode=forecastset.citycode and forecastaudit.timepoint=forecastset.timepoint  " +
                "                  where forecastaudit.timepoint>=DATEADD(DAY,"+fday+",'"+startTime+"') and forecastaudit.timepoint<=DATEADD(DAY,"+fday+",'"+endTime+"') " +
                "                  ) f   " +
                "                 on a.CityCode1/100 =f.citycode and f.timepoint=b.datetime   " +
                "                WHERE  f.citycode like '"+cityCode.substring(0,4)+"%' )  data) m " +
                "GROUP BY ProvinceName,areaName,CityName,CityCode " +
                "              ";
        return sql;
    }




    //--------------------------------------------------------工具---------------------------------------------------


    /**
     * 查询模式预测时转换参数名称（与数据库匹配）
     * @param gas
     * @param table:
     *              0:XN_forecastModelDay  模式预测（天）
     *             1:XN_forecastModelHour  模式预测（小时）
     *             2:GW_Fact_CityDay       城市实测数据（天）
     *             3:GW_Fact_CityHour       城市实测数据（小时）
     *             4:AQIStationDayDataPublishHistory   站点实测数据（天）
     *             5:AQIStationRealTimeDataPublishHistory  站点实测数据（小时）
     * @return
     */
    private String conversion(String gas,int table){
        if(table==0){
            if(("O3").equals(gas)){gas="O3_8H";}
            if(("PM25").equals(gas)){gas="PM2_5";}
        }
        if(table==1){
            if(("O3").equals(gas)){gas="O3_8H";}
            if(("PM25").equals(gas)){gas="PM2_5_24H";}
            if(("PM10").equals(gas)){gas="PM10_24H";}
        }
        if(table==2){
            if(("O3").equals(gas)){gas="O3_8";}
        }
        if(table==4 ||table==5){
            if(("O3").equals(gas)){gas="O3_1h";}
            if(("PM25").equals(gas)){gas="PM2_5_1h";}
            if(("PM10").equals(gas)){gas="PM10_1h";}
            if(("NO2").equals(gas)){gas="NO2_1h";}
            if(("SO2").equals(gas)){gas="SO2_1h";}
            if(("CO").equals(gas)){gas="CO_1h";}
        }
        return gas;
    }
 private Boolean meteorological(String gas){
        if("AQI".equals(gas)||"O3".equals(gas)||"PM25".equals(gas)||"PM10".equals(gas)||"NO2".equals(gas)||"SO2".equals(gas)||"CO".equals(gas)){
            return true;
        }
        return false;
 }

    /**
     *
     * @param year
     * @param month
     * @param type 判断是开始时间还是结束时间（结束时间在加上一个月）
     * @return
     */
 private String  timeUtil(int year,int month,int type){
     String time="";
     Calendar c1 = Calendar.getInstance();
     c1.set(year, month-1, 1);
     if(type==2){
         c1.add(Calendar.MONTH, 1);
     }
     Date date=c1.getTime();
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
     time = format.format(date);
     return time;
 }





}
