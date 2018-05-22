package xsscd.monitor.air.southwest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.hutool.cron.task.Task;
import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.jdbc.util.FormatUtil;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AirQualityLvlForecastStatistics;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ProvinceReportDto;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.vo.ProvinceReportVo;
import xsscd.monitor.air.southwest.modules.core.service.AirQualityLvlForecastStatisticsSevice;
import xsscd.monitor.air.southwest.modules.core.service.ProvinceReportSevice;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.*;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.service.*;
import xsscd.monitor.air.southwest.modules.job.task.ModelCityDayDateImportTask;
import xsscd.monitor.air.southwest.modules.job.task.ModelCityHourDateImportTask;
import xsscd.monitor.air.southwest.modules.job.task.ModelMediumLongAnalysisAirDateImportTask;
import xsscd.monitor.air.southwest.modules.job.task.ModelMediumLongAnalysisPollutantDateImportTask;
import xsscd.monitor.air.southwest.modules.job.task.PollutantAnalysisDateImportTask;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	

	private final static Logger log = LoggerFactory.getLogger(RedisTest.class);
    @Autowired
    private AirQualityLvlForecastStatisticsSevice airQualityLvlForecastStatisticsSevice;
    @Autowired
    private ForcastDataViewService forcastDataViewService;
    @Autowired
    private ForcastDataHourService forcastDataHourService;
    @Autowired
    private ForecastModelDayService forecastModelDayService;

    @Autowired
    private AQIStationRealTimeDataPublishHistoryService aqiStationRealTimeDataPublishHistoryService;

    @Autowired
    private GWFactCityHourService gwFactCityHourService;

    @Autowired
    private AQIStationDayDataPublishHistoryService aqiStationDayDataPublishHistoryService;

    @Autowired
    private GWFactCityDayService gwFactCityDayService;
    @Autowired
    private ForecastModelHourService forecastModelHourService;
    @Autowired
    private ProvinceReportSevice provinceReportSevice;
    @Autowired
    private PollutantAnalysisService pollutantAnalysisService;
    @Autowired
    private MediumLongAnalysisAirService mediumLongAnalysisAirService;

    @Autowired
    private MediumLongAnalysisPollutantService mediumLongAnalysisPollutantService;

    @Autowired
    private ExpertForcastDataViewService expertForcastDataViewService;
    @Autowired
    private ExpertFirstPollutantService expertFirstPollutantService;
    @Autowired
    private ExpertAirLvlService expertAirLvlService;
/*    private RedisUtils redisUtils;*/
@Autowired
private ForecastAuditService forecastAuditService;


@Autowired
private ExpertAirLvlCountSourceService expertAirLvlCountSourceService;

@Autowired
private ExpertAirLvlCountService expertAirLvlCountService;
@Autowired
private ModelMediumLongAnalysisAirDateImportTask modelMediumLongAnalysisAirDateImportTask;

@Autowired
private ModelCityHourDateImportTask modelCityHourDateImportTask;

@Autowired
private ModelMediumLongAnalysisPollutantDateImportTask mediumLongAnalysisPollutantDateImportTask;

@Autowired
private PollutantAnalysisDateImportTask pollutantAnalysisDateImportTask;

@Autowired
private ModelCityDayDateImportTask modelCityDayDateImportTask;


@Test
public void Task() throws ParseException {
//	modelMediumLongAnalysisAirDateImportTask.importAir();
//	mediumLongAnalysisPollutantDateImportTask.importPollutant();
//	modelCityHourDateImportTask.importHourdateMain();
	String Start = "2018-05-17 00:00:00";
	String endTime = "2018-05-23 00:00:00";
//	pollutantAnalysisDateImportTask.importDayDateMainWithParam(Start, endTime);
//	modelCityDayDateImportTask.importDaydateMainWithParam(Start, endTime);
	modelCityHourDateImportTask.importHourdateMainWithParam(Start, endTime);
}


/**
 * 预报评估，专家评估，分数图表
 */
//@Test
public void importExpertAirLvlCount(){
    SelectPiontVO selectVO = new SelectPiontVO();
    int forecastDay = 6;//预测天数
    //2017-09-18 00:00:00.000 之前预测5天
    Date forecast = DateUtil.parseSimpleDateTime("2017-09-18 00:00:00");//预测分割日
    Date edDate = DateUtil.parseSimpleDateTime("2018-05-08 00:00:00");
    Date stDate = DateUtil.parseSimpleDateTime("2018-04-30 00:00:00");
    selectVO.setDateED(edDate);
    selectVO.setDateST(stDate);
    if (stDate.compareTo(forecast)>=0){
        forecastDay=8;
    }
    for (int i = 1;i<forecastDay;i++){
        selectVO.setForcastDay(i);
        //使用sql获取数据
        List<ExpertAirLvlCountSource> list = expertAirLvlCountSourceService.select(selectVO);
        List<ExpertAirLvlCount> insertList = new ArrayList<>();
        for(ExpertAirLvlCountSource resReult:list){
            ExpertAirLvlCount expertAirLvlCount = new ExpertAirLvlCount();
            expertAirLvlCount.setCity(resReult.getCityName());
            //城市编码不足6位补足6位
            String citycode = FormatUtil.appendZero(String.valueOf(resReult.getCityCode()),6);
            expertAirLvlCount.setCityCode(citycode);
            expertAirLvlCount.setForcastDay(i);
            expertAirLvlCount.setProvince(resReult.getProvinceName());
            expertAirLvlCount.setPredictTime(resReult.getDateTime());
            expertAirLvlCount.setExpertAirQualityR(resReult.getQualityScore());
            expertAirLvlCount.setExpertAQIR(resReult.getAqiScore());
            expertAirLvlCount.setExpertFirstPollutantR(resReult.getPrimaryPollutant1Score());
            expertAirLvlCount.setExpertPM25R(resReult.getPm25Score());
            insertList.add(expertAirLvlCount);
        }
        expertAirLvlCountService.saveBatch(insertList);
    }
}

    /**
     * 污染解析导入
     */
    //@Test
    public void  importPollutantAnalysisDayDateD(){
//        int j=5264;
    	/*
    	 * 暂停，先导入2018年数据
    	 *==========Start whith
    	 *==========End whith
    	 * 
    	 * 以下是2018年数据
    	 * start = 5265
    	 * end = 179971
    	 */

        int j = 179972;
        for (int i=j+267; i<=180239;i=i+1){
        	log.debug("==========Start whith"+j);
        	log.debug("==========End whith"+i);
        	System.err.println("==========Start whith"+j);
        	System.err.println("==========End whith"+i);
        	importPollutantAnalysisDayDate(j,i);
            j=i+1;
        }
//        contextLoads1(105428,105429);
    }
    public void importPollutantAnalysisDayDate(int st,int ed) {
        ForecastModelDay forecastModelDay = new ForecastModelDay();
        forecastModelDay.setiDST(st);
        forecastModelDay.setiDED(ed);

        Integer id = null;
        try{
            List<ForecastModelDay> list = forecastModelDayService.select(forecastModelDay);
            list.size();
            List<PollutantAnalysis> viewList = new ArrayList<>();
            for (ForecastModelDay resForecastModelDay:list) {
                id = resForecastModelDay.getiD();
                String [] HNO3 = resForecastModelDay.gethNO3().split("\\|");//HNO3
                String [] NH3 = resForecastModelDay.getnH3().split("\\|");//NH3
                String [] NOX = resForecastModelDay.getnOX().split("\\|");//NOX
                String [] EC = resForecastModelDay.geteC().split("\\|");//EC
                String [] OC = resForecastModelDay.getoC().split("\\|");//OC
                String [] SO4 = resForecastModelDay.getsO4().split("\\|");//SO4
                String [] NO3 = resForecastModelDay.getnO3().split("\\|");//NO3
                String [] NH4 = resForecastModelDay.getnH4().split("\\|");//NH4
                String [] SOIL = resForecastModelDay.getSoil().split("\\|");//SOIL
                String [] OM = resForecastModelDay.getoM().split("\\|");//OM
                viewList.addAll(addDate(HNO3,resForecastModelDay,1,"HNO3"));
                viewList.addAll(addDate(NH3,resForecastModelDay,1,"NH3"));
                viewList.addAll(addDate(NOX,resForecastModelDay,1,"NOX"));
                viewList.addAll(addDate(EC,resForecastModelDay,2,"EC"));
                viewList.addAll(addDate(OC,resForecastModelDay,2,"OC"));
                viewList.addAll(addDate(SO4,resForecastModelDay,2,"SO4"));
                viewList.addAll(addDate(NO3,resForecastModelDay,2,"NO3"));
                viewList.addAll(addDate(NH4,resForecastModelDay,2,"NH4"));
                viewList.addAll(addDate(SOIL,resForecastModelDay,2,"Soil"));
                viewList.addAll(addDate(OM,resForecastModelDay,2,"OM"));
            }
            if (null != viewList && viewList.size()>0)
                pollutantAnalysisService.saveBatch(viewList);
            System.err.println("===========================执行结束=======================");
            System.err.println("===========================st======================="+id);
        }catch (Exception e){
            System.err.println("ERROR ID is"+id);
            throw e;
        }
    }

    /**
     * @param prama
     * @param resForecastModelDay
     * @param grop 1:气态组分,2:PM2.5组分
     * @param pollutant 污染物
     * @return
     */
    private List<PollutantAnalysis> addDate(String[] prama,ForecastModelDay resForecastModelDay,int grop,String pollutant){
        if (null == prama || null == resForecastModelDay){
            return null;
        }
        List<PollutantAnalysis> list = new ArrayList<>();
        for (int i=0; i<prama.length; i++){
			if (i == 14) {
				break;
			}
            PollutantAnalysis pollutantAnalysis = new PollutantAnalysis();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(resForecastModelDay.getTimepoint());
            calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+(i+1));
            pollutantAnalysis.setCity(resForecastModelDay.getCityName());
            pollutantAnalysis.setStationname(resForecastModelDay.getStationname());
            //城市编码不足6位补足6位
            String citycode = FormatUtil.appendZero(String.valueOf(resForecastModelDay.getCityCode()),6);
            pollutantAnalysis.setCityCode(citycode);
            pollutantAnalysis.setPredictTime(resForecastModelDay.getTimepoint());
            pollutantAnalysis.setForcastDay(i+1);
            pollutantAnalysis.setForcastTime(calendar.getTime());
            pollutantAnalysis.setPollutantName(pollutant);
            pollutantAnalysis.setPollutantValue(replic(prama[i]));
            if(grop == 1){
                pollutantAnalysis.setPollutantGroup("气态组分");
            }else {
                pollutantAnalysis.setPollutantGroup("PM2.5组分");
            }
            list.add(pollutantAnalysis);
        }
        return list;
    }
    /**
     * 中长期分析，空气质量等级
     */
    //@Test
    public void importAir(){
        GWFactCityDay gwFactCityDayVO = new GWFactCityDay();
        gwFactCityDayVO.setiDST(217368);
        gwFactCityDayVO.setiDED(219163);

        List<GWFactCityDay> list = gwFactCityDayService.selectByPrimaryKey(gwFactCityDayVO);
        List<MediumLongAnalysisAir> listMe = new ArrayList<>();
        for (GWFactCityDay resGWFactCityDay:list) {
            MediumLongAnalysisAir mediumLongAnalysisAir = new MediumLongAnalysisAir();
            mediumLongAnalysisAir.setCity(resGWFactCityDay.getCityname());
            //城市编码不足6位补足6位
            String citycode = appendZero(String.valueOf(resGWFactCityDay.getCitycode()),6);
            mediumLongAnalysisAir.setCityCode(citycode);
            mediumLongAnalysisAir.setLvlCode(String.valueOf(romalToNumber(resGWFactCityDay.getGrade())));
            mediumLongAnalysisAir.setLvlName(resGWFactCityDay.getGradeDescription());
            mediumLongAnalysisAir.setReportTime(resGWFactCityDay.getDatetime());
            listMe.add(mediumLongAnalysisAir);
        }
        mediumLongAnalysisAirService.saveBatch(listMe);
    }
    private Integer romalToNumber(String romal){
        if (null == romal){
            return 0;
        }
        String[] romalArray = {"I","II","III","IV","V","VI"};
        Integer[] number = {1,2,3,4,5};
        for(int i=0; i<number.length; i++){
            if (romalArray[i].equals(romal)){
                return number[i];
            }
        }
        return 0;
    }
    /**
     * 中长期分析，首要污染物
     */
    //@Test
    public void importPollutant(){
        GWFactCityDay gwFactCityDayVO = new GWFactCityDay();
        gwFactCityDayVO.setiDST(216799);
        gwFactCityDayVO.setiDED(219163);

        List<GWFactCityDay> list = gwFactCityDayService.selectByPrimaryKey(gwFactCityDayVO);
        List<MediumLongAnalysisPollutant> listMe = new ArrayList<>();
        for (GWFactCityDay resGWFactCityDay:list) {
            MediumLongAnalysisPollutant mediumLongAnalysisPollutant = new MediumLongAnalysisPollutant();
            mediumLongAnalysisPollutant.setCity(resGWFactCityDay.getCityname());
            //城市编码不足6位补足6位
            String citycode = FormatUtil.appendZero(String.valueOf(resGWFactCityDay.getCitycode()),6);
            mediumLongAnalysisPollutant.setCityCode(citycode);
            mediumLongAnalysisPollutant.setReportTime(resGWFactCityDay.getDatetime());
            String[] pollutant =  resGWFactCityDay.getChieflyInfectant().split(",");
            mediumLongAnalysisPollutant.setcO("0");
            mediumLongAnalysisPollutant.setO38h("0");
            mediumLongAnalysisPollutant.setpM25("0");
            mediumLongAnalysisPollutant.setnO2("0");
            mediumLongAnalysisPollutant.setsO2("0");
            mediumLongAnalysisPollutant.setpM10("0");
            mediumLongAnalysisPollutant.setiSNONE("0");
            for (String value:pollutant) {
                if ("CO".equals(value)){
                    mediumLongAnalysisPollutant.setcO("1");
                }
                if ("O3_8".equals(value)){
                    mediumLongAnalysisPollutant.setO38h("1");
                }
                if ("PM2.5".equals(value)){
                    mediumLongAnalysisPollutant.setpM25("1");
                }
                if ("NO2".equals(value)){
                    mediumLongAnalysisPollutant.setnO2("1");
                }
                if ("SO2".equals(value)){
                    mediumLongAnalysisPollutant.setsO2("1");
                }
                if ("PM10".equals(value)){
                    mediumLongAnalysisPollutant.setpM10("1");
                }
                if ("—".equals(value)){
                    mediumLongAnalysisPollutant.setiSNONE("1");
                }
            }
            listMe.add(mediumLongAnalysisPollutant);
        }
        mediumLongAnalysisPollutantService.saveBatch(listMe);
    }
    //@Test
    public void testRe() throws ParseException{

		Date edDate = new Date();//当前时间
		Long edDateL = edDate.getTime();
		Long stDate = DateUtil.setDate(edDate,0,0,0,-2).getTime();//二个小时以前

		String stredDate= DateUtil.convertToStrwithformat(new Date(stDate),"yyyy-MM-dd HH:mm");
		Date dateStart = DateUtil.convertToDate(stredDate+":00","yyyy-MM-dd HH:mm:ss");//YYYY-MM-DD
    	
        ProvinceReportDto provinceReportDto = new ProvinceReportDto();
        byte [] d = {'f','e','d','v'};
        provinceReportDto.setMapPath(d);
        provinceReportDto.setDetailSceneryImagePath(d);
        provinceReportDto.setSceneryImagesPath(d);
        provinceReportDto.setForecastInfo("dasdasdasdasdasd");
        provinceReportDto.setOtherInfo("dsavvbcxb");
        provinceReportDto.setHealthTips("fdgvfxcbvfdsdfg");
        provinceReportDto.setWarningInfo("13155555555");
        provinceReportDto.setIshealthTips(0);
        provinceReportDto.setIsPicInfo(0);
        provinceReportDto.setIsReportInfo(0);
        provinceReportDto.setIsWarning(0);
        provinceReportDto.setProvinceCode(3333);
        ProvinceReportVo province = new ProvinceReportVo();
        province.setProvinceCode(333);
        try {
            province.setCreatDateST(DateUtil.convertToDate("2018-04-17 00:00:00","yyyy-MM-dd HH:mm:ss"));
            province.setCreatDateED(DateUtil.convertToDate("2018-04-17 00:00:00","yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        province.setCreatName("44");
        ProvinceReportDto provinceReportDto1 = provinceReportSevice.getList(province);
        if (provinceReportDto1 == null){
            provinceReportSevice.save(provinceReportDto);
        }else{
            provinceReportSevice.update(provinceReportDto);
        }

    }

    /**
     * 预测模式-城市1,2 日数据导入
     */
    //@Test
    public void  importDate(){
//        int j=5264;
        int j = 179972;
        for (int i=j+267; i<=180239;i=i+1){
        	log.debug("==========Start whith"+j);
        	log.debug("==========End whith"+i);
        	System.err.println("==========Start whith"+j);
        	System.err.println("==========End whith"+i);
            importDayDate(j,i);
            j=i+1;
        }
//        contextLoads1(105428,105429);
    }


    /**
     * 预测模式-城市1,2 小时据导入
     */
    //@Test
    public void  importHourDateA(){
//        int j=5264;
    	/*
    	 * 暂停，先导入2018年数据
    	 *==========Start whith3624745
    	 *==========End whith3625244
    	 * 
    	 * 以下是最近一次年数据
    	 * start = 4252594
    	 * end = 4259025
    	 */
    	
        int j = 4259026;
        for (int i=j+431; i<=4265457;i=i+500){
        	log.debug("==========Start whith"+j);
        	log.debug("==========End whith"+i);
        	System.err.println("==========Start whith"+j);
        	System.err.println("==========End whith"+i);
        	importHourDate(j,i);
            j=i+1;
        }
//        contextLoads1(105428,105429);
    }
//    //@Test
    public void importHourDate(int st,int ed) {
        ForecastModelHour forecastModelHour = new ForecastModelHour();
        forecastModelHour.setiDST(st);
        forecastModelHour.setiDED(ed);

        Integer id = null;
        try{
            List<ForecastModelHour> list = forecastModelHourService.select(forecastModelHour);
            list.size();
            List<ForcastDataHour> viewList = new ArrayList<>();
        for (ForecastModelHour resForecastModelHour:list) {
            id = resForecastModelHour.getiD();
            String [] AQL = resForecastModelHour.getaQI().split("\\|");//AQL
            String [] PM25 = resForecastModelHour.getpM251H().split("\\|");//pm2.5
            String [] PM10 = resForecastModelHour.getpM101H().split("\\|");//pm10
            String [] SO2 = resForecastModelHour.getsO2().split("\\|");//SO2
            String [] NO2 = resForecastModelHour.getnO2().split("\\|");//NO2
            String [] CO = resForecastModelHour.getcO().split("\\|");//CO
            String [] O38H = resForecastModelHour.getO31H().split("\\|");//臭氧八小时
            String [] RH = resForecastModelHour.getrH().split("\\|");//相对湿度
            String [] precipitation = resForecastModelHour.getPrecipitation().split("\\|");//降雨量
            String [] Temperature = resForecastModelHour.getTemperature().split("\\|");//温度
            String [] WindSpeed = resForecastModelHour.getWindSpeed().split("\\|");//风力
            String [] Pressure = resForecastModelHour.getPressure().split("\\|");//压强
            String [] CloudCover = resForecastModelHour.getCloudCover().split("\\|");//云量
            String [] PBL = resForecastModelHour.getpBL().split("\\|");//边界层高度
            String [] WindDIR = resForecastModelHour.getWindDIR().split("\\|");//风向
            String [] primaryPollutant = resForecastModelHour.getPrimaryPollutant().split("\\|");//首要污染物
            String [] SVR = resForecastModelHour.getsVR().split("\\|");//能见度
            String [] HNO3 = resForecastModelHour.gethNO3().split("\\|");//HNO3
            String [] NH3 = resForecastModelHour.getnH3().split("\\|");//NH3
            String [] NOX = resForecastModelHour.getnOX().split("\\|");//NOX
            String [] EC = resForecastModelHour.geteC().split("\\|");//EC
            String [] OC = resForecastModelHour.getoC().split("\\|");//OC
            String [] SO4 = resForecastModelHour.getsO4().split("\\|");//SO4
            String [] NO3 = resForecastModelHour.getnO3().split("\\|");//NO3
            String [] NH4 = resForecastModelHour.getnH4().split("\\|");//NH4
            String [] SOIL = resForecastModelHour.getSoil().split("\\|");//SOIL
            String [] OM = resForecastModelHour.getoM().split("\\|");//OM
            String [] quality = resForecastModelHour.getQuality().split("\\|");//空气质量
            for (int i=0; i<CO.length; i++){
				if (i == 14) {
					break;
				}
                ForcastDataHour forcastDataHour = new ForcastDataHour();
                forcastDataHour.setForcastType("小时");
                Date hourPlus = DateUtil.setDate(resForecastModelHour.getTimepoint(),0,0,0,i+1);//加i小时
                forcastDataHour.setForcastTime(resForecastModelHour.getTimepoint());
                forcastDataHour.setPredictTime(hourPlus);
                forcastDataHour.setForcastDay(i+1);
                forcastDataHour.setCity(resForecastModelHour.getCityName());
                //城市编码不足6位补足6位
                String citycode = FormatUtil.appendZero(String.valueOf(resForecastModelHour.getCityCode()),6);
                forcastDataHour.setCityCode(citycode);
                forcastDataHour.setStation(resForecastModelHour.getStationname());
                forcastDataHour.setaQIC(replic(AQL[i]));
                forcastDataHour.setpM25C(replic(PM25[i]));
                forcastDataHour.setpM10C(replic(PM10[i]));
                forcastDataHour.setsO2C(replic(SO2[i]));
                forcastDataHour.setnO2C(replic(NO2[i]));
                forcastDataHour.setcOC(replic(CO[i]));
                forcastDataHour.setO3C(replic(O38H[i]));
                forcastDataHour.setrHC(replic(RH[i]));
                forcastDataHour.setPrecipitationC(replic(precipitation[i]));
                forcastDataHour.setTemperatureC(replic(Temperature[i]));
                forcastDataHour.setWindSpeedC(replic(WindSpeed[i]));
                forcastDataHour.setPressureC(replic(Pressure[i]));
                forcastDataHour.setCloudCoverC(replic(CloudCover[i]));
                forcastDataHour.setpBLC(replic(PBL[i]));
                forcastDataHour.setWindDIRC(replic(WindDIR[i]));
                if (resForecastModelHour.getStationname().equals("均值")){
                    GWFactCityHour gwFactCityHour = new GWFactCityHour();
                    gwFactCityHour.setDatetime(hourPlus);
                    gwFactCityHour.setCityname(resForecastModelHour.getCityName());
                    GWFactCityHour resGWFactCityHour = gwFactCityHourService.select(gwFactCityHour);
                    if (null != resGWFactCityHour){
                        forcastDataHour.setaQIR(replic(resGWFactCityHour.getaQI()));
                        forcastDataHour.setcOR(replic(resGWFactCityHour.getcO()));
                        forcastDataHour.setnO2R(replic(resGWFactCityHour.getnO2()));
                        forcastDataHour.setO3R(replic(resGWFactCityHour.getO3()));
                        forcastDataHour.setpM10R(replic(resGWFactCityHour.getpM10()));
                        forcastDataHour.setpM25R(replic(resGWFactCityHour.getpM25()));
                        forcastDataHour.setsO2R(replic(resGWFactCityHour.getsO2()));
                    }
                }else {
                    AQIStationRealTimeDataPublishHistory aqiStationRealTimeDataPublishHistory = new AQIStationRealTimeDataPublishHistory();
                    aqiStationRealTimeDataPublishHistory.setTimePoint(hourPlus);
                    aqiStationRealTimeDataPublishHistory.setStationName(resForecastModelHour.getStationname());
                    aqiStationRealTimeDataPublishHistory.setCityName(resForecastModelHour.getCityName());
                    AQIStationRealTimeDataPublishHistory resaqiStationRealTimeDataPublishHistory =
                            aqiStationRealTimeDataPublishHistoryService.select(aqiStationRealTimeDataPublishHistory);
                    if (null != resaqiStationRealTimeDataPublishHistory){
                        forcastDataHour.setStationCode(resaqiStationRealTimeDataPublishHistory.getStationCode());
                        forcastDataHour.setaQIR(replic(resaqiStationRealTimeDataPublishHistory.getaQI()));
                        forcastDataHour.setcOR(replic(resaqiStationRealTimeDataPublishHistory.getcO1h()));
                        forcastDataHour.setnO2R(replic(resaqiStationRealTimeDataPublishHistory.getnO21h()));
                        forcastDataHour.setO3R(replic(resaqiStationRealTimeDataPublishHistory.getO31h()));
                        forcastDataHour.setpM10R(replic(resaqiStationRealTimeDataPublishHistory.getpM101h()));
                        forcastDataHour.setpM25R(replic(resaqiStationRealTimeDataPublishHistory.getpM251h()));
                        forcastDataHour.setsO2R(replic(resaqiStationRealTimeDataPublishHistory.getsO21h()));
                    }
                }
                viewList.add(forcastDataHour);
            }
        }
        if (null != viewList && viewList.size()>0)
            forcastDataHourService.saveBatch(viewList);
        System.err.println("===========================执行结束=======================");
        System.err.println("===========================st======================="+st);
        System.err.println("===========================ed======================="+ed);
        }catch (Exception e){
            System.err.println("ERROR ID is"+id);
            throw e;
        }
    }
    public void importDayDate(int st,int ed) {
        ForecastModelDay forecastModelDay = new ForecastModelDay();
        forecastModelDay.setiDST(st);
        forecastModelDay.setiDED(ed);

        Integer id = null;
        try{
            List<ForecastModelDay> list = forecastModelDayService.select(forecastModelDay);
            list.size();
            List<ForcastDataView> viewList = new ArrayList<>();
            for (ForecastModelDay resForecastModelDay:list) {
                id = resForecastModelDay.getiD();
                String [] AQL = resForecastModelDay.getaQI().split("\\|");//AQL
                String [] PM25 = resForecastModelDay.getpM25().split("\\|");//pm2.5
                String [] PM10 = resForecastModelDay.getpM10().split("\\|");//pm10
                String [] SO2 = resForecastModelDay.getsO2().split("\\|");//SO2
                String [] NO2 = resForecastModelDay.getnO2().split("\\|");//NO2
                String [] CO = resForecastModelDay.getcO().split("\\|");//CO
                String [] O38H = resForecastModelDay.getO38H().split("\\|");//臭氧八小时
                String [] RH = resForecastModelDay.getAvgRH().split("\\|");//相对湿度
                String [] precipitation = resForecastModelDay.getAccPrecipitation().split("\\|");//降雨量
                String [] Temperature = resForecastModelDay.getHighTemperature().split("\\|");//温度
                String [] WindSpeed = resForecastModelDay.getAvgWindSpeed().split("\\|");//风力
                String [] Pressure = resForecastModelDay.getAvgPressure().split("\\|");//压强
                String [] CloudCover = resForecastModelDay.getAvgCloudCover().split("\\|");//云量
                String [] PBL = resForecastModelDay.getMaxPBL().split("\\|");//边界层高度
                String [] WindDIR = resForecastModelDay.getDomWindDIR().split("\\|");//风向
                String [] primaryPollutant = resForecastModelDay.getPrimaryPollutant().split("\\|");//首要污染物
                String [] SVR = resForecastModelDay.getMaxSVR().split("\\|");//能见度
                String [] HNO3 = resForecastModelDay.gethNO3().split("\\|");//HNO3
                String [] NH3 = resForecastModelDay.getnH3().split("\\|");//NH3
                String [] NOX = resForecastModelDay.getnOX().split("\\|");//NOX
                String [] EC = resForecastModelDay.geteC().split("\\|");//EC
                String [] OC = resForecastModelDay.getoC().split("\\|");//OC
                String [] SO4 = resForecastModelDay.getsO4().split("\\|");//SO4
                String [] NO3 = resForecastModelDay.getnO3().split("\\|");//NO3
                String [] NH4 = resForecastModelDay.getnH4().split("\\|");//NH4
                String [] SOIL = resForecastModelDay.getSoil().split("\\|");//SOIL
                String [] OM = resForecastModelDay.getoM().split("\\|");//OM
                String [] quality = resForecastModelDay.getQuality().split("\\|");//空气质量
                for (int i=0; i<CO.length; i++){
					if (i == 14) {
						break;
					}
                    ForcastDataView forcastDataView = new ForcastDataView();
                    forcastDataView.setForcastType("日均");
                    Date datePlus = DateUtil.setDate(resForecastModelDay.getTimepoint(),0,0,i+1);//加i天
                    forcastDataView.setForcastTime(resForecastModelDay.getTimepoint());
                    forcastDataView.setPredictTime(datePlus);
                    forcastDataView.setForcastDay(i+1);
                    forcastDataView.setCity(resForecastModelDay.getCityName());
                    //城市编码不足6位补足6位
                    String citycode = FormatUtil.appendZero(String.valueOf(resForecastModelDay.getCityCode()),6);
                    forcastDataView.setCityCode(citycode);
                    forcastDataView.setStation(resForecastModelDay.getStationname());
                    forcastDataView.setaQIC(replic(AQL[i]));
                    forcastDataView.setpM25C(replic(PM25[i]));
                    forcastDataView.setpM10C(replic(PM10[i]));
                    forcastDataView.setsO2C(replic(SO2[i]));
                    forcastDataView.setnO2C(replic(NO2[i]));
                    forcastDataView.setcOC(replic(CO[i]));
                    forcastDataView.setO3C(replic(O38H[i]));
                    forcastDataView.setrHC(replic(RH[i]));
                    forcastDataView.setPrecipitationC(replic(precipitation[i]));
                    forcastDataView.setTemperatureC(replic(Temperature[i]));
                    forcastDataView.setWindSpeedC(replic(WindSpeed[i]));
                    forcastDataView.setPressureC(replic(Pressure[i]));
                    forcastDataView.setCloudCoverC(replic(CloudCover[i]));
                    forcastDataView.setpBLC(replic(PBL[i]));
                    forcastDataView.setWindDIRC(replic(WindDIR[i]));
                    if (resForecastModelDay.getStationname().equals("均值")){
                        GWFactCityDay gwFactCityDay = new GWFactCityDay();
                        gwFactCityDay.setDatetime(datePlus);
                        gwFactCityDay.setCityname(resForecastModelDay.getCityName());
                        GWFactCityDay resGWFactCityDay = gwFactCityDayService.select(gwFactCityDay);
                        if (null != resGWFactCityDay){
                            forcastDataView.setaQIR(replic(resGWFactCityDay.getaQI()));
                            forcastDataView.setcOR(replic(resGWFactCityDay.getcO()));
                            forcastDataView.setnO2R(replic(resGWFactCityDay.getnO2()));
                            forcastDataView.setO3R(replic(resGWFactCityDay.getO38()));
                            forcastDataView.setpM10R(replic(resGWFactCityDay.getpM10()));
                            forcastDataView.setpM25R(replic(resGWFactCityDay.getpM25()));
                            forcastDataView.setsO2R(replic(resGWFactCityDay.getsO2()));
                        }
                    }else {
                        AQIStationDayDataPublishHistory aqiStationDayDataPublishHistory = new AQIStationDayDataPublishHistory();
                        aqiStationDayDataPublishHistory.setTimePoint(datePlus);
                        aqiStationDayDataPublishHistory.setStationName(resForecastModelDay.getStationname());
                        aqiStationDayDataPublishHistory.setCityName(resForecastModelDay.getCityName());
                        AQIStationDayDataPublishHistory resAQIStationDayDataPublishHistory = aqiStationDayDataPublishHistoryService.select(aqiStationDayDataPublishHistory);
                        if (null != resAQIStationDayDataPublishHistory){
                            forcastDataView.setStationCode(resAQIStationDayDataPublishHistory.getStationCode());
                            forcastDataView.setaQIR(replic(resAQIStationDayDataPublishHistory.getaQI()));
                            forcastDataView.setcOR(replic(resAQIStationDayDataPublishHistory.getcO1h()));
                            forcastDataView.setnO2R(replic(resAQIStationDayDataPublishHistory.getnO21h()));
                            forcastDataView.setO3R(replic(resAQIStationDayDataPublishHistory.getO31h()));
                            forcastDataView.setpM10R(replic(resAQIStationDayDataPublishHistory.getpM101h()));
                            forcastDataView.setpM25R(replic(resAQIStationDayDataPublishHistory.getpM251h()));
                            forcastDataView.setsO2R(replic(resAQIStationDayDataPublishHistory.getsO21h()));
                        }
                    }
                    viewList.add(forcastDataView);
                }
            }
            if (null != viewList && viewList.size()>0)
                forcastDataViewService.saveBatch(viewList);
            System.err.println("===========================执行结束=======================");
            System.err.println("===========================st======================="+st);
            System.err.println("===========================ed======================="+ed);
        }catch (Exception e){
            System.err.println("ERROR ID is"+id);
            throw e;
        }
    }
    
    
    /**
     * 专家天数据
     */
    //@Test
    public void  importExpertDayDateA(){
//        int j=5264;
        int j = 22486;
        for (int i=j+153; i<=22639;i=i+1){
        	System.err.println("==========Start whith"+j);
        	System.err.println("==========End whith"+i);
        	imporeExpertDayDate(j,i);
            j=i+1;
        }
//        contextLoads1(105428,105429);
    }
    /**
     * 预报评估-专家评估，除【分数】以外数据 导入
     */
//    //@Test
    public void imporeExpertDayDate(int st,int ed){
        ForecastAudit forecastAudit = new ForecastAudit();
        forecastAudit.setiDST(st);
        forecastAudit.setiDED(ed);

        Integer id = null;
        try{
            List<ForecastAudit> list = forecastAuditService.select(forecastAudit);
            list.size();
            List<ExpertForcastDataView> viewList = new ArrayList<>();
            List<ExpertFirstPollutant> pollutans = new ArrayList<>();
            List<ExpertAirLvl> airLvls = new ArrayList<>();
            for (ForecastAudit resForecastAudit:list) {
                id = resForecastAudit.getiD();
                String str1=resForecastAudit.getAqi().replace("|","");
                int size = resForecastAudit.getAqi().length()-str1.length()+1;
                String [] PM25 = new String [size];
                if (null != resForecastAudit.getPm25()) {
                    String strpm=resForecastAudit.getPm25().replace("|","");
                    int sizepm = resForecastAudit.getPm25().length()-strpm.length()+1;
                    if (size < sizepm) {
                    	size = sizepm;
					}
                	PM25 = resForecastAudit.getPm25().split("\\|",size);//pm2.5
                }
                String [] AQL = resForecastAudit.getAqi().split("\\|",size);//AQL
                String [] pollutant = resForecastAudit.getPrimaryPollutant().split("\\|",size);//首要污染物
                String [] Quality = resForecastAudit.getQuality().split("\\|",size);//空气质量
                for (int i=0; i<size; i++){
                    //专家PM25和AQI表
                    ExpertForcastDataView expertForcastDataView = new ExpertForcastDataView();
                    expertForcastDataView.setForcastDay(i+1);
                    expertForcastDataView.setForcastTime(resForecastAudit.getTimepoint());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(resForecastAudit.getTimepoint());
                    calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+(i+1));
                    expertForcastDataView.setPredictTime(calendar.getTime());
                    //城市编码不足6位补足6位
                    String citycode = FormatUtil.appendZero(String.valueOf(resForecastAudit.getCitycode()),6);
                    expertForcastDataView.setCityCode(citycode);
                    expertForcastDataView.setpM25Y(replic(PM25[i]));

                    if (AQL.length-1 >= i) {
                    	if (null == replic(AQL[i]) || "".equals(replic(AQL[i]))){
                    		expertForcastDataView.setaQIYST(null);
                    		expertForcastDataView.setaQIYED(null);
                    	}else{
                    		String[] aqi_S_D =replic(AQL[i]).split("~");
                    		if (null != aqi_S_D && aqi_S_D.length == 2){
                    			expertForcastDataView.setaQIYST(aqi_S_D[0]);
                    			expertForcastDataView.setaQIYED(aqi_S_D[1]);
                    		}
                    	}
                    }
                    GWFactCityDay gwFactCityDay = new GWFactCityDay();
                    gwFactCityDay.setDatetime(calendar.getTime());
                    gwFactCityDay.setCitycode(resForecastAudit.getCitycode());
                    GWFactCityDay resGWFactCityDay = gwFactCityDayService.select(gwFactCityDay);
                    if (null != resGWFactCityDay) {
                    	expertForcastDataView.setaQIR(resGWFactCityDay.getaQI());
                    	expertForcastDataView.setpM25R(resGWFactCityDay.getpM25());
					}
                    viewList.add(expertForcastDataView);

                    //专家首要污染物表
                    ExpertFirstPollutant expertFirstPollutant = new ExpertFirstPollutant();
                    expertFirstPollutant.setForcastDay(i+1);
                    expertFirstPollutant.setForcastTime(resForecastAudit.getTimepoint());
                    expertFirstPollutant.setPredictTime(calendar.getTime());
                    //城市编码不足6位补足6位
                    expertFirstPollutant.setCityCode(citycode);
                    //初始值
                    expertFirstPollutant.setpM25R("0");
                    expertFirstPollutant.setpM25Y("0");
                    expertFirstPollutant.setO38hR("0");
                    expertFirstPollutant.setO38hY("0");
                    expertFirstPollutant.setsO2R("0");
                    expertFirstPollutant.setsO2Y("0");
                    expertFirstPollutant.setnO2R("0");
                    expertFirstPollutant.setnO2Y("0");
                    expertFirstPollutant.setcOR("0");
                    expertFirstPollutant.setcOY("0");
                    expertFirstPollutant.setpM10R("0");
                    expertFirstPollutant.setpM10Y("0");
                    expertFirstPollutant.setiSNONER("0");
                    expertFirstPollutant.setiSNONEY("0");
                    if (pollutant.length-1 >= i) {
                    	for (String value:pollutant[i].split(",")) {
                    		if ("细颗粒物(PM2.5)".equals(value)){
                    			expertFirstPollutant.setpM25Y("1");
                    		}
                    		if ("颗粒物(PM10)".equals(value)){
                    			expertFirstPollutant.setpM10Y("1");
                    		}
                    		if ("臭氧8小时".equals(value)){
                    			expertFirstPollutant.setO38hY("1");
                    		}
                    		if ("无".equals(value)){
                    			expertFirstPollutant.setiSNONEY("1");
                    		}
                    		if ("一氧化碳".equals(value)){
                    			expertFirstPollutant.setcOY("1");
                    		}
                    		if ("二氧化硫".equals(value)){
                    			expertFirstPollutant.setsO2Y("1");
                    		}
                    		if ("二氧化氮".equals(value)){
                    			expertFirstPollutant.setnO2Y("1");
                    		}
                    	}
                    }
                    if (null != resGWFactCityDay) {
                    	//实测首要污染物
                    	String[] pollutantReal =  resGWFactCityDay.getChieflyInfectant().split(",");
                    	for (String value:pollutant) {
                    		if ("CO".equals(value)){
                    			expertFirstPollutant.setcOR("1");
                    		}
                    		if ("O3_8".equals(value)){
                    			expertFirstPollutant.setO38hR("1");
                    		}
                    		if ("PM2.5".equals(value)){
                    			expertFirstPollutant.setpM25R("1");
                    		}
                    		if ("NO2".equals(value)){
                    			expertFirstPollutant.setnO2R("1");
                    		}
                    		if ("SO2".equals(value)){
                    			expertFirstPollutant.setsO2R("1");
                    		}
                    		if ("PM10".equals(value)){
                    			expertFirstPollutant.setpM10R("1");
                    		}
                    		if ("—".equals(value)){
                    			expertFirstPollutant.setiSNONER("1");
                    		}
                    	}
					}
                    pollutans.add(expertFirstPollutant);

                    //专家空气质量等级表
                    ExpertAirLvl expertAirLvl = new ExpertAirLvl();
                    expertAirLvl.setForcastDay(i+1);
                    expertAirLvl.setForcastTime(resForecastAudit.getTimepoint());
                    expertAirLvl.setPredictTime(calendar.getTime());
                    //城市编码不足6位补足6位
                    expertAirLvl.setCityCode(citycode);
                    if (Quality.length-1 >= i) {
                    	String[] singleQuality = Quality[i].split("至|或");
                    	//预测数据
                    	for (String str:singleQuality){
                    		if (str.equals("优")) {
                    			expertAirLvl.setLv1Y("1");
                    		}else if(str.equals("良")){
                    			expertAirLvl.setLv2Y("1");
                    		}else if(str.equals("轻度污染")){
                    			expertAirLvl.setLv3Y("1");
                    		}else if(str.equals("中度污染")){
                    			expertAirLvl.setLv4Y("1");
                    		}else if(str.equals("重度污染")){
                    			expertAirLvl.setLv5Y("1");
                    		}else if(str.equals("严重污染")){
                    			expertAirLvl.setLv6Y("1");
                    		}
                    	}
					}
                    //实测数据
                    if (null != resGWFactCityDay) {
                    	String realLvl = resGWFactCityDay.getGradeDescription();
                    	if (realLvl.equals("优")) {
                    		expertAirLvl.setLv1R("1");
                    	}else if(realLvl.equals("良")){
                    		expertAirLvl.setLv2R("1");
                    	}else if(realLvl.equals("轻度污染")){
                    		expertAirLvl.setLv3R("1");
                    	}else if(realLvl.equals("中度污染")){
                    		expertAirLvl.setLv4R("1");
                    	}else if(realLvl.equals("重度污染")){
                    		expertAirLvl.setLv5R("1");
                    	}else if(realLvl.equals("严重污染")){
                    		expertAirLvl.setLv6R("1");
                    	}
					}
                    airLvls.add(expertAirLvl);
                }
            }
            if (null != viewList && viewList.size()>0){
                expertForcastDataViewService.saveBatch(viewList);
            }
            if (null != pollutans && pollutans.size()>0){
                expertFirstPollutantService.saveBatch(pollutans);
            }
            if (null != airLvls && airLvls.size()>0){
                expertAirLvlService.saveBatch(airLvls);
            }
//                forcastDataViewService.saveBatch(viewList);
            System.err.println("===========================执行结束=======================");

        }catch (Exception e){
            System.err.println("ERROR ID is"+id);
            throw e;
        }

    }


    /**
     * 后面补0
     * @param param
     * @param size
     * @return
     */
    private String appendZero(String param,int size){
        if (null == param){
            return param;
        }
        if (param.length() < size){
            StringBuffer sb = new StringBuffer();
            sb.append(param);
            for (int i=param.length();i<size;i++){
                sb.append("0");
            }
            param = sb.toString();
        }
        return param;
    }
    @Test
    public void spit() throws ParseException {
/*    	
        String nub="8.610";
//        int aaaa = Integer.parseInt(nub);
        Double aaaaa = Double.parseDouble(nub);
        String astr = "优或良";
        String bstr = "优至良";
        String cstr = "优";
        String[] aa = astr.split("至|或");
        String[] bb = bstr.split("至|或");
        String[] cc = cstr.split("至|或");
        int al = aa.length;
        int bl = bb.length;
        int cl = cc.length;
        //城市编码不足6位补足6位
        String citycode = String.valueOf(234500);
        if (citycode.length() < 6){
            StringBuffer sb = new StringBuffer();
            sb.append(citycode);
            for (int i=citycode.length();i<6;i++){
                sb.append("0");
            }
            citycode = sb.toString();
        }

        Date date = DateUtil.setDate(new Date(),0,0,-1);
        String strDate= DateUtil.convertToStr(date);
        Date date2 = DateUtil.convertToDate(strDate);
        */
        String aqi = "43~73|41~71|40~70||||";
        String str1=aqi.replace("|","");
        int sizea = aqi.length()-str1.length()+1;
        int size = aqi.indexOf("|");
        String [] array = aqi.split("\\|",size);
        String a = array[0];
        System.err.println(array.length);
        for (int i=0; i<array.length; i++){
            System.err.println(array[i]);
            String [] array2 = array[i].split("~");
            for (int j=0; j<array2.length; j++){
                System.err.println(array2[j]);
            }
        }
    }
    //@Test
    public void contextLoads() {
        List<AreaProvince> list = airQualityLvlForecastStatisticsSevice.getList(new Date());
//        System.err.println(JSONUtils.toJSONString(list));
        List<AirQualityLvlForecastStatistics> list1 = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i=1;i<6;i++){
            AirQualityLvlForecastStatistics a1 = new AirQualityLvlForecastStatistics();
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            a1.setCreatDate(new Date());
            a1.setCreatName("cctb");
            a1.setForecastDate(calendar.getTime());
            a1.setLevelCode(String.valueOf(i));
            a1.setLevelName("TEST");
            a1.setZoneId(2l);
            list1.add(a1);
        }
//        airQualityLvlForecastStatisticsSevice.saveBatch(list1);
        airQualityLvlForecastStatisticsSevice.delete(new Date());
/*        SysUserEntity user = new SysUserEntity();
        user.setEmail("123456@qq.com");
        redisUtils.set("user", user);

        System.out.println(ToStringBuilder.reflectionToString(redisUtils.get("user", SysUserEntity.class)));*/
    }

    public String replic(String param){
        if (param == null)
            return null;
        if ("—".equals(param) || "-".equals(param))
            return null;
        else
            return param;
    }
}
