package xsscd.monitor.air.southwest.modules.job.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xsscd.monitor.air.southwest.common.utils.Constant;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJob;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.EasymisScheduleJobMapper;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobService;
import xsscd.monitor.air.southwest.modules.job.utils.ScheduleUtils;
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;
	@Autowired
	private EasymisScheduleJobMapper mapper;
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<EasymisScheduleJob> scheduleJobList = this.getList(null);
		for(EasymisScheduleJob scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}
	@Override
	public List<EasymisScheduleJob> getList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.getList((HashMap<String, Object>) params);
	}

	@Override
	public EasymisScheduleJob findById(String jobId) {
		// TODO Auto-generated method stub
		return mapper.findById(jobId);
	}

	@Override
	public void save(EasymisScheduleJob bean) {
		bean.setCreateTime(new Date());
		bean.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
		mapper.save(bean);
        
        ScheduleUtils.createScheduleJob(scheduler, bean);
    }

	@Override
	public void update(EasymisScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        mapper.update(scheduleJob);    
    }

	@Override
	public void deleteBatch(String[] jobIds) {
    	for(String jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	//删除数据
    	mapper.deleteBatch(Arrays.asList(jobIds));
    	
	}

	@Override
	public int updateBatch(String[] jobIds, int status) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", jobIds);
    	map.put("status", status);
       //return mapper.updateBatch(map);
    	return 0;

    }

	@Override
	public void run(String[] jobIds) {
    	for(String jobId : jobIds){
    		ScheduleUtils.run(scheduler, this.findById(jobId));
    	}
    }

	@Override
	public void pause(String[] jobIds) {
        for(String jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}        
    	updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

	@Override
	public void resume(String[] jobIds) {
    	for(String jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }

}
