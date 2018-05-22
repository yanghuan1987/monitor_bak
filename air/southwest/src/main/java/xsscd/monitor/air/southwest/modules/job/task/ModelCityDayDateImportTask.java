package xsscd.monitor.air.southwest.modules.job.task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.jdbc.util.FormatUtil;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.AQIStationDayDataPublishHistory;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelDay;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.GWFactCityDay;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.service.AQIStationDayDataPublishHistoryService;
import xsscd.monitor.air.southwest.modules.job.service.ForcastDataViewService;
import xsscd.monitor.air.southwest.modules.job.service.ForecastModelDayService;
import xsscd.monitor.air.southwest.modules.job.service.GWFactCityDayService;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobLogService;

/**
 * * 模型-城市-日均数据导入
 \* @author yanghuan
 *
 */
@Component("ModelCityDayDateImportTask")
public class ModelCityDayDateImportTask {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ForcastDataViewService forcastDataViewService;
	@Autowired
	private ForecastModelDayService forecastModelDayService;

	@Autowired
	private AQIStationDayDataPublishHistoryService aqiStationDayDataPublishHistoryService;

	@Autowired
	private GWFactCityDayService gwFactCityDayService;

	@Autowired
	private ScheduleJobLogService jobLog;

	public void importDaydateMainWithParam(String startTime,String endTime) throws ParseException{
		logger.info("我是带参数的test方法，正在被执行，参数为：startTime = " + startTime+"--endtime = "+ endTime);
		Date dateStart = DateUtil.convertToDate(startTime);//YYYY-MM-DD
		Date dateEnd = DateUtil.convertToDate(endTime);//YYYY-MM-DD

		logger.info("Start with time ="+dateStart);
		logger.info("End with time ="+dateEnd);
		SelectPiontVO selectPiontVO = new SelectPiontVO();
		selectPiontVO.setDateST(dateStart);
//		selectPiontVO.setDateED(edDate);
		forcastDataViewService.deleteByPrimaryKey(selectPiontVO);//删除数据重新插入
		while (dateStart.compareTo(dateEnd) < 0) {
			ForecastModelDay forecastModelDay = new ForecastModelDay();
			forecastModelDay.setDateST(dateStart);
			Date edDate = DateUtil.setDate(dateStart,0,0,1);//一天
			forecastModelDay.setDateED(edDate);
			importDayDate(forecastModelDay);
			dateStart = edDate;
		}
		
	}

	public void importDaydateMain() throws ParseException {
		Date miDate = new Date();//当前时间 
		Date stDate = DateUtil.setDate(miDate,0,0,-4);//4 day before
		Date endTime = DateUtil.setDate(miDate,0,0,1);//1 day after
		
		logger.info("Start with time ="+DateUtil.formatDate(stDate, "yyyy-MM-dd"));
		logger.info("End with time ="+DateUtil.formatDate(endTime, "yyyy-MM-dd"));
		importDaydateMainWithParam(DateUtil.formatDate(stDate, "yyyy-MM-dd"), DateUtil.formatDate(endTime, "yyyy-MM-dd"));
	}
	
	public void importDayDate(ForecastModelDay forecastModelDay) throws ParseException {
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
					forcastDataView.setaQIC(FormatUtil.replic(AQL[i]));
					forcastDataView.setpM25C(FormatUtil.replic(PM25[i]));
					forcastDataView.setpM10C(FormatUtil.replic(PM10[i]));
					forcastDataView.setsO2C(FormatUtil.replic(SO2[i]));
					forcastDataView.setnO2C(FormatUtil.replic(NO2[i]));
					forcastDataView.setcOC(FormatUtil.replic(CO[i]));
					forcastDataView.setO3C(FormatUtil.replic(O38H[i]));
					forcastDataView.setrHC(FormatUtil.replic(RH[i]));
					forcastDataView.setPrecipitationC(FormatUtil.replic(precipitation[i]));
					forcastDataView.setTemperatureC(FormatUtil.replic(Temperature[i]));
					forcastDataView.setWindSpeedC(FormatUtil.replic(WindSpeed[i]));
					forcastDataView.setPressureC(FormatUtil.replic(Pressure[i]));
					forcastDataView.setCloudCoverC(FormatUtil.replic(CloudCover[i]));
					forcastDataView.setpBLC(FormatUtil.replic(PBL[i]));
					forcastDataView.setWindDIRC(FormatUtil.replic(WindDIR[i]));
					if (resForecastModelDay.getStationname().equals("均值")){
						GWFactCityDay gwFactCityDay = new GWFactCityDay();
						gwFactCityDay.setDatetime(datePlus);
						gwFactCityDay.setCityname(resForecastModelDay.getCityName());
						GWFactCityDay resGWFactCityDay = gwFactCityDayService.select(gwFactCityDay);
						if (null != resGWFactCityDay){
							forcastDataView.setaQIR(FormatUtil.replic(resGWFactCityDay.getaQI()));
							forcastDataView.setcOR(FormatUtil.replic(resGWFactCityDay.getcO()));
							forcastDataView.setnO2R(FormatUtil.replic(resGWFactCityDay.getnO2()));
							forcastDataView.setO3R(FormatUtil.replic(resGWFactCityDay.getO38()));
							forcastDataView.setpM10R(FormatUtil.replic(resGWFactCityDay.getpM10()));
							forcastDataView.setpM25R(FormatUtil.replic(resGWFactCityDay.getpM25()));
							forcastDataView.setsO2R(FormatUtil.replic(resGWFactCityDay.getsO2()));
						}
					}else {
						AQIStationDayDataPublishHistory aqiStationDayDataPublishHistory = new AQIStationDayDataPublishHistory();
						aqiStationDayDataPublishHistory.setTimePoint(datePlus);
						aqiStationDayDataPublishHistory.setStationName(resForecastModelDay.getStationname());
						aqiStationDayDataPublishHistory.setCityName(resForecastModelDay.getCityName());
						AQIStationDayDataPublishHistory resAQIStationDayDataPublishHistory = aqiStationDayDataPublishHistoryService.select(aqiStationDayDataPublishHistory);
						if (null != resAQIStationDayDataPublishHistory){
							forcastDataView.setStationCode(resAQIStationDayDataPublishHistory.getStationCode());
							forcastDataView.setaQIR(FormatUtil.replic(resAQIStationDayDataPublishHistory.getaQI()));
							forcastDataView.setcOR(FormatUtil.replic(resAQIStationDayDataPublishHistory.getcO1h()));
							forcastDataView.setnO2R(FormatUtil.replic(resAQIStationDayDataPublishHistory.getnO21h()));
							forcastDataView.setO3R(FormatUtil.replic(resAQIStationDayDataPublishHistory.getO31h()));
							forcastDataView.setpM10R(FormatUtil.replic(resAQIStationDayDataPublishHistory.getpM101h()));
							forcastDataView.setpM25R(FormatUtil.replic(resAQIStationDayDataPublishHistory.getpM251h()));
							forcastDataView.setsO2R(FormatUtil.replic(resAQIStationDayDataPublishHistory.getsO21h()));
						}
					}
					viewList.add(forcastDataView);
				}
			}
			if (null != viewList && viewList.size()>0)
				forcastDataViewService.saveBatch(viewList);
			logger.info("===========================执行结束=======================");
			logger.info("===========================st======================="+id);
		}catch (Exception e){
			logger.error("ERROR ID is"+id,e,e.getMessage());
			throw e;
		}
	}
}
