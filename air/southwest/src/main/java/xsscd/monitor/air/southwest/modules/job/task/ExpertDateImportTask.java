package xsscd.monitor.air.southwest.modules.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.jdbc.util.FormatUtil;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.*;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.service.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * * 专家pm2.5，AQI，首要污染物，空气质量等级表导入
 \* @author yanghuan
 *
 */
@Component("ExpertDateImportTask")
public class ExpertDateImportTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ForecastAuditService forecastAuditService;
	@Autowired
	private GWFactCityDayService gwFactCityDayService;
	@Autowired
	private ExpertForcastDataViewService expertForcastDataViewService;
	@Autowired
	private ExpertFirstPollutantService expertFirstPollutantService;
	@Autowired
	private ExpertAirLvlService expertAirLvlService;
	@Autowired
	private ScheduleJobLogService jobLog;
	public void test(String params){
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//SysUserEntity user = sysUserService.selectById(1L);
		System.out.println(/*ToStringBuilder.reflectionToString(user)*/"wwwww");
		
	}
	
	
	public void imporeExpertDayDate() throws ParseException {
		ForecastAudit forecastAudit = new ForecastAudit();
		Date date = DateUtil.setDate(new Date(),0,0,-1);//前一天
		String strDate= DateUtil.convertToStr(date);
		Date date2 = DateUtil.convertToDate(strDate);//YYYY-MM-DD
		forecastAudit.setDateST(date2);
		SelectPiontVO selectPiontVO = new SelectPiontVO();
		selectPiontVO.setDateST(date);
		expertForcastDataViewService.deleteByPrimaryKey(selectPiontVO);
		expertFirstPollutantService.deleteByPrimaryKey(selectPiontVO);
		expertAirLvlService.deleteByPrimaryKey(selectPiontVO);
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
                int size = resForecastAudit.getAqi().length()-str1.length()+1;//取得预测最大天数
                String [] PM25 = new String [size];
                if (null != resForecastAudit.getPm25()) {
                    String strpm=resForecastAudit.getPm25().replace("|","");
                    int sizepm = resForecastAudit.getPm25().length()-strpm.length()+1;
                    if (size < sizepm) {
                    	size = sizepm;//取得预测最大天数，数据不整合，再次取最大值
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
                    expertForcastDataView.setpM25Y(FormatUtil.replic(PM25[i]));

                    if (AQL.length-1 >= i) {
                    	if (null == FormatUtil.replic(AQL[i]) || "".equals(FormatUtil.replic(AQL[i]))){
                    		expertForcastDataView.setaQIYST(null);
                    		expertForcastDataView.setaQIYED(null);
                    	}else{
                    		String[] aqi_S_D =FormatUtil.replic(AQL[i]).split("~");
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
						for (String value:pollutantReal) {
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
					if (null != resGWFactCityDay) {
						//实测数据
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
			logger.info("===========================st======================="+id);
		}catch (Exception e){
			logger.error("ERROR ID is"+id,e,e.getMessage());
			throw e;
		}
	}

}
