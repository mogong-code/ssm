package cn.controller;

import cn.domain.Permission;
import cn.domain.Role;
import cn.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("roleController")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    /**
     * 通过id查找角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String roleId)throws Exception{
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissions
     * @return
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,
                                      @RequestParam(name = "ids",required = true)String[] permissions){
        System.out.println(roleId);
        for (String permissionid:permissions
             ) {
            System.out.println(permissionid);
        }
        roleService.addPermissionToRole(roleId,permissions);
        return "redirect:findAll.do";
    }

    /**
     * 查询角色尚未拥有的权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllRole.do")
    public ModelAndView findRoleByIdAndAllRole(@RequestParam(name = "id",required = true)String roleId){
        ModelAndView mv=new ModelAndView();
        List<Permission> otherPermission=roleService.findOtherPermission(roleId);
        mv.addObject("roleId",roleId);
        mv.addObject("permissionList",otherPermission);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * 查询所有
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @Secured({"ROLE_admin"})//设置访问权限
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name="pageSize",required = true,defaultValue = "10")Integer pageSize) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Role> roles=roleService.findAll(page,pageSize);
        PageInfo pageInfo=new PageInfo(roles);
        mv.addObject("pageInfo",pageInfo);
//        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 保存创建的角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 对角色表进行模糊查询
     * @param page
     * @param pageSize
     * @param findString
     * @return
     * @throws Exception
     */
    @RequestMapping("/findString.do")
    public ModelAndView findString(@RequestParam(name = "page",required = true,defaultValue ="1")Integer page,
                                   @RequestParam(name="pageSize",required = true,defaultValue = "10")Integer pageSize,
                                   @RequestParam(name = "findString",required = true,defaultValue = "")String findString)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Role> roles=roleService.findString(page,pageSize,findString);
        PageInfo pageInfo=new PageInfo(roles);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

}
