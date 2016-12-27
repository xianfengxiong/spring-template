package cn.wanru.common.web.scheme;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Http Connector 配置
 * 因为Spring Boot 不支持同时配置 Http 和 Https
 * 所以必须要编程实现某一个 Connector
 * 在 application.properties 文件中配置 Https
 * 通过自定义配置 Http 是比较简单的做法
 *
 * @author xxf
 * @since 12/27/16
 */
@Configuration
@EnableConfigurationProperties(HttpServerProperties.class)
@ConditionalOnProperty(prefix = "http.server",value = "port")
@ConditionalOnWebApplication
public class HttpConnectorAutoConfiguration {

    @Autowired
    private HttpServerProperties httpServerProperties;

    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory(){
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createHttpConnector());
        return tomcat;
    }

    private Connector createHttpConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(httpServerProperties.getPort());
        connector.setSecure(false);
        connector.setRedirectPort(httpServerProperties.getRedirectPort());
        return connector;
    }
}
