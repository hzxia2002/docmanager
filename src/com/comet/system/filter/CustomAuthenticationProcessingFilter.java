package com.comet.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AbstractProcessingFilter;

/**
 * Created by IntelliJ IDEA.
 * User: tcg
 * Date: 2011-12-26
 * Time: 15:02:28
 * To change this template use File | Settings | File Templates.
 */
public class CustomAuthenticationProcessingFilter implements Filter {
    public static String VALIDATION_CODE = "VALIDATION_CODE";
    public static String J_VALIDATION_CODE = "j_validation_code";
    public void destroy() {

    }

//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        if ((request != null) && request instanceof HttpServletRequest) {
//            HttpServletRequest req = (HttpServletRequest) request;
//            Enumeration parameterNames = req.getParameterNames();
//            boolean flag = false;
//            while(parameterNames.hasMoreElements()){
//                String name = parameterNames.nextElement().toString();
//                if(name.equals(J_VALIDATION_CODE)){
//                    flag = true;
//                }
//            }
//            String code = req.getParameter(J_VALIDATION_CODE);
//            if(flag){
//                code = StringUtils.defaultIfEmpty(code, "");
//                HttpSession session = req.getSession();
//                if (session.getAttribute(VALIDATION_CODE) != null) {
//                    ImageCaptcha imageCaptcha = (ImageCaptcha) session.getAttribute(VALIDATION_CODE);
//                    if (!(imageCaptcha.validateResponse(code.toUpperCase())).booleanValue()) {
//                        session.setAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY,
//                                new BadCredentialsException("验证码错误!"));
//                        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp?login_error=1");
//                        req.setAttribute("SPRING_SECURITY_LAST_USERNAME", req.getParameter("j_username"));
//                        rd.forward(request, response);
//                        session.removeAttribute(VALIDATION_CODE);
//                    }
//                }
//            }
//        }
//        chain.doFilter(request, response);
//    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if ((request != null) && request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;

            String code = StringUtils.defaultIfEmpty(req
                    .getParameter("j_validation_code"), "");

            HttpSession session = req.getSession();

            if (session.getAttribute(VALIDATION_CODE) != null) {
                String sessionCode = StringUtils.defaultIfEmpty(
                        (String) session
                                .getAttribute(VALIDATION_CODE), "");

                if (!code.equals("") && !sessionCode.equals("")
                        && !code.equalsIgnoreCase(sessionCode)) {
                    session
                            .setAttribute(
                                    AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY,
                                    new BadCredentialsException("验证码错误!"));
                    RequestDispatcher rd = request
                            .getRequestDispatcher("/login.jsp?login_error=1");
                    req.setAttribute("SPRING_SECURITY_LAST_USERNAME", req
                            .getParameter("j_username"));
                    rd.forward(request, response);
                }

                // session.removeAttribute(Constants.VALIDATION_CODE);
            }
        }
        chain.doFilter(request, response);
    }


    public void init(FilterConfig arg0) throws ServletException {

    }

}
