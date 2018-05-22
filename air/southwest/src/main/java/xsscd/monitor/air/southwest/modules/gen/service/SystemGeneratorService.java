package xsscd.monitor.air.southwest.modules.gen.service;
import java.util.List;
import java.util.Map;

import xsscd.monitor.air.southwest.common.jdbc.BaseMap;
import xsscd.monitor.air.southwest.common.utils.Page;
import xsscd.monitor.air.southwest.modules.gen.qvo.GeneratorQuery;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:33:38
 */
public interface SystemGeneratorService {
    
    /**
     * 分页查询
     * 
     * @param page
     * @param qvo
     * @return
     * @throws Exception
     */
	List<Map>  list(Page page,GeneratorQuery qvo) throws Exception;
    
    /**
     * 查询表
     * 
     * @param tableName
     * @return
     */
    Map<String, String> queryTable(String tableName);
    
    /**
     * 查询列
     * 
     * @param tableName
     * @return
     */
    List<Map<String, String>> queryColumns(String tableName);
    
    /**
     * 生成代码
     * 
     * @param tableNames
     * @return
     */
    byte[] generatorCode(String[] tableNames);
    
    /** 获取生成配置 **/
    Map<String, String> queryGenSetting();
    
    /**
     * 更新生成设置配置
     * 
     * @param updatemap
     * @param wheremap
     */
    void updateGenSetting(BaseMap<String, Object> updatemap,BaseMap<String, Object> wheremap);
}
