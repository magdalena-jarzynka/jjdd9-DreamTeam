package com.infoshareacademy.dreamteam.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "AdminAccessFilter",
        urlPatterns = {"/stats/*", "/manage/*", "/users/*"}
)

public class AdminAccessFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession(false);

        boolean isAdmin = session != null && String.valueOf(session.getAttribute("role"))
                .matches("ADMIN|SUPER_ADMIN");

        servletRequest.setAttribute("isAdmin", isAdmin);

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
