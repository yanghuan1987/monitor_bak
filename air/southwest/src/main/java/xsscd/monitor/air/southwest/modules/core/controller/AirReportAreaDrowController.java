package xsscd.monitor.air.southwest.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMNode;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.hutool.json.JSONObject;
import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.utils.Json;
import xsscd.monitor.air.southwest.common.utils.ValidatorUtils;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AirQualityLvlForecastStatistics;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.service.AirQualityLvlForecastStatisticsSevice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 业务填报-西南区域-业务填色
 *
 * @author Yanghuan
 * @date 2018年4月12日10:46:37
 */
@Api("业务填色")
@Controller
@RequestMapping("/airReport/drow_do/")
public class AirReportAreaDrowController {

    @Autowired
    private AirQualityLvlForecastStatisticsSevice airQualityLvlForecastStatisticsSevice;

    @ApiOperation(value = "更新业务填色",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "update.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public Json update(@RequestBody List<AirQualityLvlForecastStatistics> listBeans){
        Json j = new Json();
        j.setSuccess(true);
        ValidatorUtils.validateEntity(listBeans);
        try {
            airQualityLvlForecastStatisticsSevice.delete(new Date());
            airQualityLvlForecastStatisticsSevice.saveBatch(listBeans);
        } catch (Exception e) {
            e.printStackTrace();
            j.setMsg("操作失败");
            return j;
        }
        j.setMsg("操作成功");
        return j;
    }


    @ApiOperation(value = "获取业务填色",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "get.do" },method = { RequestMethod.GET, RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> get(@RequestParam(value="creatDate", required = false) String creatDate) throws Exception{
        System.out.println(creatDate);
        Date date = DateUtil.convertToDate(creatDate,"yyyy-MM-dd HH:mm:ss");
        Map<String, Object> result = new HashMap<String, Object>();
        List<AreaProvince> list = airQualityLvlForecastStatisticsSevice.getList(date);
        result.put("Result", list);
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }
    
    @ApiOperation(value = "更新业务填色国家",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "updateChina.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>>  updateChina(@RequestBody Object obj){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject(obj);
            String methodName = "UploadAreaExcelData";
           /* 四川省： 510000 dqs@123
          	 	西南区域：900070 dqs@123*/
            OMElement rElement = webService("http://106.37.208.228:8083/ForecastService.asmx",jsonObject,methodName);
            result = getResults(rElement);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
            return map;
        }

        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }

    /**
     * 发送请求
     * @param requestUrl 请求URL
     * @param jsonObject 参数
     * @param methodName 方法名
     * @return
     */
    public OMElement webService(String requestUrl,JSONObject jsonObject,String methodName) {

        OMElement result = null;
    	try {
            String url = requestUrl;
            Options options = new Options();
            EndpointReference targetEPR = new EndpointReference(url);
            options.setTo(targetEPR);
            options.setAction("http://tempuri.org/"+methodName);//需要加上这条语句
            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            OMFactory fac = OMAbstractFactory.getOMFactory();
            String tns = "http://tempuri.org/";
            OMNamespace omNs = fac.createOMNamespace(tns, "");
            OMElement method = fac.createOMElement(methodName, omNs);
            OMElement loginName = fac.createOMElement("loginName", omNs);
            loginName.addChild(fac.createOMText(loginName, 
                    jsonObject.get("loginName").toString()));
            method.addChild(loginName);
            

            OMElement password = fac.createOMElement("password", omNs);
            password.addChild(fac.createOMText(password, 
                    jsonObject.get("password").toString()));
            method.addChild(password);
            

            OMElement forecastInfo = fac.createOMElement("forecastInfo", omNs);
            forecastInfo.addChild(fac.createOMText(forecastInfo, 
                    jsonObject.get("forecastInfo").toString()));
            method.addChild(forecastInfo);
            

            OMElement dataType = fac.createOMElement("dataType", omNs);
            dataType.addChild(fac.createOMText(dataType, 
                    jsonObject.get("dataType").toString()));
            method.addChild(dataType);
            

            OMElement date = fac.createOMElement("date", omNs);
            date.addChild(fac.createOMText(date, 
                    jsonObject.get("date").toString()));
            method.addChild(date);
            
            method.build();
            result = sender.sendReceive(method);
            System.out.println(result);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
        return result;
    }
    
    /** 
     * 遍历全部节点，将节点放入Map返回 
     * @param element 
     * @return 
     */  
    public static Map<String, Object> getResults(OMElement element) {  
        if (element == null) {  
            return null;  
        }  
        @SuppressWarnings("unchecked")
		Iterator<OMElement> iter = element.getChildElements();  
        Map<String, Object> map = new HashMap<String, Object>();  
        while (iter.hasNext()) {  
            OMNode omNode = (OMNode) iter.next();  
            if (omNode.getType() == OMNode.ELEMENT_NODE) {  
                OMElement omElement = (OMElement) omNode;  
                String key = omElement.getLocalName().trim();  
                //System.out.println("sta: " + key);  
                String value = omElement.getText().trim();  
                map.put(key, value);  
            }  
        }  
        return map;  
    }  
      
    /** 
     * 遍历当前父节点下的所有子节点 
     * @param element OMElement 对象 
     * @param parentNode 父节点 
     * @return List 
     */  
    public static List<Map<String, String>> getNodeList(OMElement element, String parentNode) throws java.io.IOException{  
        if (element == null || parentNode == null) {  
            return null;  
        }  
          
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();   
        @SuppressWarnings("unchecked")
		Iterator<OMElement> iter = element.getChildElements();  
        while(iter.hasNext()){  
            OMElement node = iter.next();  
            if(node.getLocalName().equals(parentNode)){  
                Map<String, String> map = new HashMap<String, String>();  
                @SuppressWarnings("unchecked")
				Iterator<OMElement> iter1 = node.getChildElements();  
                while(iter1.hasNext()){  
                    OMElement node1 = iter1.next();  
                    map.put(node1.getLocalName(), node1.getText());  
                    //System.out.println(node1.getLocalName()+":"+node1.getText());  
                }   
                list.add(map);//System.out.println(list);  
            }  
        }  
          
        return list;  
    } 
}
