package com.idwxy.fli.interceptor;

import com.idwxy.fli.common.ResultObject;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

// 敏感词汇拦截器，通过拦截/user/search接口，获取请求参数，实现拦截敏感词汇拦截功能
@Component
public class WordInterceptor implements HandlerInterceptor {

    // 设置敏感词汇
    private static final String[] forbitWords = {"admin", "user", "test"};

    // 处理前方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 获取请求中的参数 word
        String word = request.getParameter("word");
        // 判断 word 参数是否在敏感词汇中
        for (String forbitWord : forbitWords) {
            if (word.equals(forbitWord)) {
                render(response, "search word " + "'" + word + "'" + " is not allowed.");
                // 返回假，中断请求
                return false;
            }
        }
        // 返回真，继续处理请求
        return true;
    }

    // 处理后方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    // 完成后方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

    }

    // 返回假消息
    public void render(HttpServletResponse response, String msg) throws IOException {
        // 返回失败信息
        ResultObject resultObject = new ResultObject(412, msg, null);
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
