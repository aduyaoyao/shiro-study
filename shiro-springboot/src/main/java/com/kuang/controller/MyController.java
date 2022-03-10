package com.kuang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @GetMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg", "hello,shiro");
        return "index";
    }
    @GetMapping("/user/add")
    public String add(){
        return "user/add";
    }
    @GetMapping("/user/update")
    public String update(){
        return "user/update";
    }
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            //执行登录
            subject.login(token);
            return "index";
            //若没有指定的账户，则shiro会抛出UnknownAccountException异常
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "用户名错误");
            return "login";
            //若账户存在，但是密码不匹配，则shiro会抛出IncorrectCredentialsException异常
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码错误");
            return "login";
            //用户被锁定的异常 LockedAccountException
        }
    }

    @GetMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未经授权的页面";
    }
}
