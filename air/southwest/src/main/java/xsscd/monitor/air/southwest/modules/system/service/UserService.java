package xsscd.monitor.air.southwest.modules.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xsscd.monitor.air.southwest.common.jdbc.BaseMap;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Member;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.User;

public interface UserService {
	/**
	 * 存储登录时的ip
	 */
	public int saveIP(Map<String, String> map);

	/**
	 * 获取用户角色
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> getRoles(String username);

	/**
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> getPermissions(String username);

	/**
	 * 通过用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */

	public Member findByMembername(String username);

	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */

	public Member doLoginCheck(String username, String password);

	/**
	 * 根据用户序号查询用户信息
	 * 
	 * @param id
	 * @return
	 */

	public User findByUId(int id);

	/**
	 * 获取所有的用户信息并分页显示
	 * 
	 * @param pageNo
	 *            当前页面数
	 * @param pageSize
	 *            每一页面的页数
	 * @return
	 */

	public List<Member> findAll(int pageNo, int pageSize, String str);

	/**
	 * 根据关键字和日期查询
	 * 
	 * @param pageNo
	 *            当前页面数
	 * @param pageSize
	 *            每一页面的页数
	 * @param dir
	 * @param str
	 * @param keyword
	 *            关键字
	 * @param startDate
	 * @param endDate
	 * @return
	 */

	public List<User> searchU(int pageNo, int pageSize, String str, String keyword, Date startDate, Date endDate);

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	public void save(User user);

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	public void save(Member member);

	/**
	 * 清楚注册不激活的用户
	 * 
	 * @throws Exception
	 **/
	void removeExpired() throws Exception;

	void remove(BaseMap<String, Object> map);
	
	void updateStatus(String membername,Integer status);

	Member findByUsername(String username);
}
