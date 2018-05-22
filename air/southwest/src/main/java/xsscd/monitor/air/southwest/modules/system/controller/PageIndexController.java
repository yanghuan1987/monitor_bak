package xsscd.monitor.air.southwest.modules.system.controller;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Deament
 * 
 * @date 2016/9/31
 ***/
@Api(tags = "平台角色首页加载API")
@Controller
public class PageIndexController {
    

    @ApiOperation(value = "登录成功后的页面 ",notes = "",produces = MediaType.TEXT_HTML_VALUE)
    @RequestMapping(value = "/common/login_do/index.do",method = { RequestMethod.GET, RequestMethod.POST })
    public String index(Model model) throws Exception{
/*        if (user == null) {
            return "redirect:/common/login_do/tologin.do";
        }
        // 取消加载 菜单
        // 登陆的信息回传页面
        model.addAttribute("systemname", SystemConstant.getSystemName());
        model.addAttribute("systemauth", SystemConstant.getSystemAuth());
        model.addAttribute("icp", SystemConstant.getICP());
        model.addAttribute("systemdomain", SystemConstant.getSystemdomain());
        model.addAttribute("userFormMap", user);*/
        return "/index/admin/index";
    }
    
    /** 加载欢迎页面 **/
    @ApiOperation(value = "登录成功后的页面-欢迎页面 ",notes = "",produces = MediaType.TEXT_HTML_VALUE)
    @RequestMapping(value = "/common/login_do/welcome.do",method = { RequestMethod.GET, RequestMethod.POST })
    public String welcome(/*@AuthenticationPrincipal UserVO user,*/ModelMap map){
/*        if (user == null) {
            return "";
        }
        map.put("userFormMap", user);
        if (RbacConstant.isAdmin(user)) {
            return "/index/admin/welcome";
        }
        if (RbacConstant.isOther(user)) {
            // 让用户选择子系统
            return "/index/other/welcome";
        }*/
        return "/index/admin/welcome";
    }
    
  
}
