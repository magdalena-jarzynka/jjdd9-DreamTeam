package com.infoshareacademy.dreamteam.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "UserAccessFilter",
        urlPatterns = {"/favourites/*", "/reservations/*"}
)

public class UserAccessFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession(false);

        boolean isLoggedIn = session != null && String.valueOf(session.getAttribute("role"))
                .matches("USER|ADMIN|SUPER_ADMIN");

        servletRequest.setAttribute("isLoggedIn", isLoggedIn);


        filterChain.doFilter(servletRequest, servletResponse);
    }

}
