package com.exemplo.webappfinancemanager.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exemplo.webappfinancemanager.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
    @Autowired
    TokenService tokenService;
    
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    		throws ServletException, IOException {

    	var token = this.recoverToken(request);

    	if (token == null) {
    		filterChain.doFilter(request, response);
    		return;
    	}

    	var login = tokenService.validateToken(token);
    	if (login != null) {
    		UserDetails user = userRepository.findByUserName(login);
    		var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    		SecurityContextHolder.getContext().setAuthentication(authentication);
    	}

    	filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    	String path = request.getServletPath();
        String method = request.getMethod();

        System.out.println("shouldNotFilter PATH = " + path + " | METHOD = " + method);

        boolean shouldSkip = path.equals("/auth")
            || path.startsWith("/h2-console")
            || (path.equals("/users") && method.equals("POST"));

        System.out.println("shouldSkip? " + shouldSkip);

        return shouldSkip;
    }


    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
