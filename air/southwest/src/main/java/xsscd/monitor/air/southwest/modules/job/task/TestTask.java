package xsscd.monitor.air.southwest.modules.job.task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobLogService;

/**
 * * 测试定时任务(演示Demo，可删除)
 \* @author tanyujie
 *
 */
@Component("testTask")
public class TestTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
/*	@Autowired
	private SysUserService sysUserService;*/
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
	
	
	public void test2(){
		System.out.println("test2");
		logger.info("我是不带参数的test2方法，正在被执行");
	}

}
