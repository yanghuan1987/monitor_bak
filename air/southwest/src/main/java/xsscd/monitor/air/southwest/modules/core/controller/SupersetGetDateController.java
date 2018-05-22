package xsscd.monitor.air.southwest.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaCity;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaStation;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.SupersetDashboardsAssembly;
import xsscd.monitor.air.southwest.modules.core.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * superset获取数据接口
 *
 * @author Yanghuan
 * @date 2018年4月12日10:46:37
 */
@Api("superset获取数据")
@Controller
@RequestMapping("java/superset/get/")
public class SupersetGetDateController {

    @Autowired
    private SupersetDashboardsAssemblyService supersetDashboardsAssemblyService;
    @Autowired
    private AreaProvinceSevice areaProvinceSevice;
    @Autowired
    private AreaCitySevice areaCitySevice;
    @Autowired
    private AreaZoneSevice areaZoneSevice;
    @Autowired
    private AreaStationSevice areaStationSevice;

    @ApiOperation(value = "看板中可选模块",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "getDashboardsItem" },method = { RequestMethod.GET, RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> get(@RequestParam(value="dashboards_id", required = false) Long dashboards_id) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        List<SupersetDashboardsAssembly> list = supersetDashboardsAssemblyService.selectByPrimaryKey(dashboards_id);
        result.put("Result", list);
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }
    @ApiOperation(value = "获取省",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "getProvince" },method = { RequestMethod.GET, RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> getProvince() throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        AreaProvince areaProvince = new AreaProvince();
        List<AreaProvince> list = areaProvinceSevice.getLists(areaProvince);
        result.put("Result", list);
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }

    @ApiOperation(value = "获取市",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "getCity" },method = { RequestMethod.GET, RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> getProvince(String provinceCode) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        AreaCity areaCity = new AreaCity();
        areaCity.setProvinceCode(Integer.valueOf(provinceCode));
        List<AreaCity> list = areaCitySevice.getLists(areaCity);
        result.put("Result", list);
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }

    @ApiOperation(value = "获取地区",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "getStation" },method = { RequestMethod.GET, RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> getStation(String cityCode) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        AreaStation areaStation = new AreaStation();
        areaStation.setCityCode(cityCode);
        List<AreaStation> list = areaStationSevice.getLists(areaStation);
        result.put("Result", list);
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }

}
