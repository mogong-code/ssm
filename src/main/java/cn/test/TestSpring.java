package cn.test;

import cn.dao.IUserDao;
import cn.domain.Orders;
import cn.domain.Product;
import cn.domain.SysLog;
import cn.domain.UserInfo;
import cn.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;

public class TestSpring {

    private ApplicationContext ac;
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Before
    public void init(){
        ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }
    @After
    public void closesql(){
    }

    @Test
    public  void  testfindAllByPage() throws Exception {
        //加载配置文件
        //获取对象
        IOrdersService as= (IOrdersService) ac.getBean("ordersService");
        List<Orders> orders=as.findAll(0,4);
        for (Orders order:orders) {
            System.out.println(order);
        }
    }
    @Test
    public  void  testOrdersFindById() throws Exception {
        //获取对象
        IOrdersService as= (IOrdersService) ac.getBean("ordersService");
        Orders orders=as.findById("0E7231DC797C486290E8713CA3C6ECCC");
        System.out.println(orders);
    }
    @Test
    public  void  testUserFindByName() throws Exception {
        //获取对象
        IUserService as= (IUserService) ac.getBean("userService");
        UserInfo userInfo =as.findByUsername("admin");
        System.out.println(userInfo);
    }
    @Test
    public void testEncodePassword(){
        String passowrd="user";
        String pwd=bCryptPasswordEncoder.encode(passowrd);
        System.out.println(pwd);
        String pwd2=bCryptPasswordEncoder.encode(pwd);
        System.out.println(pwd2);
    }
    @Test
    public  void  testUserfindAll() throws Exception {
        //获取对象
        IUserService as= (IUserService) ac.getBean("userService");
        List<UserInfo> userInfos =as.findAll(1,4);
        for (UserInfo userInfo:userInfos) {
            System.out.println(userInfo);
        }
    }
    @Test
    public  void  testSaveUser() throws Exception {
        UserInfo userInfo=new UserInfo();
        //email,username,password,phoneNum,status
        userInfo.setEmail("1658858452@qq.com");
        userInfo.setUsername("123");
        userInfo.setPassword("123");
        userInfo.setPhoneNum("17302265262");
        userInfo.setStatus(1);
        //获取对象
        IUserService as= (IUserService) ac.getBean("userService");
        as.saveUser(userInfo);
    }
    @Test
    public  void  testUserfindById() throws Exception {
        //获取对象
        IUserService as= (IUserService) ac.getBean("userService");
        UserInfo userInfo =as.findById("1");
        System.out.println(userInfo);
    }
    @Test
    public  void  testAddPermissionToRole() throws Exception {
        String[] permissionIds={"1","2"};
        //获取对象
        IRoleService as= (IRoleService) ac.getBean("roleService");
        as.addPermissionToRole("8c3cc0fb-0f96-11ea-a745-b06ebf0d3dea",permissionIds);
    }
    @Test
    public  void  testsysLogServiceSave() throws Exception {
        //将日志相关信息封装到SysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(new Long(12)); //执行时长
        sysLog.setIp("ip");
        sysLog.setMethod("[类名] clazz.getName() [方法名]  method.getName()");
        sysLog.setUrl("url");
        sysLog.setUsername("username");
        sysLog.setVisitTime(new Date());
        //获取对象
        ISysLogService as= (ISysLogService) ac.getBean("sysLogService");
        as.save(sysLog);
    }
    @Test
    public  void  testSysLogfindString() throws Exception {
        //获取对象
        ISysLogService as= (ISysLogService) ac.getBean("sysLogService");
        List<SysLog>sysLogs=as.findString(0,3,"2019");
        for (SysLog sysLog:sysLogs
             ) {
            System.out.println(sysLog);
        }
    }
    @Test
    public  void  testProductAlter() throws Exception {
        //获取对象
        IProductService as= (IProductService) ac.getBean("productService");
        Product product=new Product();
        product.setProductNum("itcast-003");
        product.setProductName("上海石日游");
        product.setCityName("上海");
        product.setDepartureTime(new Date());
        product.setProductPrice(1800.0);
        product.setProductDesc("aaaaaa");
        product.setProductStatus(1);
        product.setId("1287ABF2A4C544568B8A7C69F36BF8B7");
        as.alter(product);
    }

    @Test
    public  void  testProductfindByIdOrders() throws Exception {
        //获取对象
        IProductService as= (IProductService) ac.getBean("productService");
        List<Orders> orders=as.findByIdOrders(0,2,"9F71F01CB448476DAFB309AA6DF9497F");
        for (Orders orders1:orders
        ) {
            System.out.println(orders1);
        }
    }
    @Test
    public  void  testUserAlter() throws Exception {
        //获取对象
        IUserService as= (IUserService) ac.getBean("userService");
        UserInfo userInfo=new UserInfo();
        userInfo.setId("077cc486-27a6-11ea-acaa-005056c00001");
        userInfo.setEmail("222222");
        userInfo.setUsername("text2");
        userInfo.setPassword("$2a$10$VdQYMv3PQfkYCAmlYga83eeItGAYzSVoGjgOF21tQGhPJKi4g1jfm");
        userInfo.setPhoneNum("2222222");
        userInfo.setStatus(1);
        as.alter(userInfo);
    }
}
