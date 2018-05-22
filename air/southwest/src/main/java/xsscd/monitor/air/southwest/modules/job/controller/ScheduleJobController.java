package xsscd.monitor.air.southwest.modules.job.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import xsscd.monitor.air.southwest.annotation.SysLog;
import xsscd.monitor.air.southwest.common.utils.Page;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.EasymisScheduleJob;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobService;

/**
 * 定时任务
 *
 */
@Controller
@RequestMapping("/system/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
    @ApiOperation(value = "定时任务列表",notes = "",produces = MediaType.TEXT_HTML_VALUE)
    @RequestMapping(value = { "/index.do" },method = { RequestMethod.GET, RequestMethod.POST })
    public String index(){
        return "/admin/quartz/job/index";
    }
    /**
     * 定时任务列表
     * 
     * @throws Exception
     */
    @ApiOperation(value = "定时任务列表",notes = "",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = { "/list.do" },method = { RequestMethod.GET, RequestMethod.POST })
    public Page list(@ModelAttribute Page page,ModelMap map) throws Exception{
		PageHelper.startPage(page.getCurPage(), page.getPageSize());
		List<EasymisScheduleJob> scheduleJobList=scheduleJobService.getList(new HashMap<String, Object>());
		PageInfo<EasymisScheduleJob> p = new PageInfo<EasymisScheduleJob>(scheduleJobList);
        page.init();
        page.setResult(scheduleJobList);
        page.setCurPage(page.getCurPage());
        return page;
    }
	/**
	 * 定时任务列表
	 */
	@ApiOperation(value="定时任务列表", notes="定时任务列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "跳转页码", required = false, dataType = "Integer",paramType = "page"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示记录条数", required = false, dataType = "Integer")
	})
	@RequestMapping(value = "/list",method = {RequestMethod.POST,RequestMethod.GET})
	public R list(Integer page, Integer pageSize) {
		List<?> list=scheduleJobService.getList(new HashMap<String, Object>());
		return R.ok().put("page", list);
	}

	/**
	 * 定时任务信息
	 */
	@ApiOperation(value="定时任务信息", notes="根据url的jobId来指定浏览信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "jobId", value = "定时任务id", required = false, dataType = "Integer",paramType = "path")
	})
	@RequestMapping(value = "/info/{jobId}",method = {RequestMethod.POST,RequestMethod.GET})
	public R info(@PathVariable("jobId") String jobId) {
		EasymisScheduleJob schedule = scheduleJobService.findById(jobId);
		return R.ok().put("schedule", schedule);
	}

	/**
	 * 保存定时任务
	 */
	@ApiOperation(value="保存定时任务", notes="保存定时任务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "scheduleJob", value = "定时任务实体", required = false, dataType = "EasymisScheduleJob",paramType = "EasymisScheduleJob")
	})
	@SysLog("保存定时任务")
	@RequestMapping(value = "/save",method = {RequestMethod.POST,RequestMethod.GET})
	public R save(@RequestBody EasymisScheduleJob scheduleJob) {
		scheduleJobService.save(scheduleJob);
		return R.ok();
	}

	/**
	 * 修改定时任务
	 */
	@ApiOperation(value="修改定时任务", notes="修改定时任务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "scheduleJob", value = "定时任务实体", required = false, dataType = "EasymisScheduleJob",paramType = "EasymisScheduleJob")
	})
	@SysLog("修改定时任务")
	@RequestMapping(value = "/update",method = {RequestMethod.POST,RequestMethod.GET})
	public R update(@RequestBody EasymisScheduleJob scheduleJob) {
		scheduleJobService.update(scheduleJob);
		return R.ok();
	}
	/**
	 * 删除定时任务
	 */
	@ApiOperation(value="删除定时任务", notes="删除定时任务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "jobIds", value = "定时任务id", required = false, dataType = "String")
	})
	@SysLog("删除定时任务")
	@RequestMapping(value = "/delete",method = {RequestMethod.POST,RequestMethod.GET})
	public R delete(@RequestBody String[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@ApiOperation(value="立即执行任务", notes="立即执行任务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "jobIds", value = "定时任务id", required = false, dataType = "String")
	})
	@SysLog("立即执行任务")
	@RequestMapping(value = "/run",method = {RequestMethod.POST,RequestMethod.GET})
	public R run(@RequestBody String[] jobIds){
		scheduleJobService.run(jobIds);		
		return R.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@ApiOperation(value="暂停定时任务", notes="暂停定时任务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "jobIds", value = "定时任务id", required = false, dataType = "String")
	})
	@SysLog("暂停定时任务")
	@RequestMapping(value = "/pause",method = {RequestMethod.POST,RequestMethod.GET})
	public R pause(@RequestBody String[] jobIds){
		scheduleJobService.pause(jobIds);		
		return R.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@ApiOperation(value="恢复定时任务", notes="恢复定时任务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "jobIds", value = "定时任务id", required = false, dataType = "String")
	})
	@SysLog("恢复定时任务")
	@RequestMapping(value = "/resume",method = {RequestMethod.POST,RequestMethod.GET})
	public R resume(@RequestBody String[] jobIds){
		scheduleJobService.resume(jobIds);		
		return R.ok();
	}
}
