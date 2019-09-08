package com.idwxy.fli.filter;

import com.idwxy.fli.common.ResultObject;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

// 登录过滤器，过滤除登录接口外的所有接口，通过检查session属性---user实现登录功能
public class LoginFilter implements Filter {

    // 存储 FilterConfig 中不需要过滤的 URLs
    private static String exclusions;
    // 把 FilterConfig 中不需要过滤的 URLs 分别存储到列表
    private static String[] ALLOWEDURLS;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        // 获取在 FilterConfig 中配置好的，不需要过滤的 URLs
        exclusions = filterConfig.getInitParameter("exclusions");
        if (!exclusions.isEmpty()) {
            // 获取不需要过滤的 URLs，可以直接访问的 URLs
            ALLOWEDURLS = exclusions.split(",");
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取当前请求的 URL
        String url = request.getRequestURI();
        // 获取 session，false 表示如果没有获取到也不会创建
        HttpSession session = request.getSession(false);
        // 如果当前请求的 URL 不需要过滤或者以及登录的用户请求，则继续处理
        if (isAllowed(url) || session != null && session.getAttribute("user") != null) {
            filterChain.doFilter(request, response);
        } else {
            // 若用户没有登录，则直接返回
            render(response, "未登录");
        }
    }

    // 当前请求是否需要过滤
    public boolean isAllowed(String url) {
        for (String ALLOWEDURL : ALLOWEDURLS) {
            if (ALLOWEDURL.equals(url)) {
                return true;
            }
        }
        return false;
    }

    // 返回请求失败消息
    public void render(HttpServletResponse response, String msg) throws IOException {
        // 返回失败信息
        ResultObject resultObject = new ResultObject(406,"请求失败", msg);
        // 生成 json 数据
        JSONObject object = new JSONObject(resultObject);
        // 返回 json 数据
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write(object.toString().getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
