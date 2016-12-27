package cn.wanru.common.web.decrypt;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 对请求中加密的字段进行解密
 *
 * @author xxf
 * @since 12/26/16
 */
public class DecryptFilter implements Filter {

    private DecryptUtils decryptUtils;

    public DecryptFilter(DecryptProperties decryptProperties) {
        decryptUtils = new DecryptUtils(decryptProperties);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        DecryptRequestWrapper decryptRequest = new DecryptRequestWrapper((HttpServletRequest) request,decryptUtils);
        chain.doFilter(decryptRequest,response);
    }

    @Override
    public void destroy() {

    }
}
