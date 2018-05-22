package xsscd.monitor.air.southwest.modules.gen.entitys.mybatis.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.jdbc.core.RowMapper;

import xsscd.monitor.air.southwest.common.jdbc.BaseMap;
import xsscd.monitor.air.southwest.common.utils.Page;
import xsscd.monitor.air.southwest.modules.gen.qvo.GeneratorQuery;

public interface SysGeneratorMapper {
    
    /**
     * 分页查询
     * 
     * @param page
     * @param qvo
     * @return
     * @throws Exception
     */
	@Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables")
    List<Map> list(Page page,GeneratorQuery qvo) throws Exception;
    
    /**
     * 查询表
     * 
     * @param tableName
     * @return
     */

/*    StringBuilder sql = new StringBuilder();
    sql.append(" select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables ");
    sql.append(" where table_schema = (select database()) and table_name = '" + tableName + "'");
    Map<String, String> map = new LinkedHashMap<String, String>();
    getJdbcTemplate().query(sql.toString(), new RowMapper<Object>() {
        
        @Override
        public Object mapRow(ResultSet rs,int arg1) throws SQLException{
            map.put("tableName", rs.getString("tableName"));
            map.put("engine", rs.getString("engine"));
            map.put("tableComment", rs.getString("tableComment"));
            map.put("createTime", rs.getString("createTime"));
            return null;
        }
    });
    return map;*/
	@Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables "
			+ "where table_name =#{tableName}")  
    Map<String, String> queryTable(String tableName);
    
    /**
     * 查询列
     * 
     * @param tableName
     * @return
     */
	@Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns "
			+ " where table_name = #{tableName}  order by ordinal_position") 
    List<Map<String, String>> queryColumns(String tableName);
    
    /** 获取生成配置 **/
	
	@Select("SELECT easymis_generator.key,	easymis_generator.value FROM easymis_generator")  
	List<Map<String, String>> queryGenSetting();
    
    /**
     * 更新生成设置配置
     * 
     * @param updatemap
     * @param wheremap
     */
    void updateGenSetting(BaseMap<String, Object> updatemap,BaseMap<String, Object> wheremap);
}
