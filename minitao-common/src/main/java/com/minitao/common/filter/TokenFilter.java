package com.minitao.common.filter;

import cn.hutool.json.JSONUtil;
import com.minitao.common.config.FilterProperties;
import com.minitao.common.response.CommonResult;
import com.minitao.common.response.ResultCode;
import com.minitao.common.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-09-28 10:35
 **/
@Component
@EnableConfigurationProperties(FilterProperties.class)
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    private final String tokenHead = "taoToken";
    @Autowired
    private FilterProperties filterProperties;
    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @Override
    public void init(FilterConfig filterConfig)  {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestPath = request.getServletPath();
        System.out.println(request.getRequestURL());
        List<String> allowPath = filterProperties.getAllowPath();
        for (String path : allowPath) {
            //不需要token验证的请求  直接放过
            if (requestPath.startsWith(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        //解密token
        String token = request.getHeader(tokenHead);
        if (StringUtils.isBlank(token)) {
            servletResponse.getWriter().write(JSONUtil.toJsonStr(CommonResult.failed(ResultCode.UNAUTHORIZED)));
            return;
        }
        Claims body = jwtTokenUtil.getClaimsFromToken(token);

        // 定义新的消息头
        request.setAttribute("UserInfo", JSONUtil.toJsonStr(body));

        filterChain.doFilter(servletRequest, servletResponse);

    }

}
