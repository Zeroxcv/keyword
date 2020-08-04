package com.lisk.keyword.shiro;

import com.lisk.keyword.pojo.User;
import com.lisk.keyword.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService;

    /**
     * 执行授权逻辑
     * 只要访问加上授权的资源都会调用改方法
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //添加授权字符串 这个字符串和加上权限的字符串一致
        //info.addStringPermission("user:add");
        //到数据库查询当前用户的授权的字符串
        Subject subject= SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();//这里的CLogin对象是登陆的时候传过来的
        User dbclogin=userService.FindUserLogin(user.getUsername());
        info.addStringPermission(dbclogin.getPerms());
        return info;
    }
    /**
     * 执行认证逻辑
     * 只要使用subject.login(token) 就会调用改方法
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro判断逻辑，判断用户名和密码
        //1、判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user=userService.FindUserLogin(token.getUsername());
        if(user==null){
            //用户名不存在
            return null;//shiro底层抛出UnknownAccountException
        }
        //2、判断密码 三个参数：1、返回给subject.login(token);方法的参数  2、数据库中的密码 3、shiro的名字
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
