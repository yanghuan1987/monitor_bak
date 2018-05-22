package xsscd.monitor.air.southwest.modules.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.jdbc.util.FormatUtil;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.GWFactCityDay;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisPollutant;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.service.GWFactCityDayService;
import xsscd.monitor.air.southwest.modules.job.service.MediumLongAnalysisPollutantService;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobLogService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * * 模型-中长期-首要污染物
 \* @author yanghuan
 *
 */
@Component("ModelMediumLongAnalysisPollutantDateImportTask")
public class ModelMediumLongAnalysisPollutantDateImportTask {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MediumLongAnalysisPollutantService mediumLongAnalysisPollutantService;
	@Autowired
	private GWFactCityDayService gwFactCityDayService;

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
	
	
	public void importPollutant() throws ParseException {
		GWFactCityDay gwFactCityDayVO = new GWFactCityDay();
		Date date = DateUtil.setDate(new Date(),0,0,-3);//前三天
		String strDate= DateUtil.convertToStr(date);
		Date date2 = DateUtil.convertToDate(strDate);//YYYY-MM-DD
		gwFactCityDayVO.setDateST(date2);
		Integer id = null;
		SelectPiontVO selectPiontVO = new SelectPiontVO();
		selectPiontVO.setDateST(date2);
		try{
			mediumLongAnalysisPollutantService.deleteByPrimaryKey(selectPiontVO);
			List<GWFactCityDay> list = gwFactCityDayService.selectByPrimaryKey(gwFactCityDayVO);
			List<MediumLongAnalysisPollutant> listMe = new ArrayList<>();
			for (GWFactCityDay resGWFactCityDay:list) {
				id = resGWFactCityDay.getiD();
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
			if (null != listMe && listMe.size()>0) {
				mediumLongAnalysisPollutantService.saveBatch(listMe);
			}
			logger.info("===========================执行结束=======================");
			logger.info("===========================st======================="+id);
		}catch (Exception e){
			logger.error("ERROR ID is"+id,e,e.getMessage());
			throw e;
		}
	}
}
