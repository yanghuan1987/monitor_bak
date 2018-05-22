package xsscd.monitor.air.southwest.modules.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xsscd.monitor.air.southwest.common.jdbc.BaseMap;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.HrmStaffInfo;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Member;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.User;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper.HrmStaffInfoMapper;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper.MemberMapper;
import xsscd.monitor.air.southwest.modules.system.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	MemberMapper mapper;
	@Autowired
	HrmStaffInfoMapper staffInfomapper;
	
	@Override
	public int saveIP(Map<String, String> map) {
		String loginIp = map.get("loginIp");
		String username = map.get("username");
		// int code = userRepository.updateLoginIpById(loginIp,username);
		return 0;
	}

	@Override
	public Set<String> getRoles(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getPermissions(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member findByUsername(String username) {
		// TODO Auto-generated method stub
		return mapper.findByMemberame(username);
	}

	/*@SysLog("用户登录检查")*/
	public Member doLoginCheck(String username, String password) {
		// TODO Auto-generated method stub
		Member member = mapper.findByMemberame(username);
		return member;
	}

	@Override
	public User findByUId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findAll(int pageNo, int pageSize, String str) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		return mapper.getList(params);
	}

	@Override
	public List<User> searchU(int pageNo, int pageSize, String str, String keyword, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeExpired() throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void save(Member member) {
		// TODO Auto-generated method stub
		member.setMemberId(UUID.randomUUID().toString());
		mapper.save(member);
		HrmStaffInfo staff = new HrmStaffInfo();
		staff.setMemberId(member.getMemberId());
		staff.setName(member.getName());
		//user.setRoleids(RbacConstant.initRole());
		staffInfomapper.save(staff);
		
	}

	@Override
	public Member findByMembername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(BaseMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatus(String membername, Integer status) {
		// TODO Auto-generated method stub
		
	}

}
