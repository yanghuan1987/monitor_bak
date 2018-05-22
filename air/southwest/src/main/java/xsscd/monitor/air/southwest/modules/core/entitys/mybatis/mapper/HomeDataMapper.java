package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ModleRealSql;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dcx on 2017/8/22 0022.
 */
@Repository
public interface HomeDataMapper {



    /**
     * 查询全国城市预测数据(当前最新时间)
     * @return
     */
    @Select("select top 1 timepoint from XN_forecastModelDay order by timepoint desc ")
    Date getAllCityForcastDataDayTime();

    /**
     * 查询全国城市实测数据(当前最新时间)
     * @return
     */
    @Select("select top 1 datetime from GW_Fact_CityDay order by datetime desc ")
    Date getAllCityForcastDataDayTimeReal();

    /**
     * 查询全国城市实测数据(当前最新时间)
     * @return
     */
    @Select("select top 1 datetime from GW_Fact_CityHour order by datetime desc ")
    Date getAllCityForcastDataHourTimeReal();



    /**
     * 查询首页圆点全国城市天数据(实测)
     * @return
     */
    @Select("select a.datetime as time,a.cityName,a.SO2,a.NO2,a.PM10,a.PM25 AS PM2_5,a.CO,a.O3_8 AS O3,a.AQI,b.latitude,b.longitude from  GW_Fact_CityDay a LEFT JOIN CityAll b on a.citycode=b.CityCode where datetime=#{time}  ")
    List<Map<String,Object>> getAllCityRealDataDay(@Param("time") String time);

    /**
     * 查询首页圆点全国城市天数据(实测:数据库最新值)
     * @return
     */
    @Select("select a.datetime as time,a.cityName,a.SO2,a.NO2,a.PM10,a.PM25 AS PM2_5,a.CO,a.O3_8 AS O3,a.AQI,b.latitude,b.longitude from  GW_Fact_CityDay a LEFT JOIN CityAll b on a.citycode=b.CityCode where datetime=(select max(datetime) from GW_Fact_CityDay  ) ")
    List<Map<String,Object>> getAllCityRealDataNewDay();

    /**
     * 查询全国城市数据小时(实测)
     * @return
     */
    @Select("select a.datetime as time,a.cityName,a.SO2,a.NO2,a.PM10,a.PM25 AS PM2_5,a.CO,a.O3,a.AQI,b.latitude,b.longitude from  GW_Fact_CityHour a LEFT JOIN CityAll b on a.citycode=b.CityCode where datetime=#{time}  ")
    List<Map<String,Object>> getAllCityRealDataHour(@Param("time") String time);

 /**
     * 查询全国城市数据小时(实测:数据库最新值)
     * @return
     */
    @Select("select a.datetime as time,a.cityName,a.SO2,a.NO2,a.PM10,a.PM25 AS PM2_5,a.CO,a.O3,a.AQI,b.latitude,b.longitude from  GW_Fact_CityHour a LEFT JOIN CityAll b on a.citycode=b.CityCode where datetime=(select max(datetime) from GW_Fact_CityHour )  ")
    List<Map<String,Object>> getAllCityRealDataNewHour();


    /**
     * 查询首页圆点全国城市天数据(预测)
     * @return
     */
    @Select("select timepoint as time ,a.cityName,a.stationname,b.latitude,b.longitude, " +
            "dbo.GetStrArrayStrOfIndex(a.CO ,'|',#{day}) CO, " +
            "dbo.GetStrArrayStrOfIndex(a.NO2 ,'|',#{day}) NO2, " +
            "dbo.GetStrArrayStrOfIndex(a.O3_8H ,'|',#{day}) O3, " +
            "dbo.GetStrArrayStrOfIndex(a.SO2 ,'|',#{day}) SO2, " +
            "dbo.GetStrArrayStrOfIndex(a.PM10 ,'|',#{day}) PM10, " +
            "dbo.GetStrArrayStrOfIndex(a.PM2_5 ,'|',#{day}) PM2_5, " +
            "dbo.GetStrArrayStrOfIndex(a.AQI ,'|',#{day}) AQI " +
            "from XN_forecastModelDay a  " +
            "LEFT JOIN CityAll b on a.cityName=b.cityName   where  timepoint=(select max(timepoint) from XN_forecastModelDay  ) and stationname='主城区'   ")
    List<Map<String,Object>> getAllCityForcastDataDay(@Param("day") int day);

    /**
     * 查询全国城市数据小时(预测)
     * @return
     */
    @Select("select timepoint  as time ,a.cityName,a.stationname,b.latitude,b.longitude," +
            "dbo.GetStrArrayStrOfIndex(a.CO ,'|',#{day}) CO," +
            "dbo.GetStrArrayStrOfIndex(a.NO2 ,'|',#{day}) NO2," +
            "dbo.GetStrArrayStrOfIndex(a.O3_8H ,'|',#{day}) O3," +
            "dbo.GetStrArrayStrOfIndex(a.SO2 ,'|',#{day}) SO2," +
            "dbo.GetStrArrayStrOfIndex(a.PM10_1H ,'|',#{day}) PM10," +
            "dbo.GetStrArrayStrOfIndex(a.PM2_5_1H ,'|',#{day}) PM2_5," +
            "dbo.GetStrArrayStrOfIndex(a.AQI ,'|',#{day}) AQI  " +
            "from XN_forecastModelHour a  " +
            "LEFT JOIN CityAll b on a.cityName=b.cityName   where  timepoint=#{time} and stationname='主城区'  ")
    List<Map<String,Object>> getAllCityForcastDataHour(@Param("time") String time,@Param("day") int day);


    /**
     * 查询首页圆点弹框当前最新数据的14天数据
     * @return
     */
    @Select("select a.datetime as time,a.cityName,a.SO2,a.NO2,a.PM10,a.PM25 AS PM2_5,a.CO,a.O3_8 AS O3,a.AQI from  GW_Fact_CityDay a  " +
            "where datetime>=DATEADD(DAY,-13,#{time})  and datetime<=#{time} and a.CityName=#{cityName} ORDER BY datetime ASC ")
    List<Map<String,Object>> getBouncedRealData(@Param("cityName") String cityName, @Param("time") String time);

   /**
     * 查询首页圆点弹框当前最新数据的14天数据
     * @return
     */
    @Select("select timepoint,CityName,AQI,CO,NO2,O3_8H AS O3,SO2,PM10,PM2_5 AS PM25 from XN_forecastModelDay   where  timepoint=#{time} and stationname='主城区'  and  SUBSTRING(CityName, 0,3) = SUBSTRING(#{cityName}, 0,3)  ")
    Map<String,Object> getBouncedForcastData(@Param("cityName") String cityName, @Param("time") String time);



}
