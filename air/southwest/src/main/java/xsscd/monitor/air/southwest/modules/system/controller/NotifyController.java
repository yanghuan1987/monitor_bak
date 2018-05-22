package xsscd.monitor.air.southwest.modules.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
/**
 * 通知通告
 \* @author tanyujie
 *
 */
public class NotifyController {
	@GetMapping()
	String oaNotify() {
		return "oa/notify/notify";
	}

}
