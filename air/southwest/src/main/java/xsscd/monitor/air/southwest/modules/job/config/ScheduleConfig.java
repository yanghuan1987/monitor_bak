package xsscd.monitor.air.southwest.modules.job.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务配置
 *
 */
/*@Configuration*/
public class ScheduleConfig {
	@Autowired   
    DataSource dataSource;  
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(this.dataSource);

        //quartz参数
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "EasymisScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");        
        //线程池配置
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        //JobStore配置:Job和Trigger的细节信息被存储在内存
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.useProperties", "true");
        //prop.put("org.quartz.jobStore.driverDelegateClass","org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        //集群配置
        prop.put("org.quartz.jobStore.isClustered", "true");
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");

        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        prop.put("org.quartz.jobStore.tablePrefix", "easymis_");
       // prop.put("org.quartz.jobStore.dataSource", "easymis"); 
        //配置数据源  
        //prop.put("org.quartz.dataSource.easymis.driver", "com.mysql.jdbc.Driver");
        //prop.put("org.quartz.dataSource.easymis.URL", "jdbc:mysql://127.0.0.1:3306/easymis"); 
        //prop.put("org.quartz.dataSource.easymis.user", "root");
        //prop.put("org.quartz.dataSource.easymis.password", "root");  
     //   prop.put("org.quartz.dataSource.easymis.maxConnection", "5");
        factory.setQuartzProperties(prop);

        factory.setSchedulerName("EasymisScheduler");
        //延时启动
        factory.setStartupDelay(30);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
