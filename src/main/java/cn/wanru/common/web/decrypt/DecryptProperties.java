package cn.wanru.common.web.decrypt;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xxf
 * @since 12/26/16
 */
@ConfigurationProperties(prefix = DecryptProperties.PREFIX)
public class DecryptProperties {

    public static final String PREFIX = "app.decrypt";

    /**
     * 需要解密的字段
     */
    private String[] field;

    /**
     * RSA 解密的私钥
     */
    private String privateKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }
}
