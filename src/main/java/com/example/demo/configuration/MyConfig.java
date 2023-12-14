package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
class MyConfig {
    
	@Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder().
                username("DURGESH")
                .password(passwordEncoder().encode("DURGESH")).roles("ADMIN").
                build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
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