package com.skyline.webutil.config;

import com.skyline.webutil.WebUtil;
import com.skyline.webutil.weblog.WebLogInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [FEATURE INFO]<br/>
 * web 工具自动配置类
 *
 * @author Skyline
 * @create 2022-10-27 20:30
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass(WebUtil.class)
@EnableConfigurationProperties(WebUtilProperties.class)
public class WebUtilAutoConfig {

    private WebUtilProperties webUtilProperties;

    @Autowired
    public void setWebUtilProperties(WebUtilProperties webUtilProperties) {
        this.webUtilProperties = webUtilProperties;
    }

    @Bean
    public AspectJExpressionPointcutAdvisor configAdvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(webUtilProperties.getLogPointcut());
        advisor.setAdvice(new WebLogInterceptor());
        return advisor;
    }

    @Bean("WebUtil")
    public WebUtil webUtil() {
        return new WebUtil();
    }
}
