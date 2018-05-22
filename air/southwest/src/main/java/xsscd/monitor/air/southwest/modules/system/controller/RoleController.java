package xsscd.monitor.air.southwest.modules.system.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import xsscd.monitor.air.southwest.common.utils.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.common.utils.SimplePage;
import xsscd.monitor.air.southwest.common.utils.UUIDUtil;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Member;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Menu;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.OrganizeRole;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Permission;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.Role;
import xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto.RolePermission;
import xsscd.monitor.air.southwest.modules.system.service.MenuService;
import xsscd.monitor.air.southwest.modules.system.service.MenuTreeService;
import xsscd.monitor.air.southwest.modules.system.service.OrganizeRoleService;
import xsscd.monitor.air.southwest.modules.system.service.PermissionService;
import xsscd.monitor.air.southwest.modules.system.service.RolePermissionService;
/**
 */
@Api(tags = "角色管理")
@Controller
@RequestMapping("/role/role_do/")
public class RoleController extends BaseController {

    @Autowired
    OrganizeRoleService service;
    /*    @Autowired
    MenuService menuService;
    @Autowired
    MenuTreeService menuTreeService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RolePermissionService rolePermissionService;*/
    @ApiOperation(value = "角色管理页面",notes = "",produces = MediaType.TEXT_HTML_VALUE)
    @RequestMapping(value = { "index.do" },method = { RequestMethod.GET, RequestMethod.POST })
    public String index(){
        return "/admin/system/role/index";
    }
    @ApiOperation(value = "角色分页列表",notes = "JSON ",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = { "list.do" },method = { RequestMethod.GET, RequestMethod.POST })
    public Page list(/*@ModelAttribute RoleQuery qvo,*/@ModelAttribute Page page) throws Exception{
        //qvo.setBlurred(true);
		PageHelper.startPage(page.getCurPage(), page.getPageSize());
		List<OrganizeRole> roleList = service.getList(new HashMap<String, Object>());
		PageInfo<OrganizeRole> p = new PageInfo<OrganizeRole>(roleList);
        page.init();
        page.setResult(roleList);
        return page;
    }  

    /*//**
     * 跳转到新增角色页面
     * @return
     *//*
    @RequestMapping(value = "/goAddR" , method = RequestMethod.GET)
    public String goAddR(){
        return "admin/role/role_add";
    }

    *//**
     *  新增角色信息
     * @param params
     * @param response
     *//*
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public void addR(@RequestParam("params")String params, HttpServletResponse response){
        String[] strs = params.split(",");
        String roleName = strs[0];
        String roleDesc = strs[1];
        System.out.println(roleName+","+roleDesc);
        OrganizeRole role = new OrganizeRole();
        role.setRoleName(roleName);

        PrintWriter out = null;

        response.setCharacterEncoding("utf-8");

        JSONObject obj = new JSONObject();

        try {
            out = response.getWriter();
            service.save(role);
            obj.put("result","success");
            out.write(obj.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            obj.put("result","error");
            out.write(obj.toString());
            out.flush();
            out.close();
        }
    }

    *//**
     * 跳转到编辑角色信息页面
     * @param roleId
     * @param model
     * @return
     *//*
    @RequestMapping(value = "/goEditR", method = RequestMethod.GET)
    public String goEditR(@RequestParam("roleId")String roleId, Model model){
    	OrganizeRole role = service.findById(roleId);
        model.addAttribute("role",role);
        return "admin/role/role_edit";
    }

    *//**
     * 编辑角色信息
     * @param params
     *//*
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void editR(@RequestParam("params")String params, HttpServletResponse response){
        String strs[]=params.split(",");
        String roleId = strs[0];
        String roleName = strs[1];
        String roleDesc = strs[2];
        OrganizeRole bean = new OrganizeRole();

        PrintWriter out = null;

        response.setCharacterEncoding("utf-8");

        JSONObject obj = new JSONObject();

        try {
            out = response.getWriter();
            service.save(bean);
            obj.put("result","success");
            out.write(obj.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            obj.put("result","error");
            out.write(obj.toString());
            out.flush();
            out.close();
        }

    }

    *//**
     * 跳转到角色授权页面
     * @param roleId
     * @param model
     * @return
     *//*
    @RequestMapping(value = "/goAuthorise" )
    public String goAuth(@RequestParam String roleId, Model model){

        List<Menu> menuList = menuTreeService.findAll();

        OrganizeRole role = service.findById(roleId);

        Set<Permission> hasPermissions = null;

        if(role != null){
            //hasPermissions = role.getPermissions();
        }

        for (Menu m : menuList) {
            for(Permission p : hasPermissions){
                if(p.getMenu().getMenuId()==m.getMenuId()){
                    m.setHasSubMenu(true);
                }
            }
        }

        model.addAttribute("roleId" , roleId);

        JSONArray jsonArray = JSONArray.fromObject(menuList);
        String json = jsonArray.toString();

        json = json.replaceAll("menuId","id").replaceAll("parentId","pId").
                replaceAll("menuName","name").replaceAll("hasSubMenu","checked");

        model.addAttribute("menus",json);

        return "admin/role/role_auth";
    }

    *//**
     * 角色授权
     * @param response
     *//*
    @RequestMapping(value = "/authorise", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void authorise(@RequestParam("params")String params, HttpServletResponse response){

        String[] strs = params.split(";");

        String roleId = strs[0];
        String menuIdarr = strs[1];
        String[] menuIds = menuIdarr.split(",");

        PrintWriter out = null;

        response.setCharacterEncoding("utf-8");

        JSONObject obj = new JSONObject();

        try {
            out = response.getWriter();
            List<RolePermission> rplist = new ArrayList<RolePermission>();
            rplist = rolePermissionService.findById(Integer.parseInt(roleId));
            //先删除数据
            for(RolePermission r:rplist){
                rolePermissionService.doDel(r);
            }

            for (int i=0;i<menuIds.length;i++){
                //重新写入数据
                RolePermission rop = new RolePermission();
                rop.setRpId(UUIDUtil.getRandomNum());
                rop.setRoleId(Integer.parseInt(roleId));
                rop.setPermissionId(Integer.parseInt(menuIds[i]));
                rolePermissionService.doSave(rop);
            }

//            Role role = roleService.findByRoleId(roleId);
//            Set<Permission> permissions = role.getPermissions();
//
//            Set<Permission> hasPerms = role.getPermissions();
//
//            for(Permission p:permissions){
//                if(hasPerms.contains(p))
//                    continue;
//                role.getPermissions().add(p);
//            }
//
//            roleService.doSave(role);

            obj.put("result","success");
            out.write(obj.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            obj.put("result","error");
            out.write(obj.toString());
            out.flush();
            out.close();
        }

    }

//    public void delR(List<RolePermission> rplist){
//
//    }
*/
}
