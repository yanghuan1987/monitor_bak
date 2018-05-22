package xsscd.monitor.air.southwest.modules.job.task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.jdbc.util.FormatUtil;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForecastModelDay;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.PollutantAnalysis;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.service.ForecastModelDayService;
import xsscd.monitor.air.southwest.modules.job.service.PollutantAnalysisService;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobLogService;

/**
 * * 污染解析导入
 \* @author yanghuan
 *
 */
@Component("PollutantAnalysisDateImportTask")
public class PollutantAnalysisDateImportTask {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PollutantAnalysisService pollutantAnalysisService;
	@Autowired
	private ForecastModelDayService forecastModelDayService;

	@Autowired
	private ScheduleJobLogService jobLog;

	public void importDayDateMainWithParam(String startTime,String endTime) throws ParseException{
		logger.info("我是带参数的test方法，正在被执行，参数为：startTime = " + startTime+"--endtime = "+ endTime);
		Date dateStart = DateUtil.convertToDate(startTime);//YYYY-MM-DD
		Date dateEnd = DateUtil.convertToDate(endTime);//YYYY-MM-DD

		logger.info("Start with time ="+dateStart);
		logger.info("End with time ="+dateEnd);

		SelectPiontVO selectPiontVO = new SelectPiontVO();
		selectPiontVO.setDateST(dateStart);
		logger.info("Start with time ="+dateStart);
//		logger.info("End with time ="+endDate);
		pollutantAnalysisService.deleteByPrimaryKey(selectPiontVO);
		
		while (dateStart.compareTo(dateEnd) < 0) {
			ForecastModelDay forecastModelDay = new ForecastModelDay();
			forecastModelDay.setTimepoint(dateStart);
			Date edDate = DateUtil.setDate(dateStart,0,0,1);//一天
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
		importDayDateMainWithParam(DateUtil.formatDate(stDate, "yyyy-MM-dd"), DateUtil.formatDate(endTime, "yyyy-MM-dd"));
	}
	
	public void importDayDate(ForecastModelDay forecastModelDay) throws ParseException {
		Integer id = null;
		try{
			List<ForecastModelDay> list = forecastModelDayService.select(forecastModelDay);
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
			logger.info("===========================执行结束=======================");
			logger.info("===========================st======================="+id);
		}catch (Exception e){
			logger.error("ERROR ID is"+id,e,e.getMessage());
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
			pollutantAnalysis.setPollutantValue(FormatUtil.replic(prama[i]));
			if(grop == 1){
				pollutantAnalysis.setPollutantGroup("气态组分");
			}else {
				pollutantAnalysis.setPollutantGroup("PM2.5组分");
			}
			list.add(pollutantAnalysis);
		}
		return list;
	}

}
