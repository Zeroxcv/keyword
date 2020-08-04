package com.lisk.keyword.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class userController {
    @RequestMapping("/")
    public String wellcome(Model model){
        model.addAttribute("index","Hello World");
        return "/index";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        System.out.println();
        return "/login";
    }

    /**
     * 跳转到添加用户信息
     * */
    @RequiresPermissions("user:add")
    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("/user/update")
    public String update(){
        return "/user/update";
    }
    /**
     * 跳转到未授权界面
     * */
    @RequestMapping("/unauth")
    public String unauth(){
        return "/unauth";
    }
    /**
     * 登陆+shiro处理逻辑
     * */
    @RequestMapping("/login")
    public String login(String name, String password, Model model){
        /**
         * 使用Shiro编写认证操作
         * 1、获取Subject
         *      Subject subject= SecurityUtils.getSubject();
         * 2、封装用户数据
         *      UsernamePasswordToken token=new UsernamePasswordToken(name,password);
         * 3、执行登陆方法
         *      subject.login(token);
         * */
        Subject subject= SecurityUtils.getSubject();
        //这里使用import org.apache.shiro.crypto.hash.Md5Hash;
        //shiro自带的MD5加密密码(因为数据库中的密码是md5的)，下面也有java代码将字符串进行md5加密代码
        UsernamePasswordToken token=new UsernamePasswordToken(name, new Md5Hash(password).toString());
        try {
            subject.login(token);
            //登陆成功
            return "redirect:/";
        }catch (UnknownAccountException e){
            //登陆失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
}
