package xsscd.monitor.air.southwest.modules.job.utils;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import xsscd.monitor.air.southwest.common.utils.SpringContextUtils;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJob;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJobLog;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobLogService;


/**
 * 定时任务
 *
 */
@Component
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService service = Executors.newSingleThreadExecutor(); 
	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        EasymisScheduleJob scheduleJob = (EasymisScheduleJob) context.getMergedJobDataMap()
        		.get(EasymisScheduleJob.JOB_PARAM_KEY);
        
        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");
        
        //数据库保存执行记录
        EasymisScheduleJobLog log = new EasymisScheduleJobLog();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
            		scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);
            
			future.get();
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setRunTime((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(0);
			
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setRunTime((int)times);
			
			//任务状态    0：成功    1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(log);
			logger.info("保存任务ID：" + scheduleJob.getJobId() + "日志信息");
		}
    }
}
