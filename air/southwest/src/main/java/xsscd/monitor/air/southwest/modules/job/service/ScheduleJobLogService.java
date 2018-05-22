package xsscd.monitor.air.southwest.modules.job.service;

import java.util.List;
import java.util.Map;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJobLog;

public interface ScheduleJobLogService {
	public List<EasymisScheduleJobLog> getList(Map<String, Object> params);

	public EasymisScheduleJobLog findById(String logId);
	void save(EasymisScheduleJobLog log);
}
