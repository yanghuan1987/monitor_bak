package xsscd.monitor.air.southwest.modules.gen.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xsscd.monitor.air.southwest.common.jdbc.BaseMap;
import xsscd.monitor.air.southwest.common.utils.Page;
import xsscd.monitor.air.southwest.modules.gen.domain.GenTempVO;
import xsscd.monitor.air.southwest.modules.gen.entitys.mybatis.mapper.GenTempMapper;
import xsscd.monitor.air.southwest.modules.gen.qvo.GenTempQuery;
import xsscd.monitor.air.southwest.modules.gen.service.GeneratorTemplateService;

@Service("generatorTemplateService")
public class GeneratorTemplateServiceImpl implements GeneratorTemplateService {
    
    @Autowired
    private GenTempMapper genTempDao;
    
    @Override
    /**
     * 返回主键的创建
     * 
     * @throws Exception
     **/
    public GenTempVO save(GenTempVO bean) throws Exception{
        return genTempDao.save(bean);
    }
    
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
    @Override
    public void update(BaseMap<String, Object> updatemap,BaseMap<String, Object> wheremap){
        genTempDao.update(updatemap, wheremap);
    }
    
    /** 分页查询 **/
    @Override
    public Page list(Page page,GenTempQuery qvo) throws Exception{
        return genTempDao.list(page, qvo);
    }
    
    /** 不分页查询 **/
    @Override
    public List<GenTempVO> list(GenTempQuery qvo) throws Exception{
    	List<Map<String, String>> data= genTempDao.list(qvo);
    	List<GenTempVO> templateList = new ArrayList<GenTempVO>();
   	for(int i=0;i<data.size();i++) {
   		GenTempVO vo = new GenTempVO();   		
   		vo.setDescription(data.get(i).get("description"));
   		vo.setGencontext(data.get(i).get("gencontext"));
   		vo.setGenfilename(data.get(i).get("genfilename"));
   		//vo.setGmtCreate(data.get(i).get("gmt_create"));
   		//vo.setGmtModified(data.get(i).get("gmt_modified"));
   		vo.setId(data.get(i).get("id"));
   		vo.setState(new Integer(1/*data.get(i).get("state")*/));
   		templateList.add(vo);
   	}
   	
        return templateList;
    }
    
    /** 根据条件删除 **/
    public void remove(BaseMap<String, Object> wheremap){
        genTempDao.remove(wheremap);
    }
    
    public GenTempVO get(String id){
        return genTempDao.get(id);
    }
}
