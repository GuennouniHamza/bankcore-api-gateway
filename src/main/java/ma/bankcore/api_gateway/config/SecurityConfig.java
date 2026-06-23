package ma.bankcore.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
/*le choix de webflux c d'abord le Spring Cloud Gateway est construit sur WebFlux par design.
 *plus Tout ça doit être non bloquant (non-blocking I/O) */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeExchange(auth -> auth
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt -> {})
            );
        return http.build();
    }
}

/*.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt -> {}) :
                
 * Configure l'application comme OAuth2 Resource Server.
Indique que les tokens reçus sont des JWT.
Spring Security vérifiera automatiquement :
la signature,
l'expiration,
l'émetteur (issuer),
l'audience (si configurée).*/
