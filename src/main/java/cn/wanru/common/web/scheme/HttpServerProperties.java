package cn.wanru.common.web.scheme;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xxf
 * @since 12/27/16
 */
@ConfigurationProperties(prefix = "http.server")
public class HttpServerProperties {

    private int port;

    private int redirectPort;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getRedirectPort() {
        return redirectPort;
    }

    public void setRedirectPort(int redirectPort) {
        this.redirectPort = redirectPort;
    }
}
