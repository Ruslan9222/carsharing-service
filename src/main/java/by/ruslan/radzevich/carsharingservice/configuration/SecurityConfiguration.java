package by.ruslan.radzevich.carsharingservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private final JWTTokenProvider jwtTokenProvider;

  private static final String ADMIN_ENDPOINT = "/api/admin/**";
  private static final String CREATE_USER_ENDPOINT = "/user";
  private static final String LOGIN_ENDPOINT = "/user/login";
  private static final String CREATE_DIRECTIONAL_ENDPOINT = "/direction";
  private static final String CREATE_CANDIDATE_ENDPOINT = "/candidate/new";

  private static final String[] PUBLIC_URLS = {
      "/v2/api-docs",
      "/swagger-ui/index.html",
      "/swagger-resources/**",
      "configuration/**",
      "webjars/**",
      "/*.html",
      "/**/*.html",
      "/**/*.css",
      "/**/*.js"
  };

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST,
                CREATE_CANDIDATE_ENDPOINT,
                CREATE_USER_ENDPOINT,
                CREATE_DIRECTIONAL_ENDPOINT,
                LOGIN_ENDPOINT).permitAll()
        .antMatchers(ADMIN_ENDPOINT).hasAuthority("ADMIN")
        .antMatchers(HttpMethod.GET, PUBLIC_URLS).permitAll()
        .antMatchers("/db/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .apply(new JWTConfig(jwtTokenProvider));
    http
        .headers().frameOptions().sameOrigin();
  }
}
