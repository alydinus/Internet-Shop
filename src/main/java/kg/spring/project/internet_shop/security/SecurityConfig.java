package kg.spring.project.internet_shop.security;

import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final UserService userService;
  private final JwtFilter jwtFilter;
  private final PasswordEncoder passwordEncoder;

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    authenticationProvider.setUserDetailsService(userService);
    return authenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtEntryPoint jwtEntryPoint)
      throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
        .formLogin(Customizer.withDefaults())
        .oauth2Login(Customizer.withDefaults())

//        .exceptionHandling(exception ->
//            exception.authenticationEntryPoint(jwtEntryPoint))
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/cart/**").hasAnyRole("USER")
            .requestMatchers("/api/orders", "/api/orders/user/*").hasRole("ADMIN")
            .requestMatchers("/api/orders/user/*", "/api/orders/create", "/api/orders/*",
                "/api/orders/*").hasRole("USER")
            .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/*").permitAll()
            .requestMatchers("/api/products/create", "/api/products/update/*",
                "/api/products/*/price", "/api/products/*").hasRole("ADMIN")
            .requestMatchers("/api/admin/users/**").hasRole("ADMIN")
            .requestMatchers("/api/users/me").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/h2-console/**").permitAll()
            .anyRequest().permitAll()
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}
