package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJob;

public interface EasymisScheduleJobMapper {
	public List<EasymisScheduleJob> getList(HashMap<String, Object> params);

	public void save(EasymisScheduleJob bean);

	public void saveBatch(List<EasymisScheduleJob> beans);

	public void update(EasymisScheduleJob bean);

	public void delete(String jobId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public EasymisScheduleJob findById(String jobId);

	public List<EasymisScheduleJob> findByIds(List<String> list);
}