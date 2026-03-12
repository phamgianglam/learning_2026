package com.store.api.config;

import com.store.api.util.Helper;
import com.store.api.model.UserToken;
import com.store.api.repository.UserTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private Helper helper;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, ServletException {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        Long userId = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userId = Long.valueOf(helper.getUserIdFromToken(jwt));

            UserToken token = userTokenRepository.findByTokenAndUser_Id(jwt, userId)
                    .orElseThrow(() -> new RuntimeException("Invalid token"));;

            if (!token.isValid()) {
                throw new RuntimeException("Token is revoked or invalid");
            }

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userId, null, null);

            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);
        }

    }
}
