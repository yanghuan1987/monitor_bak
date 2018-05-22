package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.HrmStaffInfo;

public interface HrmStaffInfoMapper {
	public List<HrmStaffInfo> getList(HashMap<String, Object> params);

	public void save(HrmStaffInfo bean);

	public void saveBatch(List<HrmStaffInfo> beans);

	public void update(HrmStaffInfo bean);

	public void delete(String staffId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public HrmStaffInfo findById(String staffId);

	public List<HrmStaffInfo> findByIds(List<String> list);
}