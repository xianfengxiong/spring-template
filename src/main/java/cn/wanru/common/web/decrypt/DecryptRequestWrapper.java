package cn.wanru.common.web.decrypt;

import cn.wanru.common.web.RsaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xxf
 * @since 12/26/16
 */
class DecryptRequestWrapper extends HttpServletRequestWrapper {

    private Set<String> encryptFields;

    private DecryptUtils decryptUtils;

    private Map<String,String[]> decryptParameterMap;

    DecryptRequestWrapper(HttpServletRequest request, DecryptUtils decryptUtils) {
        super(request);
        this.decryptUtils = decryptUtils;
        String[] fields = decryptUtils.getProperties().getField();
        encryptFields = new HashSet<>(Arrays.asList(fields));
        decryptMap(request.getParameterMap());
    }

    private void decryptMap(Map<String,String[]> parameterMap){
        Map<String,String[]> tmp = new HashMap<>(parameterMap);
        for (String field : parameterMap.keySet()){
            if (contains(field)){
                try {
                    String[] decrypt = decryptArray(parameterMap.get(field));
                    tmp.put(field, decrypt);
                }catch (RsaException e){
                    throw new RsaException("Decrypt parameter " + field + " failed",e);
                }
            }
        }

        decryptParameterMap = Collections.unmodifiableMap(tmp);
    }

    private String[] decryptArray(String[] arr){
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = decryptUtils.decrypt(arr[i]);
        }
        return result;
    }

    private boolean contains(String name){
        return encryptFields.contains(name);
    }

    @Override
    public String getParameter(String name) {
        String[] val = decryptParameterMap.get(name);
        if (val == null || val.length <= 0)  {
            return null;
        }else{
            return val[0];
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return decryptParameterMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        return decryptParameterMap.get(name);
    }


}
