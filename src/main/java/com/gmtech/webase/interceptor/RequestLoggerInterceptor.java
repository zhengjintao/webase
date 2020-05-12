package com.gmtech.webase.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

public class RequestLoggerInterceptor extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggerInterceptor.class);
    
	// ①コントローラメソッドの実行前に呼ばれる
    @Override
    public boolean preHandle(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    Object handler) throws Exception {
    	showParams(request);
    	return true;
    }
    
    private void showParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                if (paramValues[0].length() != 0) {
                    params.put(paramName, paramValues[0]);
                }
            }
        }
        Map<String, String> heads = new HashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            String values = request.getHeader(name);
            heads.put(name, values);
        }

        logger.info(
        		"RemoteAddr:" + request.getRemoteAddr()
        		+ " Method:" + request.getMethod()
        		+ " URL:" + request.getRequestURL()
        		+ " params:" + JSON.toJSONString(params)
        		+ " heads:" + JSON.toJSONString(heads));
    }

    // ②コントローラメソッドの実行後に呼ばれる
    @Override
    public void postHandle(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Object handler,
                        ModelAndView modelAndView) throws Exception {
    	logger.info("postHandle");
    }

    // ③リクエスト処理の完了後に呼ばれる
    @Override
    public void afterCompletion(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Object handler, Exception ex) throws Exception {
    	logger.info("afterCompletion");
    }
}
