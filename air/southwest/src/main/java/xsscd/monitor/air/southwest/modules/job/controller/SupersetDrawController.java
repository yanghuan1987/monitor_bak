package xsscd.monitor.air.southwest.modules.job.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisAir;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisParam;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisPollutant;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.service.ExpertForcastDataViewService;
import xsscd.monitor.air.southwest.modules.job.service.ForcastDataHourService;
import xsscd.monitor.air.southwest.modules.job.service.ForcastDataViewService;
import xsscd.monitor.air.southwest.modules.job.service.MediumLongAnalysisAirService;
import xsscd.monitor.air.southwest.modules.job.service.MediumLongAnalysisParamService;
import xsscd.monitor.air.southwest.modules.job.service.MediumLongAnalysisPollutantService;
import xsscd.monitor.air.southwest.modules.job.utils.ExcelViewWrite;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.User;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * superset获取数据接口
 *
 * @author Yanghuan
 * @date 2018年4月12日10:46:37
 */
@Api("superset获取图数据")
@Controller
@RequestMapping("/supersetDraw/get/")
public class SupersetDrawController {

    @Autowired
    private ForcastDataViewService forcastDataViewService;
    @Autowired
    private ForcastDataHourService forcastDataHourService;
    @Autowired
    private ExpertForcastDataViewService expertForcastDataViewService;
	@Autowired
	private MediumLongAnalysisParamService mediumLongAnalysisParamService;
	
	@Autowired
	private MediumLongAnalysisAirService mediumLongAnalysisAirService;

	@Autowired
	private MediumLongAnalysisPollutantService mediumLongAnalysisPollutantService;
	
    @ApiOperation(value = "获取散点图",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "getPointPic" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> getProvince(@RequestBody SelectPiontVO selectPiontVO) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        List<ForcastDataView> listDay = new ArrayList<>();
        List<ForcastDataHour> listHour = new ArrayList<>();
        List<Double[]> aqiListCMAQ = new ArrayList<>();
        List<Double[]> aqiListOPAQ = new ArrayList<>();
        List<Double[]> pm25ListCMAQ = new ArrayList<>();
        List<Double[]> pm25ListOPAQ = new ArrayList<>();
        List<Double[]> pm10ListCMAQ = new ArrayList<>();
        List<Double[]> pm10ListOPAQ = new ArrayList<>();
        List<Double[]> o3ListCMAQ = new ArrayList<>();
        List<Double[]> o3ListOPAQ = new ArrayList<>();
        List<Double[]> no2ListCMAQ = new ArrayList<>();
        List<Double[]> no2ListOPAQ = new ArrayList<>();
        List<Double[]> so2ListCMAQ = new ArrayList<>();
        List<Double[]> so2ListOPAQ = new ArrayList<>();
        List<Double[]> coListCMAQ = new ArrayList<>();
        List<Double[]> coListOPAQ = new ArrayList<>();
        if (selectPiontVO.getForcastType().equals("日均")){
            listDay = forcastDataViewService.selectByPrimaryKey(selectPiontVO);
            for (ForcastDataView forcastDataView1:listDay){
                //CMAQ-AQI
                Double[] aqiArrayCMAQ = new Double[2];
                aqiArrayCMAQ[0]=repiceZero(forcastDataView1.getaQIC());
                aqiArrayCMAQ[1]=repiceZero(forcastDataView1.getaQIR());
                aqiListCMAQ.add(aqiArrayCMAQ);
                //OPAQ-AQI
                Double[] aqiArrayOPAQ = new Double[2];
                aqiArrayOPAQ[0]=repiceZero(forcastDataView1.getaQIO());
                aqiArrayOPAQ[1]=repiceZero(forcastDataView1.getaQIR());
                aqiListOPAQ.add(aqiArrayOPAQ);
                //CMAQ-PM25
                Double[] pm25ArrayCMAQ = new Double[2];
                pm25ArrayCMAQ[0]=repiceZero(forcastDataView1.getpM25C());
                pm25ArrayCMAQ[1]=repiceZero(forcastDataView1.getpM25R());
                pm25ListCMAQ.add(pm25ArrayCMAQ);
                //OPAQ-PM25
                Double[] pm25ArrayOPAQ = new Double[2];
                pm25ArrayOPAQ[0]=repiceZero(forcastDataView1.getpM25O());
                pm25ArrayOPAQ[1]=repiceZero(forcastDataView1.getpM25R());
                pm25ListOPAQ.add(pm25ArrayOPAQ);
                //CMAQ-PM10
                Double[] pm10ArrayCMAQ = new Double[2];
                pm10ArrayCMAQ[0]=repiceZero(forcastDataView1.getpM10C());
                pm10ArrayCMAQ[1]=repiceZero(forcastDataView1.getpM10R());
                pm10ListCMAQ.add(pm10ArrayCMAQ);
                //OPAQ-PM10
                Double[] pm10ArrayOPAQ = new Double[2];
                pm10ArrayOPAQ[0]=repiceZero(forcastDataView1.getpM10O());
                pm10ArrayOPAQ[1]=repiceZero(forcastDataView1.getpM10R());
                pm10ListOPAQ.add(pm10ArrayOPAQ);

                //CMAQ-O3
                Double[] o3ArrayCMAQ = new Double[2];
                o3ArrayCMAQ[0]=repiceZero(forcastDataView1.getO3C());
                o3ArrayCMAQ[1]=repiceZero(forcastDataView1.getO3R());
                o3ListCMAQ.add(o3ArrayCMAQ);
                //OPAQ-O3
                Double[] o3ArrayOPAQ = new Double[2];
                o3ArrayOPAQ[0]=repiceZero(forcastDataView1.getO3O());
                o3ArrayOPAQ[1]=repiceZero(forcastDataView1.getO3R());
                o3ListOPAQ.add(o3ArrayOPAQ);

                //CMAQ-NO2
                Double[] no2ArrayCMAQ = new Double[2];
                no2ArrayCMAQ[0]=repiceZero(forcastDataView1.getnO2C());
                no2ArrayCMAQ[1]=repiceZero(forcastDataView1.getnO2R());
                no2ListCMAQ.add(no2ArrayCMAQ);
                //OPAQ-NO2
                Double[] no2ArrayOPAQ = new Double[2];
                no2ArrayOPAQ[0]=repiceZero(forcastDataView1.getnO2O());
                no2ArrayOPAQ[1]=repiceZero(forcastDataView1.getnO2R());
                no2ListOPAQ.add(no2ArrayOPAQ);

                //CMAQ-SO2
                Double[] so2ArrayCMAQ = new Double[2];
                so2ArrayCMAQ[0]=repiceZero(forcastDataView1.getsO2C());
                so2ArrayCMAQ[1]=repiceZero(forcastDataView1.getsO2R());
                so2ListCMAQ.add(so2ArrayCMAQ);
                //OPAQ-SO2
                Double[] so2ArrayOPAQ = new Double[2];
                so2ArrayOPAQ[0]=repiceZero(forcastDataView1.getsO2O());
                so2ArrayOPAQ[1]=repiceZero(forcastDataView1.getsO2R());
                so2ListOPAQ.add(so2ArrayOPAQ);

                //CMAQ-CO
                Double[] coArrayCMAQ = new Double[2];
                coArrayCMAQ[0]=repiceZero(forcastDataView1.getcOC());
                coArrayCMAQ[1]=repiceZero(forcastDataView1.getcOR());
                coListCMAQ.add(coArrayCMAQ);
                //OPAQ-CO
                Double[] acoArrayOPAQ = new Double[2];
                acoArrayOPAQ[0]=repiceZero(forcastDataView1.getcOO());
                acoArrayOPAQ[1]=repiceZero(forcastDataView1.getcOR());
                coListOPAQ.add(acoArrayOPAQ);
            }
            if (listDay == null){
                result.put("Result", listDay);
            }
        }else if (selectPiontVO.getForcastType().equals("小时")){
            listHour = forcastDataHourService.selectByPrimaryKey(selectPiontVO);
            for (ForcastDataHour forcastDataView1:listHour){
                //CMAQ-AQI
                Double[] aqiArrayCMAQ = new Double[2];
                aqiArrayCMAQ[0]=repiceZero(forcastDataView1.getaQIC());
                aqiArrayCMAQ[1]=repiceZero(forcastDataView1.getaQIR());
                aqiListCMAQ.add(aqiArrayCMAQ);
                //OPAQ-AQI
                Double[] aqiArrayOPAQ = new Double[2];
                aqiArrayOPAQ[0]=repiceZero(forcastDataView1.getaQIO());
                aqiArrayOPAQ[1]=repiceZero(forcastDataView1.getaQIR());
                aqiListOPAQ.add(aqiArrayOPAQ);
                //CMAQ-PM25
                Double[] pm25ArrayCMAQ = new Double[2];
                pm25ArrayCMAQ[0]=repiceZero(forcastDataView1.getpM25C());
                pm25ArrayCMAQ[1]=repiceZero(forcastDataView1.getpM25R());
                pm25ListCMAQ.add(pm25ArrayCMAQ);
                //OPAQ-PM25
                Double[] pm25ArrayOPAQ = new Double[2];
                pm25ArrayOPAQ[0]=repiceZero(forcastDataView1.getpM25O());
                pm25ArrayOPAQ[1]=repiceZero(forcastDataView1.getpM25R());
                pm25ListOPAQ.add(pm25ArrayOPAQ);
                //CMAQ-PM10
                Double[] pm10ArrayCMAQ = new Double[2];
                pm10ArrayCMAQ[0]=repiceZero(forcastDataView1.getpM10C());
                pm10ArrayCMAQ[1]=repiceZero(forcastDataView1.getpM10R());
                pm10ListCMAQ.add(pm10ArrayCMAQ);
                //OPAQ-PM10
                Double[] pm10ArrayOPAQ = new Double[2];
                pm10ArrayOPAQ[0]=repiceZero(forcastDataView1.getpM10O());
                pm10ArrayOPAQ[1]=repiceZero(forcastDataView1.getpM10R());
                pm10ListOPAQ.add(pm10ArrayOPAQ);

                //CMAQ-O3
                Double[] o3ArrayCMAQ = new Double[2];
                o3ArrayCMAQ[0]=repiceZero(forcastDataView1.getO3C());
                o3ArrayCMAQ[1]=repiceZero(forcastDataView1.getO3R());
                o3ListCMAQ.add(o3ArrayCMAQ);
                //OPAQ-O3
                Double[] o3ArrayOPAQ = new Double[2];
                o3ArrayOPAQ[0]=repiceZero(forcastDataView1.getO3O());
                o3ArrayOPAQ[1]=repiceZero(forcastDataView1.getO3R());
                o3ListOPAQ.add(o3ArrayOPAQ);

                //CMAQ-NO2
                Double[] no2ArrayCMAQ = new Double[2];
                no2ArrayCMAQ[0]=repiceZero(forcastDataView1.getnO2C());
                no2ArrayCMAQ[1]=repiceZero(forcastDataView1.getnO2R());
                no2ListCMAQ.add(no2ArrayCMAQ);
                //OPAQ-NO2
                Double[] no2ArrayOPAQ = new Double[2];
                no2ArrayOPAQ[0]=repiceZero(forcastDataView1.getnO2O());
                no2ArrayOPAQ[1]=repiceZero(forcastDataView1.getnO2R());
                no2ListOPAQ.add(no2ArrayOPAQ);

                //CMAQ-SO2
                Double[] so2ArrayCMAQ = new Double[2];
                so2ArrayCMAQ[0]=repiceZero(forcastDataView1.getsO2C());
                so2ArrayCMAQ[1]=repiceZero(forcastDataView1.getsO2R());
                so2ListCMAQ.add(so2ArrayCMAQ);
                //OPAQ-SO2
                Double[] so2ArrayOPAQ = new Double[2];
                so2ArrayOPAQ[0]=repiceZero(forcastDataView1.getsO2O());
                so2ArrayOPAQ[1]=repiceZero(forcastDataView1.getsO2R());
                so2ListOPAQ.add(so2ArrayOPAQ);

                //CMAQ-CO
                Double[] coArrayCMAQ = new Double[2];
                coArrayCMAQ[0]=repiceZero(forcastDataView1.getcOC());
                coArrayCMAQ[1]=repiceZero(forcastDataView1.getcOR());
                coListCMAQ.add(coArrayCMAQ);
                //OPAQ-CO
                Double[] acoArrayOPAQ = new Double[2];
                acoArrayOPAQ[0]=repiceZero(forcastDataView1.getcOO());
                acoArrayOPAQ[1]=repiceZero(forcastDataView1.getcOR());
                coListOPAQ.add(acoArrayOPAQ);
            }
            if (listHour == null){
                result.put("Result", listHour);
            }
        }
        if (selectPiontVO.getModel().equals("CMAQ")){
            if (selectPiontVO.getPollutant().equals("AQI")){
                result.put("Result", aqiListCMAQ);
            }else if (selectPiontVO.getPollutant().equals("PM25")){
                result.put("Result", pm25ListCMAQ);
            }else if (selectPiontVO.getPollutant().equals("PM10")){
                result.put("Result", pm10ListCMAQ);
            }else if (selectPiontVO.getPollutant().equals("O3")){
                result.put("Result", o3ListCMAQ);
            }else if (selectPiontVO.getPollutant().equals("NO2")){
                result.put("Result", no2ListCMAQ);
            }else if (selectPiontVO.getPollutant().equals("SO2")){
                result.put("Result", so2ListCMAQ);
            }else if (selectPiontVO.getPollutant().equals("CO")){
                result.put("Result", coListCMAQ);
            }
        }else if (selectPiontVO.getModel().equals("OPAQ")){
            if (selectPiontVO.getPollutant().equals("AQI")){
                result.put("Result", aqiListOPAQ);
            }else if (selectPiontVO.getPollutant().equals("PM25")){
                result.put("Result", pm25ListOPAQ);
            }else if (selectPiontVO.getPollutant().equals("PM10")){
                result.put("Result", pm10ListOPAQ);
            }else if (selectPiontVO.getPollutant().equals("O3")){
                result.put("Result", o3ListOPAQ);
            }else if (selectPiontVO.getPollutant().equals("NO2")){
                result.put("Result", no2ListOPAQ);
            }else if (selectPiontVO.getPollutant().equals("SO2")){
                result.put("Result", so2ListOPAQ);
            }else if (selectPiontVO.getPollutant().equals("CO")){
                result.put("Result", coListOPAQ);
            }
        }
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }


    @ApiOperation(value = "获取散点图",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = { "getExpertPointPic" },method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> getExpertPointPic(@RequestBody SelectPiontVO selectPiontVO) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        List<ExpertForcastDataView> listDay = new ArrayList<>();
        List<Double[]> pm25 = new ArrayList<>();
        List<Double[]> aqi = new ArrayList<>();
        listDay = expertForcastDataViewService.selectByPrimaryKey(selectPiontVO);
        for (ExpertForcastDataView expertForcastDataView:listDay){
            //PM25
            Double[] pm25Array = new Double[2];
            pm25Array[0]=repiceZero(expertForcastDataView.getpM25Y());
            pm25Array[1]=repiceZero(expertForcastDataView.getpM25R());
            pm25.add(pm25Array);
            //AQI
            BigDecimal stBigDecimal = new BigDecimal(repiceZero(expertForcastDataView.getaQIYST()));
            BigDecimal edBigDecimal = new BigDecimal(repiceZero(expertForcastDataView.getaQIYED()));
            BigDecimal midBigDecimal = stBigDecimal.add(edBigDecimal).divide(new BigDecimal(2));
            Double[] aqiArray = new Double[2];
            aqiArray[0]=midBigDecimal.doubleValue();
            aqiArray[1]=repiceZero(expertForcastDataView.getaQIR());
            aqi.add(aqiArray);
        }
        HashMap<String,List<Double[]>> resMap = new HashMap<>();
        resMap.put("PM25",pm25);
        resMap.put("AQI",aqi);
        if (listDay == null){
            result.put("Result", listDay);
        }else {
            result.put("Result", resMap);
        }
        ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        return map;
    }

    private Double repiceZero(String param){
        if (null == param || "".equals(param)){
            return 0d;
        }
        return Double.parseDouble(param);
    }
    
    

    /**
     * 导出管理员信息到Excel表
     *
     * @param idstr
     * @return
     */
    @ApiOperation(value = "中长期导出",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = "exportExcel",method = { RequestMethod.POST })
    @CrossOrigin(origins = "*")
    public ModelAndView exportExcel(@RequestBody MediumLongVO mediumLongVO) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> titles = new ArrayList<String>();

        titles.add("日期");
        titles.add("今年值");
        titles.add("去年同期值");
        dataMap.put("titles", titles);
        mediumLongVO.setDateST(mediumLongVO.getReportTime());
        mediumLongVO.setDateED(DateUtil.setDate(mediumLongVO.getReportTime(),0,0,120));//+120天
        MediumLongVO mediumLongVOLastYear = new MediumLongVO();//一年前
        mediumLongVOLastYear.setCity(mediumLongVO.getCity());
        mediumLongVOLastYear.setDateST(DateUtil.setDate(mediumLongVO.getDateST(),-1));
        mediumLongVOLastYear.setDateED(DateUtil.setDate(mediumLongVO.getDateED(),-1));
        List<MediumLongAnalysisParam> listMP = mediumLongAnalysisParamService.selectExport(mediumLongVO);
        List<MediumLongAnalysisAir> listMA = mediumLongAnalysisAirService.selectExport(mediumLongVO);
        List<MediumLongAnalysisAir> listMALast = mediumLongAnalysisAirService.selectExport(mediumLongVOLastYear);
        List<MediumLongAnalysisPollutant> listMPu = mediumLongAnalysisPollutantService.selectExport(mediumLongVO);
        List<MediumLongAnalysisPollutant> listMPuLast = mediumLongAnalysisPollutantService.selectExport(mediumLongVOLastYear);
        List<ForcastDataView> listMAQI = forcastDataViewService.selectExport(mediumLongVO);
        List<ForcastDataView> listMAQILast = forcastDataViewService.selectExport(mediumLongVOLastYear);
        
        ExcelViewWrite ev = new ExcelViewWrite(mediumLongVO,listMP,listMA,listMALast,listMPu,listMPuLast,listMAQI,listMAQILast);

        ModelAndView mv = new ModelAndView(ev, dataMap);

        return mv;
    }


    /**
     * 导出管理员信息到Excel表
     *
     * @param idstr
     * @return
     * @throws ParseException 
     */
    @ApiOperation(value = "中长期导出get",notes = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @RequestMapping(value = "exportExcelGet",method = { RequestMethod.GET })
    @CrossOrigin(origins = "*")
    public ModelAndView exportExcelGet(@RequestParam String city,@RequestParam String param,@RequestParam String reportTime) throws ParseException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> titles = new ArrayList<String>();
        MediumLongVO mediumLongVO = new MediumLongVO();
        mediumLongVO.setCity(city);
        mediumLongVO.setParam(param);
        mediumLongVO.setReportTime(DateUtil.convertToDate(reportTime));
        titles.add("日期");
        titles.add("今年值");
        titles.add("去年同期值");
        dataMap.put("titles", titles);
        mediumLongVO.setDateST(mediumLongVO.getReportTime());
        mediumLongVO.setDateED(DateUtil.setDate(mediumLongVO.getReportTime(),0,0,120));//+120天
        MediumLongVO mediumLongVOLastYear = new MediumLongVO();//一年前
        mediumLongVOLastYear.setCity(mediumLongVO.getCity());
        mediumLongVOLastYear.setDateST(DateUtil.setDate(mediumLongVO.getDateST(),-1));
        mediumLongVOLastYear.setDateED(DateUtil.setDate(mediumLongVO.getDateED(),-1));
        List<MediumLongAnalysisParam> listMP = mediumLongAnalysisParamService.selectExport(mediumLongVO);
        List<MediumLongAnalysisAir> listMA = mediumLongAnalysisAirService.selectExport(mediumLongVO);
        List<MediumLongAnalysisAir> listMALast = mediumLongAnalysisAirService.selectExport(mediumLongVOLastYear);
        List<MediumLongAnalysisPollutant> listMPu = mediumLongAnalysisPollutantService.selectExport(mediumLongVO);
        List<MediumLongAnalysisPollutant> listMPuLast = mediumLongAnalysisPollutantService.selectExport(mediumLongVOLastYear);
        List<ForcastDataView> listMAQI = forcastDataViewService.selectExport(mediumLongVO);
        List<ForcastDataView> listMAQILast = forcastDataViewService.selectExport(mediumLongVOLastYear);
        
        ExcelViewWrite ev = new ExcelViewWrite(mediumLongVO,listMP,listMA,listMALast,listMPu,listMPuLast,listMAQI,listMAQILast);

        ModelAndView mv = new ModelAndView(ev, dataMap);

        return mv;
    }

}
