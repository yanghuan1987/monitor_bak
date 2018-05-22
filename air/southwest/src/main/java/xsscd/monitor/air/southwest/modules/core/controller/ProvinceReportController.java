package xsscd.monitor.air.southwest.modules.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.utils.Json;
import xsscd.monitor.air.southwest.common.utils.ValidatorUtils;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ProvinceReportDto;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ProvinceReportVo;
import xsscd.monitor.air.southwest.modules.core.service.ProvinceReportSevice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.text.DateFormat;
//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;
import javax.xml.namespace.QName;
import java.lang.Integer;
//import javax.xml.rpc.ParameterMode;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMNode;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import java.rmi.RemoteException;
/**
 * 业务填报-西南区域-省报上传
 * 
 * @author Yanghuan
 * @date 2018年4月12日10:46:37
 */
@Api("省报上传")
@Controller
@RequestMapping("/airReport/report_do/")
public class ProvinceReportController {
    
    @Autowired
    private ProvinceReportSevice provinceReportSevice;
    
    @ApiOperation(value = "更新省报",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "update.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public Json update(@RequestBody ProvinceReportVo province){
        Json j = new Json();
        j.setSuccess(true);
        ValidatorUtils.validateEntity(province);
        try {
            ProvinceReportDto provinceReportDto1 = provinceReportSevice.getList(province);
            if (provinceReportDto1 == null){
                provinceReportSevice.save(province);
            }else{
                provinceReportSevice.update(province);
            }
        } catch (Exception e) {
            e.printStackTrace();
            j.setMsg("操作失败");
            return j;
        }
        j.setMsg("操作成功");
        return j;
    }
    @ApiOperation(value = "删除图片",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "deletePic.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public Json deletePic(@RequestBody ProvinceReportVo province){
        Json j = new Json();
        j.setSuccess(true);
        ValidatorUtils.validateEntity(province);
        if(null == province.getDeletePic()){
            j.setMsg("请指定图片");
            return j;
        }
        try {
            ProvinceReportDto provinceReportDto1 = provinceReportSevice.getList(province);
            if (provinceReportDto1 == null){
                provinceReportSevice.save(province);
            }else{
                provinceReportSevice.update(province);
            }
        } catch (Exception e) {
            e.printStackTrace();
            j.setMsg("操作失败");
            return j;
        }
        j.setMsg("操作成功");
        return j;
    }

    @ApiOperation(value = "获取省报",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "get.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> get(@RequestBody ProvinceReportVo provinceReportDto) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        ProvinceReportDto provinceReportDtoResult = provinceReportSevice.getList(provinceReportDto);
        result.put("Result", provinceReportDtoResult);
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }

    @ApiOperation(value = "获取省报List",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "gets.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> gets(@RequestBody ProvinceReportVo provinceReportDto) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        List<ProvinceReportDto> provinceReportDtoResult = provinceReportSevice.getLists(provinceReportDto);
        result.put("Result", provinceReportDtoResult);
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }

    @ApiOperation(value = "无更多区域预报信息图片上传",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = { "updatePicD.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public @ResponseBody Json uploadImageD(HttpServletRequest request)
            throws IOException, ServletException, Exception {
        Json j = new Json();
        j.setSuccess(true);
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        ProvinceReportVo provinceReportDto = new ProvinceReportVo();
        String isDelete = multipartRequest.getParameter("isDelete");
        byte[] data = null;
        if (null != isDelete && "true".equals(isDelete)){
            provinceReportDto.setDetailSceneryImagePath(data);
        }else {
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("image");
            // 获得文件名：
            String filename = file.getOriginalFilename();
            // 获得输入流：
            InputStream input = file.getInputStream();
            data = readInputStream(input);
            provinceReportDto.setDetailSceneryImagePath(data);
            provinceReportDto.setDetailSceneryImagePathName(filename);
        }

        String provinceCode = multipartRequest.getParameter("provinceCode");
        String creatDateST = multipartRequest.getParameter("creatDateST");
        String creatDateED = multipartRequest.getParameter("creatDateED");
        //无更多区域预报信息（请上传区域风景图）
        provinceReportDto.setProvinceCode(Integer.valueOf(provinceCode));
        provinceReportDto.setCreatDateST(DateUtil.convertToDate(creatDateST));
        provinceReportDto.setCreatDateED(DateUtil.convertToDate(creatDateED));
        provinceReportDto.setCreatDate(new Date());
        provinceReportDto.setIsReportInfo(0);
        try {
            ProvinceReportDto provinceReportDto1 = provinceReportSevice.getList(provinceReportDto);
            if (provinceReportDto1 == null){
                provinceReportSevice.save(provinceReportDto);
            }else{
                provinceReportSevice.update(provinceReportDto);
            }
            j.setObj(provinceReportDto);
        } catch (Exception e) {
            e.printStackTrace();
            j.setMsg("操作失败");
            return j;
        }
        j.setMsg("操作成功");
        return j;
    }
    @ApiOperation(value = "无辖区形势图图片上传",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = { "updatePicS.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public @ResponseBody Json uploadImageS(HttpServletRequest request)
            throws IOException, ServletException, Exception {
        Json j = new Json();
        j.setSuccess(true);
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        ProvinceReportVo provinceReportDto = new ProvinceReportVo();
        String isDelete = multipartRequest.getParameter("isDelete");
        byte[] data = null;
        if (null != isDelete && "true".equals(isDelete)){
            provinceReportDto.setSceneryImagesPath(data);
        }else {
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("image");
            // 获得文件名：
            String filename = file.getOriginalFilename();
            // 获得输入流：
            InputStream input = file.getInputStream();
            data = readInputStream(input);
            provinceReportDto.setSceneryImagesPath(data);
            provinceReportDto.setSceneryImagesPathName(filename);
        }
        String provinceCode = multipartRequest.getParameter("provinceCode");
        String creatDateST = multipartRequest.getParameter("creatDateST");
        String creatDateED = multipartRequest.getParameter("creatDateED");
        //无更多区域预报信息（请上传区域风景图）
        provinceReportDto.setProvinceCode(Integer.valueOf(provinceCode));
        provinceReportDto.setCreatDateST(DateUtil.convertToDate(creatDateST));
        provinceReportDto.setCreatDateED(DateUtil.convertToDate(creatDateED));
        provinceReportDto.setCreatDate(new Date());
        provinceReportDto.setIsPicInfo(0);
        try {
            ProvinceReportDto provinceReportDto1 = provinceReportSevice.getList(provinceReportDto);
            if (provinceReportDto1 == null){
                provinceReportSevice.save(provinceReportDto);
            }else{
                provinceReportSevice.update(provinceReportDto);
            }
            j.setObj(provinceReportDto);
        } catch (Exception e) {
            e.printStackTrace();
            j.setMsg("操作失败");
            return j;
        }
        j.setMsg("操作成功");
        return j;
    }
    @ApiOperation(value = "有辖区形势图图片上传",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = { "updatePicM.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public @ResponseBody Json uploadImageM(HttpServletRequest request)
            throws IOException, ServletException, Exception {
        Json j = new Json();
        j.setSuccess(true);
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        ProvinceReportVo provinceReportDto = new ProvinceReportVo();
        byte[] data = null;
        String isDelete = multipartRequest.getParameter("isDelete");
        if (null != isDelete && "true".equals(isDelete)){
            provinceReportDto.setMapPath(data);
        }else {
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("image");
            // 获得文件名：
            String filename = file.getOriginalFilename();
            // 获得输入流：
            InputStream input = file.getInputStream();
            data = readInputStream(input);
            provinceReportDto.setMapPath(data);
            provinceReportDto.setMapPathName(filename);

        }
        String provinceCode = multipartRequest.getParameter("provinceCode");
        String creatDateST = multipartRequest.getParameter("creatDateST");
        String creatDateED = multipartRequest.getParameter("creatDateED");
        //无更多区域预报信息（请上传区域风景图）
        provinceReportDto.setProvinceCode(Integer.valueOf(provinceCode));
        provinceReportDto.setCreatDateST(DateUtil.convertToDate(creatDateST));
        provinceReportDto.setCreatDateED(DateUtil.convertToDate(creatDateED));
        provinceReportDto.setCreatDate(new Date());
        provinceReportDto.setIsPicInfo(1);
        try {
            ProvinceReportDto provinceReportDto1 = provinceReportSevice.getList(provinceReportDto);
            if (provinceReportDto1 == null){
                provinceReportSevice.save(provinceReportDto);
            }else{
                provinceReportSevice.update(provinceReportDto);
            }
            j.setObj(provinceReportDto);
        } catch (Exception e) {
            e.printStackTrace();
            j.setMsg("操作失败");
            return j;
        }
        j.setMsg("操作成功");
        return j;
    }
    //将文件输入流转换为byte数组上传到文件服务器
    public byte[] readInputStream(InputStream inStream) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    @ApiOperation(value = "转发国家",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "sendChina.do" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> sendChina(@RequestBody Object obj) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject(obj);
        String methodName = "UploadAreaData";
       /* 四川省： 510000 dqs@123
      	 	西南区域：900070 dqs@123*/
        if (jsonObject.get("loginName").toString().equals("510000")) {
        	methodName = "UploadProvinceData";
		}
        OMElement rElement = webService("http://106.37.208.228:8083/ForecastService.asmx",jsonObject,methodName);
        result = getResults(rElement);
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
            

            OMElement warningInfo = fac.createOMElement("warningInfo", omNs);
            warningInfo.addChild(fac.createOMText(warningInfo, 
                    jsonObject.get("warningInfo").toString()));
            method.addChild(warningInfo);
            

            OMElement healthTip = fac.createOMElement("healthTip", omNs);
            healthTip.addChild(fac.createOMText(healthTip, 
                    jsonObject.get("healthTip").toString()));
            method.addChild(healthTip);
            

            OMElement moreInfo = fac.createOMElement("moreInfo", omNs);
            moreInfo.addChild(fac.createOMText(moreInfo, 
                    jsonObject.get("moreInfo").toString()));
            method.addChild(moreInfo);
            

            OMElement moreInfoGraphBytes = fac.createOMElement("moreInfoGraphBytes", omNs);
            moreInfoGraphBytes.addChild(fac.createOMText(moreInfoGraphBytes, 
                    jsonObject.get("moreInfoGraphBytes").toString()));
            method.addChild(moreInfoGraphBytes);
            

            OMElement moreInfoGraphName = fac.createOMElement("moreInfoGraphName", omNs);
            moreInfoGraphName.addChild(fac.createOMText(moreInfoGraphName, 
                    jsonObject.get("moreInfoGraphName").toString()));
            method.addChild(moreInfoGraphName);
            

            OMElement isFormGraph = fac.createOMElement("isFormGraph", omNs);
            isFormGraph.addChild(fac.createOMText(isFormGraph, 
                    jsonObject.get("isFormGraph").toString()));
            method.addChild(isFormGraph);
            

            OMElement formOrSceneryGraphBytes = fac.createOMElement("formOrSceneryGraphBytes", omNs);
            formOrSceneryGraphBytes.addChild(fac.createOMText(formOrSceneryGraphBytes, 
                    jsonObject.get("formOrSceneryGraphBytes").toString()));
            method.addChild(formOrSceneryGraphBytes);
            

            OMElement formOrSceneryGraphName = fac.createOMElement("formOrSceneryGraphName", omNs);
            formOrSceneryGraphName.addChild(fac.createOMText(formOrSceneryGraphName, 
                    jsonObject.get("formOrSceneryGraphName").toString()));
            method.addChild(formOrSceneryGraphName);
            
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
    public static Map getResults(OMElement element) {  
        if (element == null) {  
            return null;  
        }  
        Iterator iter = element.getChildElements();  
        Map map = new HashMap();  
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
    public static List getNodeList(OMElement element, String parentNode) throws java.io.IOException{  
        if (element == null || parentNode == null) {  
            return null;  
        }  
          
        List list = new ArrayList();   
        Iterator<OMElement> iter = element.getChildElements();  
        while(iter.hasNext()){  
            OMElement node = iter.next();  
            if(node.getLocalName().equals(parentNode)){  
                Map map = new HashMap();  
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
