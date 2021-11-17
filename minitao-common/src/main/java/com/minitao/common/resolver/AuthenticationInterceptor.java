package com.minitao.common.resolver;

import com.minitao.common.config.FilterProperties;
import com.minitao.common.consts.UserConsts;
import com.minitao.common.entity.User;
import com.minitao.common.exception.ExceptionMessageEnum;
import com.minitao.common.exception.TaoException;
import com.minitao.common.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-13 20:38
 **/
@EnableConfigurationProperties(FilterProperties.class)
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final String tokenHead = "taoToken";
    @Autowired
    private FilterProperties filterProperties;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getServletPath();
        List<String> allowPath = filterProperties.getAllowPath();
        if (!CollectionUtils.isEmpty(allowPath)) {
            for (String path : allowPath) {
                if (requestPath.startsWith(path)) {
                    return true;
                }
            }
        }
        //解密token
        String token = request.getHeader(tokenHead);
        if (StringUtils.isBlank(token)) {
            TaoException.fail(ExceptionMessageEnum.NOT_LOGIN_ERROR.getResult());
        }
        Claims body = jwtTokenUtil.getClaimsFromToken(token);
        if (body == null) {
            TaoException.fail(ExceptionMessageEnum.TOKEN_EXPIRE_ERROR.getResult());
        }
        // 定义新的消息头
        User user = jwtTokenUtil.getUserByToken(token);
        request.setAttribute(UserConsts.CURRENT_USER, user);
        return true;
    }
}
