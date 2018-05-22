package xsscd.monitor.air.southwest.modules.system.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.common.utils.SimplePage;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeLog;
import xsscd.monitor.air.southwest.modules.system.service.OrganizeLogMng;


/**
 * 系统日志
 * 
 */
@Controller
@RequestMapping("/system/log")
public class SysLogController {
	@Autowired
	private OrganizeLogMng sysLogService;
	
	/**
	 * 列表
	 */
	@ApiOperation(value="日志列表信息", notes="日志列表信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "跳转页码", required = false, dataType = "Integer",paramType = "page"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示记录条数", required = false, dataType = "Integer")
	})
	@ResponseBody
	@RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
	public R list(Integer page, Integer pageSize){
		PageHelper.startPage(SimplePage.cpn(page), SimplePage.cps(pageSize));
		List<OrganizeLog> logsList = sysLogService.getList(new HashMap<String, Object>());
		PageInfo<OrganizeLog> p = new PageInfo<OrganizeLog>(logsList);
		return R.ok().put("page", p);
	}
	
}
