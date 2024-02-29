////package com.example.timski.config;
////
////import com.example.timski.config.UsernamePasswordProvider;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.SecurityConfigurer;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////
////import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.DefaultSecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
////public class WebSecurityConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {
////
////    private final UsernamePasswordProvider usernamePasswordProvider;
////    private final PasswordEncoder passwordEncoder;
////
////    public WebSecurityConfig(PasswordEncoder passwordEncoder, UsernamePasswordProvider usernamePasswordProvider) {
////        this.usernamePasswordProvider = usernamePasswordProvider;
////        this.passwordEncoder = passwordEncoder;
////    }
////
////    @Override
////    public void init(HttpSecurity builder) throws Exception {
////
////    }
////
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable)
////                .cors(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/home", "/", "/home", "/assets/**", "/register", "/product", "/api/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .sessionManagement(session -> session
////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                );
////    }
////
////
////    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(usernamePasswordProvider);
////    }
////}
//
//
//
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//    private final UsernamePasswordProvider usernamePasswordProvider;
//
//    public WebSecurityConfig(PasswordEncoder passwordEncoder, UsernamePasswordProvider usernamePasswordProvider) {
//        this.passwordEncoder = passwordEncoder;
//        this.usernamePasswordProvider = usernamePasswordProvider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/assets/**", "/register", "/product", "/api/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .failureUrl("/login?error=BadCredentials")
//                .defaultSuccessUrl("/product", true)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login")
//                .and()
//                .exceptionHandling().accessDeniedPage("/access_denied");
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////            auth.inMemoryAuthentication().withUser("ivana.dimitrovska")
////                    .password(passwordEncoder.encode("id"))
////                    .authorities("ROLE_USER").and().withUser("admin")
////                    .password(passwordEncoder.encode("admin"))
////                    .authorities("ROLE_ADMIN");
//        auth.authenticationProvider(usernamePasswordProvider);
//    }
//}