package by.ruslan.radzevich.carsharingservice.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST,
                    "/user",
                    "/user/login",
                    "/cars",
                    "/cars/price",
                    "/rentals",
                    "/card",
                    "/card/{id}",
                    "/cars/{id}/carPhoto",
                    "/rentals/{id}/carPhoto",
                    "/user/{id}/driversLicense",
                    "/user/{id}"
                ).permitAll()
                .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, PUBLIC_URLS).permitAll()
                .requestMatchers("/db/**", "/user/**", "/cars/**", "/rentals/**", "/card/**").permitAll()
                .anyRequest().authenticated()
            );
        http
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            );


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private static final String[] PUBLIC_URLS = {
        "/**",
        "/v2/api-docs",
        "/store/**",
        "/product/**",
        "/category/**",
        "/order/**",
        "/swagger-ui/index.html",
        "/swagger-resources/**",
        "configuration/**",
        "webjars/**",
        "/*.html",
        "/**/*.html",
        "/**/*.css",
        "/**/*.js",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html"
    };
}

