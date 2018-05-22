package xsscd.monitor.air.southwest.modules.system.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.HrmStaffInfo;

public interface HrmStaffInfoMng {
	public List<HrmStaffInfo> getList(HashMap<String, Object> params);

	public void save(HrmStaffInfo bean);

	public void saveBatch(List<HrmStaffInfo> beans);

	public void update(HrmStaffInfo bean);

	public void delete(String staffId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public HrmStaffInfo findById(String staffId);

	public HrmStaffInfo findByMemberId(String staffId);

	public HrmStaffInfo findByMemberAndOrganize(String memberId, String organizeId);

	public List<HrmStaffInfo> findByIds(List<String> list);

	public String batchImport(String fileName, MultipartFile mfile);
}
