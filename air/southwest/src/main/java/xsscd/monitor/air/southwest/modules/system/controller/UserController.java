package xsscd.monitor.air.southwest.modules.system.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import springfox.documentation.annotations.ApiIgnore;
import xsscd.monitor.air.southwest.common.utils.DateJsonValueProcessor;
import xsscd.monitor.air.southwest.common.utils.DateUtils;
import xsscd.monitor.air.southwest.common.utils.Page;
import xsscd.monitor.air.southwest.core.email.JavaEmailSender;
import xsscd.monitor.air.southwest.core.excel.ExcelViewWrite;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Member;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.User;
import xsscd.monitor.air.southwest.modules.system.service.UserService;
@Api(value = "用户管理接口",tags = "用户管理接口")
@Controller
@RequestMapping("/user/user_do/")
public class UserController extends BaseController {

    @Autowired
    UserService userService;
/*    @Autowired
    OrganizeRoleService roleService;
    @Autowired
    RolePageService rolePageService;*/
    /** 首页 **/
    @ApiOperation(value = "用户页面",notes = "",produces = MediaType.TEXT_HTML_VALUE)
    @RequestMapping(value = { "index.do" },method = { RequestMethod.GET, RequestMethod.POST })
    public String index(@ApiIgnore ModelMap modelMap){
        return "admin/system/user/index";
    }
    /**
     * 列表
     * 
     * @throws Exception
     **/
    @ApiOperation(value = "用户分页列表",notes = "",produces = MediaType.APPLICATION_JSON_VALUE)
    // @ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "java.lang.Integer"), @ApiImplicitParam(name = "qvo", value = "用户查询条件", required = false, dataType = "UserQvo") })
    @ResponseBody
    @RequestMapping(value = { "list.do" },method = { RequestMethod.GET, RequestMethod.POST })
    public Page list(@ModelAttribute Page page,@ApiIgnore ModelMap modelMap) throws Exception{
/*        page = userService.findAll(page.getCurPage(), page.getPageSize(), str);
        List<UserVO> list = (List<UserVO>) page.getResult();
        for (UserVO user : list) {
            // 禁止泄露
            user.setPassword("");
            // 禁止泄露
            user.setCredentialssalt("");
        }*/
		PageHelper.startPage(page.getCurPage(), page.getPageSize());
		List<Member> memberList = userService.findAll(0, 0, null);
		PageInfo<Member> p = new PageInfo<Member>(memberList);
        page.init();
        page.setResult(memberList);
        return page;
    }
    /**
     * 查询所有管理员信息并分页显示
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response, Model model) {
        String pageIndexStr = request.getParameter("pageIndex");

        int pageSize = xsscd.monitor.air.southwest.core.Constants.PAGE_SIZE;
        ModelAndView mv = this.getModelAndView();
        List<Member> userPage;

        if (pageIndexStr == null || "".equals(pageIndexStr)) {
            pageIndexStr = "0";
        }

        int pageIndex = Integer.parseInt(pageIndexStr);

        userPage = userService.findAll(pageIndex + 1, pageSize,  "id");
       // mv.addObject("totalCount", userPage.getTotalElements());
        mv.addObject("pageIndex", pageIndex);
//        JsonConfig cfg = new JsonConfig();
//        cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        JsonConfig jcg = new JsonConfig();
        jcg.registerJsonValueProcessor(Date.class,
                new DateJsonValueProcessor("yyyy-MM-dd"));
      //  JSONArray jsonArray = JSONArray.fromObject(userPage.getContent(), jcg);
        //System.out.println(jsonArray.toString());
      //  mv.addObject("users", jsonArray.toString());
        mv.setViewName("admin/user/sys_user_list");
        return mv;
    }

    /**
     * 根据关键字和日期查询并分页显示
     *
     * @param pageIndexStr
     * @param keyword
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    @RequestMapping(value = "/searchU", produces = "application/json;charset=UTf-8")
    @ResponseBody
    public ModelAndView doSearch(@RequestParam(value = "pageIndex",required = false) String pageIndexStr, @RequestParam(value = "keyword",required = false) String keyword,
                                 @RequestParam(value = "startDate",required = false) String startDateStr, @RequestParam(value = "endDate",required = false) String endDateStr) {
        int pageSize = xsscd.monitor.air.southwest.core.Constants.PAGE_SIZE;
        ModelAndView mv = this.getModelAndView();
        List<User> userList;

        if (pageIndexStr == null || "".equals(pageIndexStr)) {
            pageIndexStr = "0";
        }

        int pageIndex = Integer.parseInt(pageIndexStr);

        System.out.println(startDateStr + " and " + endDateStr);

        Date startDate = DateUtils.parse("yyyy-MM-dd", startDateStr);
        Date endDate = DateUtils.parse("yyyy-MM-dd", endDateStr);
/*        userList = userService.searchU(pageIndex + 1, pageSize, Sort.Direction.ASC, "id", keyword, startDate, endDate);

        for (User u : userPage.getContent()) {
            System.out.println(u.getUsername());
        }

        mv.addObject("totalCount", userPage.getTotalElements());
        mv.addObject("pageIndex", pageIndex);
//        JsonConfig cfg = new JsonConfig();
//        cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        JsonConfig jcg = new JsonConfig();
        jcg.registerJsonValueProcessor(Date.class,
                new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(userPage.getContent(), jcg);*/

       // mv.addObject("users", jsonArray.toString());
        mv.setViewName("admin/user/sys_user_list");
        return mv;
    }

    /**
     * 跳转到新增管理员页面
     *
     * @return
     */
    @RequestMapping(value = "/goAddU", method = RequestMethod.GET)
    public String goAddU() {
        return "admin/user/sys_user_add";
    }

    /**
     * 新增管理员
     *
     * @param params
     * @param response
     */
    @RequestMapping(value = "/addU", method = RequestMethod.POST)
    @ResponseBody
    public void addU(@RequestParam("params") String params, HttpServletResponse response) {
        String[] arrs = params.split(",");

        String username = arrs[0];
        String password = arrs[1];
        String phone = arrs[2];
        String sex = arrs[3];
        String email = arrs[4];
        String mark = arrs[5];

        String rank = "user";

        //Shiro框架SHA加密
        String passwordsha = new SimpleHash("SHA-1",username,password).toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordsha);
        user.setPhone(phone);
        user.setSex(sex);
        user.setEmail(email);
        user.setMark(mark);
        user.setRank(rank);
        user.setRegTime(new Date());
        user.setLocked(false);
        user.setLoginIp("127.0.0.1");
        user.setLastLogin(new Date());

        PrintWriter out = null;

        response.setCharacterEncoding("utf-8");

        JSONObject obj = new JSONObject();

        try {
            out = response.getWriter();
            userService.save(user);
            obj.put("result", "success");
            out.write(obj.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "error");
            out.write(obj.toString());
            out.flush();
            out.close();
        }

    }

    @RequestMapping(value = "/goEditU", method = RequestMethod.GET)
    public String goEditU(@RequestParam("userId")String userId, Model model) {
        User user = userService.findByUId(Integer.parseInt(userId));
        model.addAttribute("user",user);
        return "admin/user/sys_user_edit";
    }

    @RequestMapping(value = "/editU", method = RequestMethod.POST)
    @ResponseBody
    public void editU(@RequestParam("params")String params, HttpServletResponse response) {
        String[] arrs = params.split(",");

        String userid = arrs[0];
        String username = arrs[1];
        String password = arrs[2];
        String phone = arrs[3];
        String sex = arrs[4];
        String email = arrs[5];
        String mark = arrs[6];
        String loginIp = arrs[7];
        String lastLogin = arrs[8];

        String rank = "user";

        User user = new User();
        user.setId(Integer.parseInt(userid));
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setSex(sex);
        user.setEmail(email);
        user.setMark(mark);
        user.setRank(rank);
        user.setRegTime(new Date());
        user.setLocked(false);
        user.setLoginIp(loginIp);
        user.setLastLogin(DateUtils.parse("yyyy-MM-dd",lastLogin));

        PrintWriter out = null;

        response.setCharacterEncoding("utf-8");

        JSONObject obj = new JSONObject();

        try {
            out = response.getWriter();
            userService.save(user);
            obj.put("result", "success");
            out.write(obj.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "error");
            out.write(obj.toString());
            out.flush();
            out.close();
        }
    }

    /**
     * 跳转到分配用户角色页面
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/goAuthU")
    public String goAuthorise(@RequestParam("userId")String userId, Model model){

/*        User user = userService.findByUId(Integer.parseInt(userId));
        Set<Role> hasRoles = user.getRoles();

        List<Role> roleList = new ArrayList<Role>();
        for(Role r:hasRoles){
            roleList.add(r);
            System.out.println(r.getRoleName());
        }

        Iterable<Role> roles = rolePageService.findAllRole();

        List<RoleVO> rolevoes = new ArrayList<RoleVO>();

        for(Role r:roles){
            if(roleList.contains(r)){
                RoleVO vo = new RoleVO();
                vo.setRoleId(r.getRoleId());
                vo.setRoleName(r.getRoleName());
                vo.setRoleDesc(r.getRoleDesc());
                vo.setChecked(true);
                rolevoes.add(vo);
            }else{
                RoleVO vo = new RoleVO();
                vo.setRoleId(r.getRoleId());
                vo.setRoleName(r.getRoleName());
                vo.setRoleDesc(r.getRoleDesc());
                vo.setChecked(false);
                rolevoes.add(vo);
            }
        }

        model.addAttribute("roles",rolevoes);
        model.addAttribute("userId",userId);*/
        return "admin/user/sys_user_auth";
    }

    @RequestMapping(value = "/auth",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public void doAuth(@RequestParam("params")String params , HttpServletResponse response){

        String[] strs = params.split(";");
        String userId = strs[0];
        List<Integer> roleIds = new ArrayList<Integer>();
        if(strs.length>=2){
            if(strs[1].indexOf(",")!=-1){
                String[] ids = strs[1].split(",");
                for(int i =0;i<ids.length;i++){
                    roleIds.add(Integer.parseInt(ids[i]));
                }
            }else{
                roleIds.add(Integer.parseInt(strs[1]));
            }
        }

        PrintWriter out = null;

        response.setCharacterEncoding("utf-8");

        JSONObject obj = new JSONObject();

        try {
            out = response.getWriter();
            User user = userService.findByUId(Integer.parseInt(userId));
/*            List<Role> roles = roleService.findAll(roleIds);
            Set<Role> preRoles = user.getRoles();
            for(Role r:roles){
                if(preRoles.contains(r))
                    continue;
                user.getRoles().add(r);
            }*/
            userService.save(user);
            obj.put("result", "success");
            out.write(obj.toString());
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
            obj.put("result", "error");
            out.write(obj.toString());
            out.flush();
            out.close();
        }

    }

    /**
     * 跳转到发送邮件页面
     *
     * @return
     */
    @RequestMapping(value = "/goSendEmail")
    public ModelAndView goSendEmailPage(@RequestParam("toEmails") String toEmails) {
        ModelAndView mv = this.getModelAndView();
        mv.addObject("toEmails", toEmails);
        mv.setViewName("admin/common/send_email");
        return mv;
    }

    /**
     * 发送邮件
     *
     * @param toEmail
     * @param title
     * @param content
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/sendEmail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void sendEmail(@RequestParam("toEmail") String toEmail, @RequestParam("title") String title,
                          @RequestParam("content") String content, HttpServletResponse response) throws Exception {

        JavaEmailSender.sendEmail(toEmail, title, content);

        JSONObject obj = new JSONObject();
        obj.put("msg", "ok");
        PrintWriter out;

        response.setCharacterEncoding("utf-8");
        out = response.getWriter();
        out.write(obj.toString());

        out.flush();
        out.close();

    }

    /**
     * 导出管理员信息到Excel表
     *
     * @param idstr
     * @return
     */
    @RequestMapping(value = "/exportExcel", produces = "application/json;charset=UTF-8")
    public ModelAndView exportExcel(@RequestParam("ids") String idstr) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> titles = new ArrayList<String>();

        titles.add("序号");
        titles.add("用户名");
        titles.add("描述");
        titles.add("手机");
        titles.add("邮箱");
        titles.add("最近登录");
        titles.add("上次登录IP");

        dataMap.put("titles", titles);

        List<HashMap<String, Object>> values = new ArrayList<HashMap<String, Object>>();
        String[] ids = idstr.split(",");
        for (int i = 0; i < ids.length; i++) {
            User user = userService.findByUId(Integer.parseInt(ids[i]));
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("var1", user.getId());
            map.put("var2", user.getUsername());
            map.put("var3", user.getMark());
            map.put("var4", user.getPhone());
            map.put("var5", user.getEmail());
            map.put("var6", user.getLastLogin());
            map.put("var7", user.getLoginIp());
            values.add(map);
        }

        dataMap.put("values", values);

        ExcelViewWrite ev = new ExcelViewWrite();

        ModelAndView mv = new ModelAndView(ev, dataMap);

        return mv;
    }

}