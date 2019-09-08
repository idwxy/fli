package com.idwxy.fli.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// 通过监听 session 统计在线人数
@WebListener
public class OnlineListener implements HttpSessionListener {

    // 记录当前登录用户数
    private int onlineNumber = 0;

    @Override
    public  void sessionCreated(HttpSessionEvent sessionEvent) {
        // 监听到创建了新的 session，在线用户累加 1
        onlineNumber++;
        // 设置 session 属性
        sessionEvent.getSession().getServletContext().setAttribute("onlineNumber", onlineNumber);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        // 监听到销毁了旧的 session，在线用户减少 1
        onlineNumber--;
        // 设置 session 属性
        sessionEvent.getSession().getServletContext().setAttribute("onlineNumber", onlineNumber);
    }
}
