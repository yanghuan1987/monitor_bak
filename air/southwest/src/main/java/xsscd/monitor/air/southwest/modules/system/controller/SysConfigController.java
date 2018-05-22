package xsscd.monitor.air.southwest.modules.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xsscd.monitor.air.southwest.common.utils.R;

/**
 * * 系统配置信息
 * 
  @author tanyujie
 *
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {
	@RequestMapping("/list")
	public R list() {
		return new R().ok();
	}
}
