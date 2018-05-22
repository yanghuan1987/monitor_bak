package xsscd.monitor.air.southwest.modules.system.service;

import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Menu;

public interface MenuTreeService {



    /**
     * 查询所有的菜单
     * @return
     */

    public List<Menu> findAll();
}
