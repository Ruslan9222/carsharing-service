package by.ruslan.radzevich.carsharingservice.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Провайдер JWT-токенов, реализующий логику генерации, валидации и извлечения информации из
 * токена.
 * <p>
 * Используется в цепочке Spring Security для обеспечения stateless-аутентификации. Хранит секретный
 * ключ, извлекает токен из HTTP-запроса, проверяет его валидность и преобразует в
 * {@link Authentication}, пригодный для установки в
 * {@link org.springframework.security.core.context.SecurityContext}.
 * </p>
 *
 * <p>
 * Конфигурация параметров токена (секрет и срок действия) задаётся через
 * application.yml/properties:
 * <ul>
 *     <li><code>jwt.token.secret</code> — секретный ключ для подписи</li>
 *     <li><code>jwt.token.expired</code> — срок действия токена в миллисекундах</li>
 * </ul>
 * </p>
 *
 * @author ruslan
 */

@Component
public class JWTTokenProvider {

    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expired}")
    private long jwtExpirationInMs;

    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;

    private SecretKey secretKey;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Инициализация секретного ключа после внедрения зависимостей. Преобразует строку секрета в
     * {@link SecretKey} с использованием HMAC SHA.
     */

    @PostConstruct
    protected void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Генерирует JWT-токен для заданного пользователя и его ролей.
     *
     * @param username имя пользователя
     * @param roles    коллекция ролей пользователя
     * @return подписанный JWT-токен
     */

    public String generateToken(String username, Collection<? extends GrantedAuthority> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", getUserRoleNamesFromJWT(roles));

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    /**
     * Восстанавливает {@link Authentication} из токена. Загружает {@link UserDetails} по имени
     * пользователя, извлечённому из токена.
     *
     * @param token JWT-токен
     * @return объект аутентификации
     */

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(
            getUserUsernameFromJWT(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "",
            userDetails.getAuthorities());
    }

    public String getUserUsernameFromJWT(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    /**
     * Извлекает JWT-токен из заголовка Authorization HTTP-запроса.
     *
     * @param req HTTP-запрос
     * @return JWT-токен или null, если отсутствует/некорректен
     */

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Проверяет валидность токена: корректность подписи и срок действия.
     *
     * @param token JWT-токен
     * @return true, если токен действителен; false — если просрочен или некорректен
     */

    @SneakyThrows
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Преобразует коллекцию ролей в список строковых представлений. Используется для сериализации
     * ролей в JWT.
     *
     * @param roles коллекция {@link GrantedAuthority}
     * @return список названий ролей
     */

    private List<String> getUserRoleNamesFromJWT(Collection<? extends GrantedAuthority> roles) {
        List<String> result = new ArrayList<>();
        roles.forEach(role -> result.add(role.getAuthority()));
        return result;
    }
}
