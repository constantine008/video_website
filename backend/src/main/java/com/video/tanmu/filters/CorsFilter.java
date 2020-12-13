package com.video.tanmu.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class CorsFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "*");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        logger.info("new request: ip -> {}, path -> {}, query -> {}, body -> {}",
                httpServletRequest.getRemoteAddr(),
                httpServletRequest.getServletPath(),
                httpServletRequest.getQueryString(),
                httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
    }
}