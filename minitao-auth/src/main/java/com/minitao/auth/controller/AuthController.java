package com.minitao.auth.controller;

import com.minitao.auth.domain.Oauth2TokenDto;
import com.minitao.common.response.CommonResult;
import com.sun.org.apache.xml.internal.security.algorithms.Algorithm;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Map;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-08-22 21:08
 **/
@RestController
public class AuthController {
    @Autowired
    private KeyPair keyPair;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/test")
    public CommonResult test()  {
        String token = request.getHeader("Authorization");
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println(privateKey == publicKey);
        System.out.println(privateKey.getEncoded().equals(publicKey.getEncoded()));
        Jwt res1 = Jwts.parser().setSigningKey(publicKey).parse(token);
        Jwt res2 = Jwts.parser().setSigningKey(privateKey).parse(token);
        System.out.println(res1);
        System.out.println("999999999999");
        System.out.println(res2);


        return CommonResult.success("");
    }




    @Autowired
    private TokenEndpoint tokenEndpoint;

    @ApiOperation("Oauth2获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", value = "登录用户名"),
            @ApiImplicitParam(name = "password", value = "登录密码")
    })
    @RequestMapping(value = "oauth/token", method = RequestMethod.POST)
    public CommonResult<Oauth2TokenDto> postAccessToken(@ApiIgnore Principal principal, @ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .build();

        return CommonResult.success(oauth2TokenDto);
    }

}
