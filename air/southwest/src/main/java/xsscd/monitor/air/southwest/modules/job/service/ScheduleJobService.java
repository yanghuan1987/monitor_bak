package xsscd.monitor.air.southwest.modules.job.service;

import java.util.List;
import java.util.Map;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJob;

public interface ScheduleJobService {
	public List<EasymisScheduleJob> getList(Map<String, Object> params);

	public EasymisScheduleJob findById(String jobId);

	/**
	 * 保存定时任务
	 */
	void save(EasymisScheduleJob scheduleJob);

	/**
	 * 更新定时任务
	 */
	void update(EasymisScheduleJob scheduleJob);

	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(String[] jobIds);

	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(String[] jobIds, int status);

	/**
	 * 立即执行
	 */
	void run(String[] jobIds);

	/**
	 * 暂停运行
	 */
	void pause(String[] jobIds);

	/**
	 * 恢复运行
	 */
	void resume(String[] jobIds);
}
