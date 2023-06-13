package com.gateway.j2hbgateway.filter;

import com.gateway.j2hbgateway.util.JwtService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ManagerFilter extends AbstractGatewayFilterFactory<ManagerFilter.Config> {

    @Autowired
    private RouteValidator validator;
    @Autowired
    private JwtService jwtUtil;

    public ManagerFilter() {
        super(Config.class);
    }

    @NotNull
    public static GatewayFilter getGatewayFilter(RouteValidator validator, JwtService jwtUtil, String role) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                if (jwtUtil.extractRole(authHeader).equals(role) || jwtUtil.extractRole(authHeader).equals("ADMIN")) {
                    try {
                        jwtUtil.validateToken(authHeader);
                    } catch (Exception e) {
                        throw new RuntimeException("un authorized access to application");
                    }
                } else {
                    throw new RuntimeException("Access denied...!");
                }

            }
            return chain.filter(exchange);
        });
    }

    @Override
    public GatewayFilter apply(Config config) {
        return getGatewayFilter(validator, jwtUtil, "MANAGER");
    }

    public static class Config {
    }
}
