package by.ruslan.radzevich.carsharingservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Конфигурационный класс для интеграции JWT-фильтра в цепочку Spring Security.
 * <p>
 * Расширяет {@link SecurityConfigurerAdapter}, позволяя внедрить {@link JWTTokenFilter} перед
 * стандартным {@link UsernamePasswordAuthenticationFilter}. Это обеспечивает предварительную
 * обработку JWT-токенов до попытки аутентификации по логину/паролю.
 * </p>
 *
 * <p>
 * Используется в {@link SecurityConfigurerAdapter} или в {@link SecurityFilterChain} для настройки
 * безопасности.
 * </p>
 *
 * @author ruslan
 */
@RequiredArgsConstructor
public class JWTConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JWTTokenProvider jwtTokenProvider;

    /**
     * Добавляет {@link JWTTokenFilter} в цепочку фильтров Spring Security.
     * <p>
     * Фильтр вставляется перед {@link UsernamePasswordAuthenticationFilter}, чтобы перехватывать и
     * обрабатывать JWT-токены до стандартной аутентификации.
     * </p>
     *
     * @param httpSecurity объект конфигурации безопасности, предоставляемый Spring Security
     */
    @Override
    public void configure(HttpSecurity httpSecurity) {
        JWTTokenFilter jwtTokenFilter = new JWTTokenFilter(jwtTokenProvider);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
