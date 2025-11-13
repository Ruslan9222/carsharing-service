package by.ruslan.radzevich.carsharingservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Основной класс конфигурации безопасности для carsharing-сервиса.
 * <p>
 * Настраивает цепочку фильтров Spring Security, включая интеграцию JWT-аутентификации через
 * {@link JWTConfig}, отключение CSRF и установку политики session-less.
 * </p>
 *
 * @author ruslan
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(
                session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .with(new JWTConfig(jwtTokenProvider), jwt -> {
            });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
        throws Exception {
        return config.getAuthenticationManager();
    }
}
