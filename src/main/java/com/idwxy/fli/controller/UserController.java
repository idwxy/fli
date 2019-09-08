package com.idwxy.fli.controller;

import com.idwxy.fli.common.ResultObject;
import com.idwxy.fli.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    // 统计在线用户数
    @GetMapping("/online")
    public ResultObject setSession(HttpServletRequest request, HttpServletResponse response) {

        // 获取 session
        HttpSession session = request.getSession();
        // 获取 sessionId，即 JSESSIONID
        String sessionId = session.getId();
        System.out.println(sessionId);
        // 获取 session 属性
        Object onlineNumber = session.getServletContext().getAttribute("onlineNumber");
        System.out.println(onlineNumber);
        return new ResultObject(onlineNumber);
    }

    // 登录验证
    @PostMapping("/login")
    public ResultObject login(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {

        // 设置一个测试用户
        UserEntity realUser = new UserEntity("test", "test");
        // 判断用户和密码是否正确
        if (realUser.equals(user)) {
            // 获取 session，获取不到则新建
            HttpSession session = request.getSession();
            // 获取 sessionId, 即 JSESSIONID
            String sessionId = session.getId();
            System.out.println(sessionId);
            // 设置 session 属性
            session.setAttribute("user", user);
            return new ResultObject("登录成功");
        }
        return new ResultObject(406, "用户名或密码错误", "登陆失败");
    }
}
