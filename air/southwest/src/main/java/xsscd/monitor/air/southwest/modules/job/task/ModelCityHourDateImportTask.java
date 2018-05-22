package xsscd.monitor.air.southwest.modules.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.jdbc.util.FormatUtil;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.AQIStationRealTimeDataPublishHistory;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.GWFactCityHour;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.service.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * * 模型-城市-小时数据导入
 \* @author yanghuan
 *
 */
@Component("ModelCityHourDateImportTask")
public class ModelCityHourDateImportTask {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ForcastDataHourService forcastDataHourService;
	@Autowired
	private ForecastModelHourService forecastModelHourService;

	@Autowired
	private AQIStationRealTimeDataPublishHistoryService aqiStationRealTimeDataPublishHistoryService;

	@Autowired
	private GWFactCityHourService gwFactCityHourService;

	@Autowired
	private ScheduleJobLogService jobLog;
	public void importHourdateMainWithParam(String startTime,String endTime) throws ParseException{
		logger.info("我是带参数的test方法，正在被执行，参数为：startTime = " + startTime+"--endtime = "+ endTime);

		Date dateStart = DateUtil.convertToDate(startTime,"yyyy-MM-dd HH:mm:ss");//YYYY-MM-DD
		Date dateEnd = DateUtil.convertToDate(endTime);//YYYY-MM-DD

		logger.info("Start with time ="+dateStart);
		logger.info("End with time ="+dateEnd);
		SelectPiontVO selectPiontVO = new SelectPiontVO();
		selectPiontVO.setDateST(dateStart);
//		selectPiontVO.setDateED(edDate);
		forcastDataHourService.deleteByPrimaryKey(selectPiontVO);//删除数据重新插入
		while (dateStart.compareTo(dateEnd) < 0) {
			ForecastModelHour forecastModelHour = new ForecastModelHour();
			forecastModelHour.setDateST(dateStart);
			Date edDate = DateUtil.setDate(dateStart,0,0,0,1);//一小时
			forecastModelHour.setDateED(edDate);
			importHourDate(forecastModelHour);
			dateStart = edDate;
		}
		
	}

	public void importHourdateMain() throws ParseException {
		Date miDate = new Date();//当前时间 
		Date stDate = DateUtil.setDate(miDate,0,0,-3);//4 day before
		Date endTime = DateUtil.setDate(miDate,0,0,1);//1 day after
		
		logger.info("Start with time ="+DateUtil.formatDate(stDate, "yyyy-MM-dd"));
		logger.info("End with time ="+DateUtil.formatDate(endTime, "yyyy-MM-dd"));
		importHourdateMainWithParam(DateUtil.formatDate(stDate, "yyyy-MM-dd"), DateUtil.formatDate(endTime, "yyyy-MM-dd"));
	}
	public void importHourDate(ForecastModelHour forecastModelHour) throws ParseException {

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
					forcastDataHour.setaQIC(FormatUtil.replic(AQL[i]));
					forcastDataHour.setpM25C(FormatUtil.replic(PM25[i]));
					forcastDataHour.setpM10C(FormatUtil.replic(PM10[i]));
					forcastDataHour.setsO2C(FormatUtil.replic(SO2[i]));
					forcastDataHour.setnO2C(FormatUtil.replic(NO2[i]));
					forcastDataHour.setcOC(FormatUtil.replic(CO[i]));
					forcastDataHour.setO3C(FormatUtil.replic(O38H[i]));
					forcastDataHour.setrHC(FormatUtil.replic(RH[i]));
					forcastDataHour.setPrecipitationC(FormatUtil.replic(precipitation[i]));
					forcastDataHour.setTemperatureC(FormatUtil.replic(Temperature[i]));
					forcastDataHour.setWindSpeedC(FormatUtil.replic(WindSpeed[i]));
					forcastDataHour.setPressureC(FormatUtil.replic(Pressure[i]));
					forcastDataHour.setCloudCoverC(FormatUtil.replic(CloudCover[i]));
					forcastDataHour.setpBLC(FormatUtil.replic(PBL[i]));
					forcastDataHour.setWindDIRC(FormatUtil.replic(WindDIR[i]));
					if (resForecastModelHour.getStationname().equals("均值")){
						GWFactCityHour gwFactCityHour = new GWFactCityHour();
						gwFactCityHour.setDatetime(hourPlus);
						gwFactCityHour.setCityname(resForecastModelHour.getCityName());
						GWFactCityHour resGWFactCityHour = gwFactCityHourService.select(gwFactCityHour);
						if (null != resGWFactCityHour){
							forcastDataHour.setaQIR(FormatUtil.replic(resGWFactCityHour.getaQI()));
							forcastDataHour.setcOR(FormatUtil.replic(resGWFactCityHour.getcO()));
							forcastDataHour.setnO2R(FormatUtil.replic(resGWFactCityHour.getnO2()));
							forcastDataHour.setO3R(FormatUtil.replic(resGWFactCityHour.getO3()));
							forcastDataHour.setpM10R(FormatUtil.replic(resGWFactCityHour.getpM10()));
							forcastDataHour.setpM25R(FormatUtil.replic(resGWFactCityHour.getpM25()));
							forcastDataHour.setsO2R(FormatUtil.replic(resGWFactCityHour.getsO2()));
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
							forcastDataHour.setaQIR(FormatUtil.replic(resaqiStationRealTimeDataPublishHistory.getaQI()));
							forcastDataHour.setcOR(FormatUtil.replic(resaqiStationRealTimeDataPublishHistory.getcO1h()));
							forcastDataHour.setnO2R(FormatUtil.replic(resaqiStationRealTimeDataPublishHistory.getnO21h()));
							forcastDataHour.setO3R(FormatUtil.replic(resaqiStationRealTimeDataPublishHistory.getO31h()));
							forcastDataHour.setpM10R(FormatUtil.replic(resaqiStationRealTimeDataPublishHistory.getpM101h()));
							forcastDataHour.setpM25R(FormatUtil.replic(resaqiStationRealTimeDataPublishHistory.getpM251h()));
							forcastDataHour.setsO2R(FormatUtil.replic(resaqiStationRealTimeDataPublishHistory.getsO21h()));
						}
					}
					viewList.add(forcastDataHour);
				}
			}
			if (null != viewList && viewList.size()>0)
				forcastDataHourService.saveBatch(viewList);
			logger.info("===========================执行结束=======================");
			logger.info("===========================st======================="+id);
		}catch (Exception e){
			logger.error("ERROR ID is"+id);
			throw e;
		}
	}

}
