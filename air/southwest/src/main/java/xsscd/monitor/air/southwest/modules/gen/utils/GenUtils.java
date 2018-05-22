package xsscd.monitor.air.southwest.modules.gen.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import cn.hutool.extra.template.VelocityUtil;
import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.utils.SpringContextUtils;
import xsscd.monitor.air.southwest.modules.gen.GenCodeConstant;
import xsscd.monitor.air.southwest.modules.gen.domain.GenTempVO;
import xsscd.monitor.air.southwest.modules.gen.entity.ColumnEntity;
import xsscd.monitor.air.southwest.modules.gen.entity.TableEntity;
import xsscd.monitor.air.southwest.modules.gen.qvo.GenTempQuery;
import xsscd.monitor.air.southwest.modules.gen.service.GeneratorTemplateService;
import xsscd.monitor.air.southwest.modules.gen.service.SystemGeneratorService;
/**
 *  * 代码生成器 工具类
 * @author lenovo-t
 *
 */
public class GenUtils {
    /**
     * 生成代码
     * 
     * @param table
     * @param columns
     * @param zip
     * @throws Exception
     */
    public static void generatorCode(Map<String, String> table,List<Map<String, String>> columns,ZipOutputStream zip) throws Exception{
        // 配置信息
        Map<String, String> config = getConfig();
        // 表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        // 表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.get("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        // 列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));
            // 列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));
            // 列的数据类型，转换成Java类型
            String attrType = config.get(columnEntity.getDataType());
            if (attrType == null) {
                attrType = "unknowType";
            }
            columnEntity.setAttrType(attrType);
            // 是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }
            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);
        // 没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }
        // 设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        // 封装模板数据
        Map<String, Object> map = new HashMap<>(10);
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", config.get("pathName") + tableEntity.getClassname().toLowerCase() + "_do/");
        map.put("columns", tableEntity.getColumns());
        map.put("package", config.get("package"));
        map.put("author", config.get("author"));
        map.put("email", config.get("email"));
        map.put("datetime", DateUtil.getDate());
        VelocityContext context = new VelocityContext(map);
        // 获取模板列表
        List<GenTempVO> templates = getTemplates();
        for (GenTempVO template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            String data =VelocityUtil.merge(template.getGencontext(), context);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(dealfilename(template.getGenfilename(), map)));
                IOUtils.write(data, zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new Exception("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }
    
    /**
     * 列名转换成Java属性名 *
     * 
     * @param columnName
     * @return
     */
    public static String columnToJava(String columnName){
        return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
    }
    
    /**
     * 表名转换成Java类名
     * 
     * @param tableName
     *            表名
     * @param tablePrefix
     *            表前缀，
     * @return 表名去掉表前缀
     */
    public static String tableToJava(String tableName,String tablePrefix){
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }
    
    /**
     * 获取配置信息
     * 
     * @throws Exception
     */
    public static Map<String, String> getConfig() throws Exception{
        SystemGeneratorService service = (SystemGeneratorService)SpringContextUtils.getBean("systemGeneratorService");
        return service.queryGenSetting();
    }
    
    /**
     * 获取启用模板列表
     * 
     * @throws Exception
     */
    public static List<GenTempVO> getTemplates() throws Exception{
        GeneratorTemplateService service = (GeneratorTemplateService)SpringContextUtils.getBean("generatorTemplateService");
        GenTempQuery qvo = new GenTempQuery();
        qvo.setState(GenCodeConstant.ENABLE);
        return service.list(qvo);
    }
    
    /**
     * 替换 生成模板的filename 字段，packagePath，className，classname 为对应的路径和类
     * 
     * @param filename
     * @param map
     * @return
     */
    public static String dealfilename(String filename,Map<String, Object> map){
        // 把className 替换成大写开头的Java Bean的名称
        filename = filename.replace("className", map.get("className").toString());
        // 把classname 替换成小写开头的Java Bean的名称
        filename = filename.replace("classname", map.get("classname").toString());
        // 把packagePath 替换成对应的生成路径
        filename = filename.replace("packagePath", map.get("package").toString());
        return filename;
    }
}
