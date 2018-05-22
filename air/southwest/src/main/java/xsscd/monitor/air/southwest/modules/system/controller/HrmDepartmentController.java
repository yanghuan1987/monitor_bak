package xsscd.monitor.air.southwest.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xsscd.monitor.air.southwest.common.utils.R;

/**
 * * 部门管理
 * 
 \* @author tanyujie
 *
 */
@Controller
@RequestMapping("/admin/department")
public class HrmDepartmentController {
	public R list() {
		return R.ok();
	}
}
