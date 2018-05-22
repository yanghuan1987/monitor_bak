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
 * * 模型-中长期-空气质量等级
 \* @author yanghuan
 *
 */
@Component("ModelMediumLongAnalysisAirDateImportTask")
public class ModelMediumLongAnalysisAirDateImportTask {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MediumLongAnalysisAirService mediumLongAnalysisAirService;
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
	
	
	public void importAir() throws ParseException {
		GWFactCityDay gwFactCityDayVO = new GWFactCityDay();
		Date date = DateUtil.setDate(new Date(),0,0,-3);//前三天
		String strDate= DateUtil.convertToStr(date);
		Date date2 = DateUtil.convertToDate(strDate);//YYYY-MM-DD
		gwFactCityDayVO.setDateST(date2);
		SelectPiontVO selectPiontVO = new SelectPiontVO();
		selectPiontVO.setDateST(date2);
		Integer id = null;
		try{
			mediumLongAnalysisAirService.deleteByPrimaryKey(selectPiontVO);
			List<GWFactCityDay> list = gwFactCityDayService.selectByPrimaryKey(gwFactCityDayVO);
			List<MediumLongAnalysisAir> listMe = new ArrayList<>();
			for (GWFactCityDay resGWFactCityDay:list) {
				id = resGWFactCityDay.getiD();
				MediumLongAnalysisAir mediumLongAnalysisAir = new MediumLongAnalysisAir();
				mediumLongAnalysisAir.setCity(resGWFactCityDay.getCityname());
				//城市编码不足6位补足6位
				String citycode = FormatUtil.appendZero(String.valueOf(resGWFactCityDay.getCitycode()),6);
				mediumLongAnalysisAir.setCityCode(citycode);
				mediumLongAnalysisAir.setLvlCode(String.valueOf(FormatUtil.romalToNumber(resGWFactCityDay.getGrade())));
				mediumLongAnalysisAir.setLvlName(resGWFactCityDay.getGradeDescription());
				mediumLongAnalysisAir.setReportTime(resGWFactCityDay.getDatetime());
				listMe.add(mediumLongAnalysisAir);
			}
			if (null != listMe && listMe.size()>0) {
				mediumLongAnalysisAirService.saveBatch(listMe);
			}
			logger.info("===========================执行结束=======================");
			logger.info("===========================st======================="+id);
		}catch (Exception e){
			logger.error("ERROR ID is"+id,e,e.getMessage());
			throw e;
		}
	}

}
