package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
        
                .authorizeHttpRequests(requests -> {
               	 requests.requestMatchers(new AntPathRequestMatcher("/login","/register")).permitAll();
                  })
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}

//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableMethodSecurity
//public class SecurityConfig{
//	 
//	@Autowired
//	private UserAuthenticationEntryPoint userAuthenticationEntryPoint;
//	@Autowired
//    private UserAuthenticationProvider userAuthenticationProvider;
//    @Autowired
//    private WebConfig webConfig;
//    @Autowired(required = false)
//    private JwtAuthFilter jwtAuthFilter;
//    @Autowired
//    private CorsFilter corsFilter;
//    
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http.
////                authorizeHttpRequests(requests -> {
////                        requests.requestMatchers(new AntPathRequestMatcher("/")).permitAll();
////                        requests.requestMatchers(new AntPathRequestMatcher("/secure/**")).hasAuthority("MyAuthority");
////                }).build();
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//   
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("*");
//            }
//        };
//    }
//    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	 http
//         .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
//         .and()
//         .addFilterBefore(new JwtAuthFilter(), BasicAuthenticationFilter.class)
//         .csrf().disable()
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         .and()
//         .addFilterBefore(webConfig.corsFilter(), UsernamePasswordAuthenticationFilter.class) // Use corsFilter() method
//         .authorizeHttpRequests((requests -> {
//        	 requests.requestMatchers(new AntPathRequestMatcher("/login**")).permitAll();
//         }));
//
//        return http.build();	
//    }
//    
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        // @formatter:off
////        return http
////            .csrf()
////                .disable()
////                .headers().frameOptions().disable()
////            .and()
////            // Enable CORS at Spring Security level
////            .cors()
////            .and()
////            .authorizeHttpRequests()
////                .requestMatchers("/login/**")
////                    .permitAll()
////                .anyRequest()
////                    .authenticated().and().build();
////        // @formatter:on
////
////    }
//    
////    @Bean
////    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
////    	 http
////         .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
////         .and()
////         .addFilterBefore(new JwtAuthFilter(), BasicAuthenticationFilter.class)
////         .csrf().disable()
////         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////         .and()
////         .addFilterBefore(webConfig.corsFilter(), UsernamePasswordAuthenticationFilter.class) // Use corsFilter() method
////         .authorizeHttpRequests((requests -> {
////        	 requests.requestMatchers(new AntPathRequestMatcher("/login**")).permitAll();
////         }));
////
////        return http.build();	
////    }
////
////    


//}