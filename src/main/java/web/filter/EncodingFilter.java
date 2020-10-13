package web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
//
//public class EncodingFilter implements Filter {
//
//
//
//    private String encoding;
//
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//
//        String requestEncoding = httpRequest.getCharacterEncoding();
//        if (requestEncoding == null) {
//            request.setCharacterEncoding(encoding);
//        }
//
//    }
//
//    public void init(FilterConfig fConfig) throws ServletException {
//        encoding = fConfig.getInitParameter("encoding");
//    }
//}