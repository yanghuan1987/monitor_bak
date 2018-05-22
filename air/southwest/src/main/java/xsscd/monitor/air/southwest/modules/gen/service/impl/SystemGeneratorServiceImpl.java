package xsscd.monitor.air.southwest.modules.gen.service.impl;
import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import xsscd.monitor.air.southwest.common.jdbc.BaseMap;
import xsscd.monitor.air.southwest.common.utils.Page;
import xsscd.monitor.air.southwest.modules.gen.entitys.mybatis.mapper.SysGeneratorMapper;
import xsscd.monitor.air.southwest.modules.gen.qvo.GeneratorQuery;
import xsscd.monitor.air.southwest.modules.gen.service.SystemGeneratorService;
import xsscd.monitor.air.southwest.modules.gen.utils.GenUtils;

/***
 * @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1
 */
@Service("systemGeneratorService")
public class SystemGeneratorServiceImpl implements SystemGeneratorService {
    
    @Autowired
    private SysGeneratorMapper sysGeneratorDao;
    
    @Override
    public List<Map> list(Page page,GeneratorQuery qvo) throws Exception{
        return sysGeneratorDao.list(page, qvo);
    }
    
    @Override
    public Map<String, String> queryTable(String tableName){
        return sysGeneratorDao.queryTable(tableName);
    }
    
    @Override
    public List<Map<String, String>> queryColumns(String tableName){
        return sysGeneratorDao.queryColumns(tableName);
    }
    
    @Override
    public byte[] generatorCode(String[] tableNames){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            // 查询表信息
            Map<String, String> table = queryTable(tableName);
            // 查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            // 生成代码
            try {
                GenUtils.generatorCode(table, columns, zip);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
    
    @Override
    public Map<String, String> queryGenSetting(){
    	List<Map<String, String>> data= sysGeneratorDao.queryGenSetting();
    	 Map<String, String> map = new LinkedHashMap<String, String>();
    	for(int i=0;i<data.size();i++)
    		map.put(data.get(i).get("key"), data.get(i).get("value"));
        return map;
    }
    
    @Override
    public void updateGenSetting(BaseMap<String, Object> updatemap,BaseMap<String, Object> wheremap){
        sysGeneratorDao.updateGenSetting(updatemap, wheremap);
    }
}
