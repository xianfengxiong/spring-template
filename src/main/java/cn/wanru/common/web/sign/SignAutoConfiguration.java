package cn.wanru.common.web.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xxf
 * @since 12/26/16
 */
@Configuration
@ConditionalOnProperty(prefix = SignProperties.PREFIX,value = "field-name")
@ConditionalOnMissingBean(SignCheckFilter.class)
@EnableConfigurationProperties(SignProperties.class)
public class SignAutoConfiguration {

    @Autowired
    SignProperties signProperties;

    @Bean
    public FilterRegistrationBean registerSignCheckFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new SignCheckFilter(signProperties));
        filter.setOrder(0);
        filter.addUrlPatterns("/user/*");
        return filter;
    }

}
