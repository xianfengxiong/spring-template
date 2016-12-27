package cn.wanru.common.web.decrypt;

import cn.wanru.common.web.RsaException;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * @author xxf
 * @since 12/26/16
 */
class DecryptUtils {
    private static final String ALGORITHM = "RSA";
    private static final String CHARSET = "UTF-8";

    private DecryptProperties properties;

    private PrivateKey privateKey;

    DecryptUtils(DecryptProperties properties) {
        this.properties = properties;
        init();
    }

    public DecryptProperties getProperties() {
        return properties;
    }

    private void init(){
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            byte[] bytes = properties.getPrivateKey().getBytes(CHARSET);
            byte[] decodedKey = Base64.getDecoder().decode(bytes);
            KeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
            privateKey = keyFactory.generatePrivate(keySpec);
        }catch (Exception e){
            throw new RsaException("Init decrypt private key failed",e);
        }
    }

    public String decrypt(String str){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] bytes = str.getBytes(CHARSET);
            byte[] decodeBytes = Base64.getDecoder().decode(bytes);
            byte[] finalBytes = cipher.doFinal(decodeBytes);
            return new String(finalBytes);
        }catch (Exception e){
            throw new RsaException(e);
        }
    }
}
