package xsscd.monitor.air.southwest.modules.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;
import xsscd.monitor.air.southwest.WebMain;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.common.utils.SimplePage;
import xsscd.monitor.air.southwest.core.email.JavaEmailSender;
import xsscd.monitor.air.southwest.core.excel.ExcelUtil;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.HrmStaffInfo;
import xsscd.monitor.air.southwest.modules.system.service.HrmStaffInfoMng;

/**
 * 员工信息
 * 
 \* @author tanyujie
 *
 */
@Controller
@RequestMapping("/admin/staff")
public class HrmStaffInfoController extends BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(WebMain.class);
	@Autowired
	private HrmStaffInfoMng service;

	/**
	 * 查询所有管理员信息并分页显示
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findAll", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public R findAll(Integer page, Integer pageSize) {
		PageHelper.startPage(SimplePage.cpn(page), SimplePage.cps(pageSize));
		List<HrmStaffInfo> logsList = service.getList(new HashMap<String, Object>());
		PageInfo<HrmStaffInfo> p = new PageInfo<HrmStaffInfo>(logsList);
		return R.ok().put("page", p);
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
	public R doSearch(@RequestParam(value = "pageIndex", required = false) String pageIndexStr,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "startDate", required = false) String startDateStr,
			@RequestParam(value = "endDate", required = false) String endDateStr) {

		System.out.println(startDateStr + " and " + endDateStr);

		/*
		 * Date startDate = DateUtils.parse("yyyy-MM-dd", startDateStr); Date endDate =
		 * DateUtils.parse("yyyy-MM-dd", endDateStr);
		 */
		return R.ok().put("page", null);
	}

	// 跳转到新增管理员页面
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public void add(@RequestParam("params") String params, HttpServletResponse response) {

	}

	//
	@RequestMapping(value = "/goEditU", method = RequestMethod.GET)
	public String goEditU(@RequestParam("userId") String staffId, Model model) {
		HrmStaffInfo user = service.findById(staffId);
		model.addAttribute("user", user);
		return "admin/user/sys_user_edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public void editU(@RequestParam("params") String params, HttpServletResponse response) {
	}

	// 跳转到分配用户角色页面
	@RequestMapping(value = "/auth", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void doAuth(@RequestParam("params") String params, HttpServletResponse response) {
	}

	/**
	 * 跳转到发送邮件页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/forward/sendEmail")
	public ModelAndView goSendEmailPage(@RequestParam("toEmails") String toEmails, ModelMap model) {
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
	 * @throws IOException 
	 */
	@RequestMapping(value = "/exportExcel", produces = "application/json;charset=UTF-8")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,@RequestParam("ids") String idstr) throws IOException {
		List<String> titles = new ArrayList<String>();
		titles.add("工号");
		titles.add("姓名");
		titles.add("部门");
		titles.add("手机");
		titles.add("邮箱");
		List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		String[] ids = idstr.split(",");
	    List<HrmStaffInfo> list = new ArrayList<HrmStaffInfo>();
		for (int i = 0; i < ids.length; i++) {
			HrmStaffInfo staff = service.findById(ids[i]);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("var1", staff.getWorkNo());
			map.put("var2", staff.getName());
			map.put("var3", staff.getDepartmentName());
			map.put("var4", staff.getMobile());
			map.put("var5", staff.getEmail());
			dataList.add(map);
			 list.add(staff);
		}
		ExcelUtil.getInstance().exportObj2Excel(response, titles, dataList);
		
	}
	/**
	 * 
	* @Title: exportExcel
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param file
	* @param @param request
	* @param @param response
	* @param @param session    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = "/importExcel", produces = "application/json;charset=UTF-8")
	public void exportExcel(@RequestParam(value = "filename") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		// 判断文件是否为空
		if (file == null) {
			session.setAttribute("msg", "文件不能为空！");
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 验证文件名是否合格
		if (!ExcelUtil.validateExcel(fileName)) {
			session.setAttribute("msg", "文件必须是excel格式！");
		}

		// 进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (StringUtils.isEmpty(fileName) || size == 0) {
			session.setAttribute("msg", "文件不能为空！");
		}
		// 批量导入
		service.batchImport(fileName, file);

	}

}
