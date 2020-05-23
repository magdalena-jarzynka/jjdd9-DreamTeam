package com.infoshareacademy.dreamteam.servlets;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.http.GenericUrl;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.infoshareacademy.dreamteam.domain.User;
import com.infoshareacademy.dreamteam.oauth.OAuthBuilder;
import com.infoshareacademy.dreamteam.oauth.OAuthManager;
import com.infoshareacademy.dreamteam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/oauth2callback")
public class CallbackServlet extends AbstractAuthorizationCodeCallbackServlet {
    private static final Logger logger = LoggerFactory.getLogger(CallbackServlet.class.getName());


    @EJB
    private UserService userService;

    @EJB
    private OAuthBuilder oAuthBuilder;

    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
            throws ServletException, IOException {

        Oauth2 oauth2 = oAuthBuilder.buildOauth(credential);
        Userinfoplus info = oauth2.userinfo().get().execute();
        String name = info.getName();
        String email = info.getEmail();
        req.getSession().setAttribute("name", name);
        req.getSession().setAttribute("email", email);

        User foundUser = userService.findByEmail(email);
        if (foundUser != null) {
            req.getSession().setAttribute("role", foundUser.getRole());
        } else {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setRole("USER");
            req.getSession().setAttribute("role", "USER");
            userService.save(user);
            logger.info("New user has been successfully created. Name = {} Email = {}", name, email);
        }
        resp.sendRedirect("/");
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
        return OAuthManager.buildFlow();
    }

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath("/oauth2callback");
        return url.build();
    }

    @Override
    protected String getUserId(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        return UUID.randomUUID().toString();
    }

}
