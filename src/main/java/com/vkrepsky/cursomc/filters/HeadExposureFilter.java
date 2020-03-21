package com.vkrepsky.cursomc.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HeadExposureFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.addHeader("access-control-expose-headers", "Authorization");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
