//package by.ruslan.radzevich.carsharingservice.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@RequiredArgsConstructor
//public class JWTTokenFilter extends OncePerRequestFilter {
//
//    private final JWTTokenProvider jwtTokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//        FilterChain filterChain) throws ServletException, IOException {
//        String token = jwtTokenProvider.resolveToken(request);
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            Authentication auth = jwtTokenProvider.getAuthentication(token);
//
//            if (auth != null) {
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
