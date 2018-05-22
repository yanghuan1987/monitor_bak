package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ModleRealSql;

import java.util.List;
import java.util.Map;

/**
 * Created by dcx on 2017/8/22 0022.
 */
@Repository
public interface CityDataMapper {

    /**
     * 查询全国城市
     * @return
     */
    @Select("select * from southwestCity ")
    List<Map<String,Object>> getCity();

    /**
     * 查询全国站点
     * @return
     */
    @Select("select * from southwestStation ")
    Map<String,Object> getStation();


    /**
     * 查询西南五省对应城市
     * @return
     */
    @Select("select * from southwestCity where cityCode like #{cityCode}")
    List<Map<String,Object>> getSouthwestCity(@Param("cityCode") String cityCode);

    /**
     * 查询西南五省对应站点
     * @return
     */
    @Select("select stationName from southwestStation where CityCode  like #{CityCode}  and StationTypeId=1  ")
    List<String> getSouthwestStation(@Param("CityCode") String CityCode);

    /**
     * 查询全国城市数据天
     * @return
     */
    @Select("select a.datetime as time,a.cityName,a.SO2,a.NO2,a.PM10,a.PM25 AS PM2_5,a.CO,a.O3_8 AS O3,a.AQI,b.latitude,b.longitude from  GW_Fact_CityDay a LEFT JOIN CityAll b on a.citycode=b.CityCode where datetime=#{time}  ")
    List<Map<String,Object>> getAllCityAQIDay(@Param("time") String time);

    /**
     * 查询全国城市数据天预测数据
     * @return
     */
    @Select("select timepoint as time,a.cityName,a.CO,a.NO2,a.O3_8H AS O3,a.SO2,a.PM10,a.PM2_5,a.AQI,b.latitude,b.longitude from XN_forecastModelDay a LEFT JOIN CityAll b on a.cityName=b.cityName   where  timepoint=(select max(timepoint) from XN_forecastModelDay  ) and stationname='均值'  ")
    List<Map<String,Object>> getAllCityAQIModleDay();

    /**
     * 查询全国城市数据小时
     * @return
     */
    @Select("select a.datetime as time,a.cityName,a.SO2,a.NO2,a.PM10,a.PM25 AS PM2_5,a.CO,a.O3,a.AQI,b.latitude,b.longitude from  GW_Fact_CityHour a LEFT JOIN CityAll b on a.citycode=b.CityCode where datetime=#{time}  ")
    List<Map<String,Object>> getAllCityAQIHour(@Param("time") String time);

 /**
     * 查询全国城市数据小时(当前最新时间)
     * @return
     */
    @Select("select top 1 datetime from GW_Fact_CityHour order by datetime desc    ")
    String getAllCityAQIHour1();


    /**
     * 查询对应城市的cityCode
     * @return
     */
    @Select("select select datetime as time,cityName,citycode,SO2,NO2,PM10,PM25 AS PM2_5,CO,O3_8 AS O3,AQI from  GW_Fact_CityDay  where datetime=#{time} and cityname = #{cityname} ")
    Map<String,Object> getcityData(@Param("cityname") String cityname, @Param("time") String time);

    /**
     * 查询面板当前城市的数据
     * @return
     */
    @Select("select datetime AS time,citycode,cityname,SO2,NO2,PM10,CO,O3,PM25,Chiefly_Infectant AS primaryPollutant,GradeDescription,AQI from  GW_Fact_CityHour where datetime=(select max(datetime) from GW_Fact_CityHour  ) and cityname = #{cityname} ")
    Map<String,Object> getcity(@Param("cityname") String cityname);

    /**
     * 查询面板当前省所有城市的数据
     * @return
     */
    @Select("select datetime AS time,citycode,cityname,SO2,NO2,PM10,CO,O3,PM25,Chiefly_Infectant AS primaryPollutant,GradeDescription,AQI from  GW_Fact_CityHour where datetime=#{time} and citycode like #{citycode} ")
    List<Map<String,Object>> getcityAll(@Param("citycode") String code, @Param("time") String time);

    /**
     * 模式预报（城市板块数据）
     */
    @Select("select * from XN_forecastModelDay   where  timepoint=#{time} and stationname='均值' and  cityName like #{cityName}")
    Map<String,Object> getModelDay(@Param("cityName") String cityName, @Param("time") String time);

    @Select("select * from XN_forecastModelHour   where timepoint>=#{startTime} and timepoint<=#{endTime} and stationname='均值' and  cityName like #{cityName} order by timepoint asc")
    List<Map<String,Object>> getModelHour(@Param("cityName") String cityName, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询全国城市数据填报预测数据
     * 当选择时间没有值的时候则使用当钱数据库中的最新时间
     * @return
     */
    @Select("select timepoint as time,a.citycode,b.cityName,a.AQI,a.PrimaryPollutant,a.quality,b.latitude,b.longitude from forecastAudit a LEFT JOIN CityAll b on a.Citycode = b.Citycode/100   where  timepoint=(select max(timepoint) from forecastAudit  )")
    List<Map<String,Object>> getAllCityAQIModleRealDay();

    /**
     * 查询全国城市数据填报预测数据
     * @return
     */
    @Select("select timepoint as time,a.citycode,b.cityName,a.AQI,a.PrimaryPollutant,a.quality,b.latitude,b.longitude from forecastAudit a LEFT JOIN CityAll b on a.Citycode = b.Citycode/100   where  timepoint=#{time}")
    List<Map<String,Object>> getAllCityAQIModleRealLDay(@Param("time") String time);


    /**
     * 查询真实数据折线:real）
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getCityReal")
    List<Map<String,Object>> getCityReal(String cityName, String startTime, String endTime, String stationName, String gas, int type);

    /**
     * 获取模式预测选择时间段内的数据
     * @param cityName
     * @param stationName
     * @param startTime
     * @param endTime
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getModleData")
    List<Map<String,Object>> getModelData(String cityName, String startTime, String endTime, String stationName, String gas, int type);


}
