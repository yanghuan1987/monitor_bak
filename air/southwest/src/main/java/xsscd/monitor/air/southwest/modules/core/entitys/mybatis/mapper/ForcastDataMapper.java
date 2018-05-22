package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ForcastDataConditions;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ModleRealSql;

/**
 * Created by dcx on 2017/8/22 0022.
 */
@Repository
public interface ForcastDataMapper {

    List<AreaProvince> getForcastAudateCity1(ForcastDataConditions conditions);

    /**
     * 查询查询人工预报数据（AQI、PM2.5）:暂时只有四川省数据
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getForcastAudateCity")
    List<Map<String,String>> getForcastAudateCity(String timepoint ,String provinces);

    /**
     * 查询查询人工预报数据（评估表）:暂时只有四川省数据
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getForcastAudateCityDay")
    List<Map<String,String>> getForcastAudateCityDay(String timepoint ,String provinces);




}
