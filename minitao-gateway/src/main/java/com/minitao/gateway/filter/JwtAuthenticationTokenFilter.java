package com.minitao.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minitao.common.response.CommonResult;
import com.minitao.gateway.config.FilterProperties;
import com.minitao.gateway.entity.User;
import com.minitao.gateway.service.UserService;
import com.minitao.gateway.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-07-24 23:16
 **/
@Component
@EnableConfigurationProperties(FilterProperties.class)
public class JwtAuthenticationTokenFilter implements GlobalFilter, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private FilterProperties filterProperties;

    @Value("${jwt.tokenHead}")
    private String tokenHead;



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("过滤");
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getURI().getPath();

        List<String> allowPath = filterProperties.getAllowPath();
        for (String path : allowPath) {
            if(requestPath.contains(path)){
                return chain.filter(exchange);
            }
        }
        String jwtToken = request.getHeaders().getFirst(tokenHead);
        if (!StringUtils.isBlank(jwtToken)) {
            String username = jwtTokenUtil.getUserNameFromToken(jwtToken);
//            redisTemplate.opsForValue().set(tokenHead,username);
            LOGGER.info("checking username:{}", username);
            User user = userService.loadUserByUsername(username);
            if (user != null) {
                if (jwtTokenUtil.validateToken(jwtToken, user)) {
                    return chain.filter(exchange);
                }
            }
        }
        ServerHttpResponse response = exchange.getResponse();
        return out(response);

    }

    @Override
    public int getOrder() {
        return 1;
    }

    private Mono<Void> out(ServerHttpResponse response) {
        CommonResult<Object> commonResult = CommonResult.failed("鉴权失败");
        commonResult.setCode(302);

        String jsonString = JSONObject.toJSONString(commonResult);
        byte[] bits = jsonString.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        return response.writeWith(Mono.just(buffer));
    }
}
