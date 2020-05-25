package com.hellocat.springBootAJAX.security;

import com.hellocat.springBootAJAX.domen.Role;
import com.hellocat.springBootAJAX.domen.RoleType;
import com.hellocat.springBootAJAX.domen.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        HttpSession session = request.getSession();
        User user = (User) authentication.getPrincipal();

        session.setAttribute("authorities", authentication.getAuthorities());
        session.setAttribute("user", user);

        response.setStatus(HttpServletResponse.SC_OK);

        if (user.getRolesSet().contains(new Role(RoleType.ROLE_ADMIN))) {
            response.sendRedirect("/admin");
        } else if (user.getRolesSet().contains(new Role(RoleType.ROLE_USER))) {
            response.sendRedirect("/user");
        }
    }
}