/**
 * 
 */
/**
 * @ClassName package-info
 * @Description TODO
 * @author tanyujie

 */
package xsscd.monitor.air.southwest.modules.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xsscd.monitor.air.southwest.modules.system.service.TestService;
/**
 * 
 * @Description TODO
 * @author tanyujie
 * @date 2018年2月27日 下午4:15:08
 */
@RestController	//使用restcontroller requestmapping不需要responsebody 自动返回JSON格式
public class TestDataSourceController{
	
	protected static final Logger logger = LoggerFactory.getLogger(TestDataSourceController.class);
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	public String index() {
		logger.debug("测试信息：{}","welcome log world");
		return "主表："+testService.queryCountByMester();
	}
	
	@RequestMapping("/test1")
	public String test() {
		return "从表："+testService.queryCountBySavle();
	}
	
	
}