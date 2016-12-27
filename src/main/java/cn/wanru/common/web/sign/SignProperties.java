package cn.wanru.common.web.sign;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xxf
 * @since 12/26/16
 */
@ConfigurationProperties(prefix = SignProperties.PREFIX)
public class SignProperties {

    public static final String PREFIX = "app.sign";

    /**
     * url 中签名的字段名
     */
    private String fieldName;

    /**
     * RSA 验证签名的公钥
     */
    private String publicKey;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
