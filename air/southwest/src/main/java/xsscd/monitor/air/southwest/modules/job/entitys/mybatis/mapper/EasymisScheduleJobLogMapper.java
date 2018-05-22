package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJobLog;

public interface EasymisScheduleJobLogMapper {
	public List<EasymisScheduleJobLog> getList(HashMap<String, Object> params);

	public void save(EasymisScheduleJobLog bean);

	public void saveBatch(List<EasymisScheduleJobLog> beans);

	public void update(EasymisScheduleJobLog bean);

	public void delete(String logId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public EasymisScheduleJobLog findById(String logId);

	public List<EasymisScheduleJobLog> findByIds(List<String> list);
}