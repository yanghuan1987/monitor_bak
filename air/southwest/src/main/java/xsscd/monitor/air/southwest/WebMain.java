package xsscd.monitor.air.southwest;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xsscd.monitor.air.southwest.cache.RedisClient;

/**
 * 添加Redis缓存Spring AOP实现
 * 
 * @SpringBootApplication 等价 @Configuration @EnableAutoConfiguration @ComponentScan
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableTransactionManagement(order = 2) // 设置事务执行顺序(需要在切换数据源之后，否则只走主库)
@MapperScan(basePackages = "xsscd.monitor.air.southwest.**.entitys.mybatis.mapper")
@ServletComponentScan
/*@EnableScheduling
@EnableAsync*/
@RestController
public class WebMain {
	protected static final Logger logger = LoggerFactory.getLogger(WebMain.class);
	@Autowired
	RedisClient redisClient;

	public static void main(String[] args) {
		logger.info("西南平台开始加载");
		SpringApplication.run(WebMain.class, args);
		logger.info("西南平台加载完毕");
	}

	@RequestMapping("/set")
	public String set(String key, String value) throws Exception {
		redisClient.setValue(key, value);
		return "success";
	}

	@RequestMapping("/get")
	public String get(String key) throws Exception {
		return redisClient.getValue(key);
	}

}
