package com.lego_jj.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("lb://auth-jj"))
                .route("usuario-service", r -> r.path("/api/usuarios/**")
                        .uri("lb://usuario-jj"))
                .route("inventario-service", r -> r.path("/api/inventario/**")
                        .uri("lb://inventario-jj"))
                .route("catalogo-service", r -> r.path("/api/catalogo/**")
                        .uri("lb://catalogo-jj"))
                .route("pago-service", r -> r.path("/api/pagos/**")
                        .uri("lb://pago-jj"))
                .route("envio-service", r -> r.path("/api/envios/**")
                        .uri("lb://envio-jj"))
                .route("pieza-service", r -> r.path("/api/piezas/**")
                        .uri("lb://piezas-jj"))
                .build();
    }
}