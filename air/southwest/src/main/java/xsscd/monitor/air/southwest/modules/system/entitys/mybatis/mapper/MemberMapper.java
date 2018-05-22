package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Member;

public interface MemberMapper {
	public List<Member> getList(HashMap<String, Object> params);

	public void save(Member bean);

	public void saveBatch(List<Member> beans);

	public void update(Member bean);

	public void delete(String memberId);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	public Member findById(String memberId);
	public Member findByMemberame(String membername);

	public List<Member> findByIds(List<String> list);
}