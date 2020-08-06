package com.minitao.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-08-04 15:12
 **/
@ConfigurationProperties(prefix = "minitao.filter")
public class FilterProperties {
    private List<String> allowPath;

    public List<String> getAllowPath() {
        return allowPath;
    }

    public void setAllowPath(List<String> allowPath) {
        this.allowPath = allowPath;
    }
}
