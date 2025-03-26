//package com.sanyam.curewell.curewell_backend.config;
//
//import com.sanyam.curewell.curewell_backend.security.JwtAuthenticationEntryPoint;
//import com.sanyam.curewell.curewell_backend.security.JwtAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class SecurityConfig {
//    @Autowired
//    private JwtAuthenticationEntryPoint authenticationEntryPoint;
//    @Autowired
//    private JwtAuthenticationFilter authenticationFilter;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests(
////                        authorizeRequests -> authorizeRequests
////                                .requestMatchers("/api/auth/**").permitAll()
////                                .anyRequest()
////                                .authenticated()
////                ).httpBasic(Customizer.withDefaults());
////        return http.build();
////    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests(
//                        authorizeRequests -> authorizeRequests
//                                .requestMatchers("/api/auth/**").permitAll()
//                                .anyRequest()
//                                .authenticated()
//                ).exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
//                .sessionManagement(session -> session.sessionCreationPolicy(
//                        SessionCreationPolicy.STATELESS
//                ))
//                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration
//    ) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
//

package com.sanyam.curewell.curewell_backend.config;

import com.sanyam.curewell.curewell_backend.security.JwtAuthenticationEntryPoint;
import com.sanyam.curewell.curewell_backend.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Private endpoints
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}