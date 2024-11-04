package com.pocket.outbound.config;

import com.pocket.core.exception.jwt.JwtAccessDeniedHandler;
import com.pocket.core.exception.jwt.JwtAuthenticationEntryPoint;
import com.pocket.outbound.adapter.authentication.OAuthLoginFailureHandler;
import com.pocket.outbound.adapter.authentication.OAuthLoginSuccessHandler;
import com.pocket.outbound.adapter.oauth.KakaoLoginAdapter;
import com.pocket.outbound.util.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final KakaoLoginAdapter kakaoLoginAdapter;
    private final OAuthLoginSuccessHandler oAuth2SuccessHandler;
    private final OAuthLoginFailureHandler oAuth2FailureHandler;

    private final JwtFilter jwtFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))

                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authz -> {
                    // 접근 허용
                    authz.requestMatchers("/swagger-ui/**",
                            "/swagger-resources/**",
                            "/v3/api-docs/**",
                            "/api/v1/photobooth/**",
                            "/api/v1/review/**",
                            "/api/v1/album/**",
                            "/v1/public/**",
                            "/page").permitAll();

                    // 로그인 필요
                    authz.requestMatchers("/v1/user/**",
                            "/api/v1/album",
                            "/api/v1/review").authenticated();

                    // 그 외의 모든 요청은 인증 필요
                    authz.anyRequest().authenticated();
                })

                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(kakaoLoginAdapter))
                        .successHandler(oAuth2SuccessHandler)
                        .failureHandler(oAuth2FailureHandler))

                .addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class)


                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/error", "/favicon.ico");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "POST", "GET", "DELETE", "PUT"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

