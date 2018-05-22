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
 * * 污染物汇总数据导入
 \* @author yanghuan
 *
 */
@Component("ExpertAirLvlCountDateImportTask")
public class ExpertAirLvlCountDateImportTask {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExpertAirLvlCountSourceService expertAirLvlCountSourceService;

    @Autowired
    private ExpertAirLvlCountService expertAirLvlCountService;

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
	public void importExpertAirLvlCountMain() throws ParseException {
		
        int forecastDay = 6;//预测天数
        //2017-09-18 00:00:00.000 之前预测5天
        Date forecast = DateUtil.parseSimpleDateTime("2017-09-18 00:00:00");//预测分割日
        Date edDate = new Date();
        Date stDate = DateUtil.setDate(edDate,0,0,-1);//减一天
        SelectPiontVO selectVO = new SelectPiontVO();

		String strDate= DateUtil.convertToStr(stDate);
		Date dateStart = DateUtil.convertToDate(strDate);//YYYY-MM-DD
		String stredDate= DateUtil.convertToStr(edDate);
		Date dateEnd = DateUtil.convertToDate(stredDate);//YYYY-MM-DD
		
		selectVO.setDateST(dateStart);
        selectVO.setDateED(dateEnd);
        if (stDate.compareTo(forecast)>=0){
            forecastDay=8;
        }
		logger.info("Start with time ="+stDate);
		logger.info("End with time ="+edDate);
        expertAirLvlCountService.deleteByPrimaryKey(selectVO);//删除一天前的
        importExpertAirLvlCount(selectVO,forecastDay);
        
	}

    public void importExpertAirLvlCount(SelectPiontVO selectVO,int forecastDay){
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


}
