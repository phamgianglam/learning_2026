package com.store.api.config;

import Util.Helper;
import com.store.api.model.UserToken;
import com.store.api.repository.UserTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

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

            Optional<UserToken> token = userTokenRepository.findByTokenAndUserId(jwt, userId);

//            if (token.isRevoked() || !token.isValid()) {
//                // return 403
//            }

            filterChain.doFilter(request, response);
        }


    }
}
