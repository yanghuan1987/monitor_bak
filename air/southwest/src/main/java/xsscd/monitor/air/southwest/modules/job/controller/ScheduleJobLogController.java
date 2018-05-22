package xsscd.monitor.air.southwest.modules.job.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJobLog;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobLogService;

/**
 * 定时任务日志
 */
/*@Controller
@RequestMapping("/system/scheduleLog")*/
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
    @ApiOperation(value = "定时任务日志首页",notes = "",produces = MediaType.TEXT_HTML_VALUE)
    @RequestMapping(value = { "/index.do" },method = { RequestMethod.GET, RequestMethod.POST })
    public String index(HttpServletRequest request,HttpServletResponse response,ModelMap map){
        return "/admin/quartz/log/index";
    }
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public R list(@RequestParam Map<String, Object> params){
		scheduleJobLogService.getList(params);
		
		return R.ok().put("page", null);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public R info(@PathVariable("logId") String logId){
		EasymisScheduleJobLog log = scheduleJobLogService.findById(logId);
		
		return R.ok().put("log", log);
	}
}
