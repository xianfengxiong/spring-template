package cn.wanru.common.web.sign;

import cn.wanru.common.web.RsaException;

import javax.servlet.ServletRequest;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author xxf
 * @since 12/26/16
 */
final class SignUtils {
    private static final String ALGORITHM = "RSA";
    private static final String CHARSET = "UTF-8";

    private SignProperties properties;

    private PublicKey publicKey;

    SignUtils(SignProperties properties) {
        this.properties = properties;
        init();
    }

    private void init(){
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            byte[] bytes = Base64.getDecoder().decode(properties.getPublicKey());
            KeySpec publicKeySpec = new X509EncodedKeySpec(bytes);
            publicKey = keyFactory.generatePublic(publicKeySpec);
        }catch (Exception e){
            throw new RsaException("Init sign public key failed",e);
        }
    }

    public boolean checkSign(ServletRequest request) {
        Map<String,String[]> parameterMap = request.getParameterMap();
        if (parameterMap.isEmpty()){
            return true;
        }

        if (!parameterMap.containsKey(properties.getFieldName())){
            return false;
        }

        // if has no parameter ,but only has sign
        if (parameterMap.size() == 1) {
            return true;
        }

        String sign = parameterMap.get(properties.getFieldName())[0];
        String source = sortAndJoinParameter(parameterMap);
        return checkSign(source,sign);
    }

    private boolean checkSign(String source,String sign){
        try {
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(source.getBytes(CHARSET));
            byte[] decodeSign = Base64.getDecoder().decode(sign);
            return signature.verify(decodeSign);
        }catch (Exception e){
            throw new RsaException("Check sign failed",e);
        }
    }

    private String sortAndJoinParameter(Map<String,String[]> map){
        StringBuilder source = new StringBuilder();
        Set<String> set = new TreeSet<>(map.keySet());
        for (String name : set) {
            if (name.equals(properties.getFieldName())) {
                continue;
            }
            String[] value = map.get(name);
            source.append(name).append("=")
                    .append(String.join(",", value))
                    .append("&");
        }
        source.deleteCharAt(source.length()-1);
        return source.toString();
    }
}
