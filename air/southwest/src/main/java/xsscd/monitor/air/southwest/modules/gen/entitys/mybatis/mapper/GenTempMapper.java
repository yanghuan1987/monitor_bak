package xsscd.monitor.air.southwest.modules.gen.entitys.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import xsscd.monitor.air.southwest.common.jdbc.BaseMap;
import xsscd.monitor.air.southwest.common.utils.Page;
import xsscd.monitor.air.southwest.modules.gen.domain.GenTempVO;
import xsscd.monitor.air.southwest.modules.gen.qvo.GenTempQuery;

public interface GenTempMapper {
    
    /**
     * 返回主键的创建
     * 
     * @throws Exception
     **/
    GenTempVO save(GenTempVO bean) throws Exception;
    
    /**
     * 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
     * 
     * @author Deament
     * @param updatemap
     *            需要更新的字段和值
     * @param wheremap
     *            更新中的条件字段和值
     * @param table_name
     *            表的名称
     **/
    // sys_role
    void update(BaseMap<String, Object> updatemap,BaseMap<String, Object> wheremap);
    
    /** 分页查询 **/
    // sys_role
    Page list(Page page,GenTempQuery qvo) throws Exception;
    
    /** 不分页查询 **/
    @Select("SELECT * FROM easymis_generator_template")  
    List<Map<String, String>> list(GenTempQuery qvo) throws Exception;
    
    /** 根据条件删除 **/
    void remove(BaseMap<String, Object> wheremap);
    
    GenTempVO get(String id);
}
