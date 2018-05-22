package xsscd.monitor.air.southwest.modules.system.aop;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import xsscd.monitor.air.southwest.annotation.SysLog;
import xsscd.monitor.air.southwest.common.utils.IPUtils;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeLog;
import xsscd.monitor.air.southwest.modules.system.service.OrganizeLogMng;

/**
 * 系统日志，切面处理类
 \* @author tanyujie
 *
 */
@Aspect
@Component
public class LogAspect {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrganizeLogMng sysLogService;
	@Pointcut("@annotation(xsscd.monitor.air.southwest.annotation.SysLog)")
    public void controllerAspect() {}

    @Pointcut("execution(public * xsscd.monitor.air.southwest.*.*.service.impl.*.*(..))")
    public void serviceAspect() {}


    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
        Object result = null;
		long time = 0;
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            LOGGER.info("=====前置通知开始=====");
            LOGGER.info("请求接口:" + request.getMethod() + "[" + request.getRequestURL() + "]");
            LOGGER.info("请求参数:" + request.getQueryString());
          //执行方法
            result = joinPoint.proceed();
          //执行时长(毫秒)
    		time = System.currentTimeMillis() - beginTime;
            LOGGER.info("=====后置通知开始=====");
            LOGGER.info("接口返回:" + JSON.toJSONString(result));


        }catch (Exception e){
            LOGGER.error("异常信息:{}", e.getMessage());
            LOGGER.info("=====全局异常处理=====");

            Map<String, Object> map = new HashMap<>(16);
            map.put("error", e.getClass().getName());
            map.put("type", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		//保存日志
		saveSysLog(joinPoint, time,1);
        return result;
    }

    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
		long beginTime = System.currentTimeMillis();
		long time = 0;
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            //获取请求ip
            String ip = request.getRemoteAddr();
            //执行时长(毫秒)
      		time = System.currentTimeMillis() - beginTime;
            //获取用户请求方法的参数并序列化为JSON格式字符串
            String params = "";
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
                }
            }
              /*========控制台输出=========*/
            LOGGER.info("=====异常通知开始=====");
            LOGGER.info("异常代码:" + e.getClass().getName());
            LOGGER.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            LOGGER.info("请求IP:" + ip);
            LOGGER.info("请求参数:" + params);
        }catch (Exception ex) {
            //记录本地异常日志
            LOGGER.error("==异常通知异常==");
            LOGGER.error("异常信息:{}", ex.getMessage());
        }
		//保存日志
		saveSysLog(joinPoint, time,0);
    }
	private void saveSysLog(JoinPoint joinPoint, long time,int status) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		OrganizeLog sysLog = new OrganizeLog();
		sysLog.setStatus(status);
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args[0]);
			sysLog.setParams(params);
		}catch (Exception e){

		}

		//获取request
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		if (ra != null) {
			ServletRequestAttributes sra = (ServletRequestAttributes) ra;
			HttpServletRequest request = sra.getRequest();
			// 设置IP地址
			sysLog.setIp(IPUtils.getIpAddr(request));
		}
		//用户名
		//String username = ((Member) SecurityUtils.getSubject().getPrincipal()).getName();
		try {
		if(null!=SecurityUtils.getSubject()&&null!=SecurityUtils.getSubject().getPrincipal())
		sysLog.setUsername(SecurityUtils.getSubject().getPrincipal().toString());
		}catch(Exception ex) {
		}
		sysLog.setRunTime((int)time);
		sysLog.setCreateTime(new Date());
		//保存系统日志
		sysLogService.save(sysLog);
	}

}
