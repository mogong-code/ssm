package cn.controller;

import cn.domain.Permission;
import cn.domain.Role;
import cn.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("permissController")
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询全部
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
        List<Permission> permissions=permissionService.findAll(page,pageSize);
        //PageInfo就是一个分页bean
        PageInfo pageInfo=new PageInfo(permissions);
        mv.addObject("pageInfo",pageInfo);
//        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    /**
     * 保存
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 通过id查找相关权限
     * @param permissionId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String permissionId) throws Exception{
        ModelAndView mv=new ModelAndView();
        Permission permission=permissionService.findById(permissionId);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    /**
     * 对权限表进行模糊查询
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
        List<Permission> permissions=permissionService.findString(page,pageSize,findString);
        PageInfo pageInfo=new PageInfo(permissions);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }
}
