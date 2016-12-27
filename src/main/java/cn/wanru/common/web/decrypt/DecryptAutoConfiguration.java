package cn.wanru.common.web.decrypt;

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
@ConditionalOnProperty(prefix = DecryptProperties.PREFIX,value = "private-key")
@ConditionalOnMissingBean(DecryptFilter.class)
@EnableConfigurationProperties(DecryptProperties.class)
public class DecryptAutoConfiguration {

    @Autowired
    DecryptProperties decryptProperties;

    @Bean
    public FilterRegistrationBean registerDecryptFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new DecryptFilter(decryptProperties));
        filter.setOrder(1);
        filter.addUrlPatterns("/user/*");
        return filter;
    }
}
