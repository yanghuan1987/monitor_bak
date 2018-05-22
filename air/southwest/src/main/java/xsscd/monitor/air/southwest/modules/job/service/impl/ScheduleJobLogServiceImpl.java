package xsscd.monitor.air.southwest.modules.job.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xsscd.monitor.air.southwest.common.utils.UUIDUtil;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJobLog;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.EasymisScheduleJobLogMapper;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobLogService;
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	@Autowired
	EasymisScheduleJobLogMapper mapper;
	@Override
	public List<EasymisScheduleJobLog> getList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.getList((HashMap<String, Object>) params);
	}

	@Override
	public EasymisScheduleJobLog findById(String logId) {
		// TODO Auto-generated method stub
		return mapper.findById(logId);
	}

	public void save(EasymisScheduleJobLog bean) {
		bean.setLogId(UUIDUtil.getRandomNum());
		mapper.save(bean);
	}

}
