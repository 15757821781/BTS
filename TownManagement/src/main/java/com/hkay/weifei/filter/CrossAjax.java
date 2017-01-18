package com.hkay.weifei.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/10.
 */
public class CrossAjax implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        httpServletResponse
                .setHeader(
                        "Access-Control-Allow-Headers",
                        "User-Agent,Origin,Cache-Control,Content-type,Date,Server,withCredentials,AccessToken");


        httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                "true");


        httpServletResponse.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        httpServletResponse.setHeader("Access-Control-Max-Age", "1209600");


        httpServletResponse.setHeader("Access-Control-Expose-Headers",
                "accesstoken");


        httpServletResponse.setHeader("Access-Control-Request-Headers",
                "accesstoken");


        httpServletResponse.setHeader("Expires", "-1");


        httpServletResponse.setHeader("Cache-Control", "no-cache");


        httpServletResponse.setHeader("pragma", "no-cache");

        filterChain.doFilter(httpRequest,httpServletResponse);

    }

    @Override
    public void destroy() {

    }
}
