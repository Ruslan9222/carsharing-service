//package by.ruslan.radzevich.carsharingservice.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    private final JWTTokenProvider jwtTokenProvider;
//
//    private static final String ADMIN_ENDPOINT = "/api/admin/**";
//    private static final String CREATE_USER_ENDPOINT = "/user";
//    private static final String CREATE_CARS_ENDPOINT = "/cars";
//    private static final String CREATE_CAR_PRICE_ENDPOINT = "/cars/price";
//    private static final String CREATE_RENTAL_ENDPOINT = "/rentals";
//    private static final String DELETE_CARD_ENDPOINT = "/card/{id}";
//    private static final String CREATE_CARD_ENDPOINT = "/card";
//    private static final String UPDATE_CAR_PHOTO_ENDPOINT = "/cars/{id}/carPhoto";
//    private static final String CREATE_RENTAL_PHOTO_ENDPOINT = "/rentals/{id}/carPhoto";
//    private static final String UPDATE_DRIVER_LICENSE_ENDPOINT = "/user/{id}/driversLicense";
//    private static final String UPDATE_USER_EMAIL_ENDPOINT = "/user/{id}";
//    private static final String LOGIN_ENDPOINT = "/user/login";
//
//    private static final String[] PUBLIC_URLS = {
//            "/**",
//            "/v2/api-docs",
//            "/store/**",
//            "/product/**",
//            "/category/**",
//            "/order/**",
//            "/swagger-ui/index.html",
//            "/swagger-resources/**",
//            "configuration/**",
//            "webjars/**",
//            "/*.html",
//            "/**/*.html",
//            "/**/*.css",
//            "/**/*.js",
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
//            "/swagger-ui.html"
//    };
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST,
//                        CREATE_USER_ENDPOINT,
//                        LOGIN_ENDPOINT,
//                        CREATE_CARS_ENDPOINT,
//                        CREATE_RENTAL_ENDPOINT,
//                        UPDATE_CAR_PHOTO_ENDPOINT,
//                        CREATE_RENTAL_PHOTO_ENDPOINT,
//                        UPDATE_DRIVER_LICENSE_ENDPOINT,
//                        UPDATE_USER_EMAIL_ENDPOINT,
//                        CREATE_CAR_PRICE_ENDPOINT,
//                        CREATE_CARD_ENDPOINT,
//                        DELETE_CARD_ENDPOINT
//                ).permitAll()
//                .antMatchers(ADMIN_ENDPOINT).hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.GET, PUBLIC_URLS).permitAll()
//                .antMatchers("/db/**").permitAll()
//                .antMatchers("/user/**").permitAll()
//                .antMatchers("/cars/**").permitAll()
//                .antMatchers("/rentals/**").permitAll()
//                .antMatchers("/card/**").permitAll()
//                .antMatchers("/v3/api-docs/**").permitAll()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers("/swagger-ui.html").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .apply(new JWTConfig(jwtTokenProvider));
//        http
//                .headers().frameOptions().sameOrigin();
//    }
//}
