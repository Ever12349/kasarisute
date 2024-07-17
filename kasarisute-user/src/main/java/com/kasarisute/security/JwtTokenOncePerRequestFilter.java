package com.kasarisute.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.kasarisute.utils.JwtUtils;

import ch.qos.logback.core.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, JwtException {
        try {

            String jwt = request.getHeader(jwtUtils.getHeader());

            if (StringUtil.isNullOrEmpty(jwt)) {
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtUtils.getClaimsByAuthorizationToken(jwt);

            if (claims == null) {
                throw new JwtException("token 不合法");
            }

            if (jwtUtils.tokenIsExpired(claims)) {
                throw new JwtException("token 已经过期");
            }

            String userName = claims.get("userName").toString();
            Long uid = Long.parseLong(claims.get("uid").toString());
            Long userCode = Long.parseLong(claims.get("userCode").toString());
            String roles = (String) claims.get("roles");

            request.setAttribute("uid", uid);
            request.setAttribute("userName", userName);
            request.setAttribute("userCode", userCode);
            request.setAttribute("roles", roles);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>() {
                {
                    String[] rolesList = roles.split(",");
                    for (String roleItem : rolesList) {

                        add(new SimpleGrantedAuthority(roleItem));
                    }
                }
            };

            SecurityUser securityUser = new SecurityUser();
            securityUser.setUid(uid);
            securityUser.setUserCode(userCode);
            securityUser.setRoles(roles);
            securityUser.setUserName(userName);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser, null,
                    authorities);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(token);

            SecurityContextHolder.setContext(context);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

    }

}
