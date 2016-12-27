package cn.wanru.common.web.sign;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 检查请求的合法性
 *
 * @author xxf
 * @since 12/26/16
 */
public class SignCheckFilter implements Filter {

    private SignUtils signUtils;

    public SignCheckFilter(SignProperties signProperties) {
        signUtils = new SignUtils(signProperties);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (signUtils.checkSign(request)) {
            chain.doFilter(request, response);
        }else{
            doSignError(request,response);
        }
    }

    @Override
    public void destroy() {

    }

    private void doSignError(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.getWriter().print("签名不通过");
    }
}
