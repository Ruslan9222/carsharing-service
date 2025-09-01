package by.ruslan.radzevich.carsharingservice.configuration;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Фильтр безопасности, выполняющий проверку JWT-токена для каждого входящего HTTP-запроса.
 * <p>
 * Расширяет {@link OncePerRequestFilter}, гарантируя, что фильтр будет вызван только один раз за
 * запрос, даже при наличии внутренних форвардов.
 * </p>
 *
 * <p>
 * Основная задача — извлечь JWT-токен из запроса, проверить его валидность и, при успешной
 * аутентификации, установить {@link Authentication} в {@link SecurityContextHolder}. Это позволяет
 * Spring Security распознавать пользователя на протяжении всего запроса.
 * </p>
 *
 * <p>
 * Используется совместно с {@link JWTTokenProvider}, который инкапсулирует логику извлечения,
 * валидации и преобразования токена в объект аутентификации.
 * </p>
 *
 * @author ruslan
 */

@NonNullApi
@RequiredArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTTokenProvider jwtTokenProvider;

    /**
     * Основной метод фильтрации запроса.
     * <p>
     * Извлекает JWT-токен из запроса, проверяет его валидность и, если токен корректен,
     * устанавливает объект {@link Authentication} в {@link SecurityContextHolder}.
     * </p>
     *
     * @param request     текущий HTTP-запрос
     * @param response    текущий HTTP-ответ
     * @param filterChain цепочка фильтров, по которой продолжается обработка запроса
     * @throws ServletException в случае ошибки сервлета
     * @throws IOException      в случае ошибки ввода/вывода
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
