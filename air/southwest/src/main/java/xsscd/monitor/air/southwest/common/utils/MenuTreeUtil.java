package xsscd.monitor.air.southwest.common.utils;
import java.util.ArrayList;
import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeFunction;

public class MenuTreeUtil {
	public List<OrganizeFunction> menuCommon;
    public List<OrganizeFunction> list = new ArrayList<OrganizeFunction>();
      
    public List<OrganizeFunction> menuList(List<OrganizeFunction> menus){
        this.menuCommon = menus;
        for (OrganizeFunction x : menus) {
        	OrganizeFunction m = new OrganizeFunction();
            if(null!=x.getParentId()&&x.getParentId().equals("00")){
                m.setParentId(x.getParentId());
                m.setFunctionId(x.getFunctionId());
                m.setFunctionName(x.getFunctionName());
                m.setIcon(x.getIcon());
                m.setUrl(x.getUrl());
                //m.setSubFunction(menuChild(x.getFunctionId()));
                list.add(m);
            }  
        }     
        return list;  
    }  
   
    public List<OrganizeFunction> menuChild(String id){
        List<OrganizeFunction> lists = new ArrayList<OrganizeFunction>();
        for(OrganizeFunction a:menuCommon){
        	OrganizeFunction m = new OrganizeFunction();
            if(a.getParentId() == id){
                m.setParentId(a.getParentId());
                m.setFunctionId(a.getFunctionId());
                m.setFunctionName(a.getFunctionName());
                m.setIcon(a.getIcon());
                m.setUrl(a.getUrl());
                lists.add(m);
            }  
        }  
        return lists; 
} 
}
