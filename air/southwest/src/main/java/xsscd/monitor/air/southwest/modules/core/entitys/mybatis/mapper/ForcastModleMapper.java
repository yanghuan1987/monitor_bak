package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ModleRealSql;

import java.util.List;
import java.util.Map;

/**
 * Created by dcx on 2017/8/22 0022.
 */
@Repository
public interface ForcastModleMapper {


   /**
     * 查询查询专家评估表--首要污染物、等级的堆叠图数据(实测、预测：首要污染物、空气等级、预报人名)
     * @return
     */
    @SelectProvider(type= ModleRealSql.class,method = "getCityForcastModle")
    List<Map<String,Object>> getCityForcastModle(String startTime,String endTime,String city,String station,int day);




}
