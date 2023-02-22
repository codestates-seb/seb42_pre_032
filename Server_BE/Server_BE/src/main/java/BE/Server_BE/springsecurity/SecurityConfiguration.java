package BE.Server_BE.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final HelloAuthorityUtils authorityUtils;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, HelloAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize

                        .antMatchers(HttpMethod.POST,"/members").permitAll()
                        .antMatchers(HttpMethod.PATCH,"/members/{member-id}").hasAnyRole("USER","ADMIN")
                        .antMatchers(HttpMethod.GET,"/members/{member-id}").hasAnyRole("USER","ADMIN")
                        .antMatchers(HttpMethod.GET,"/members").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/members/{member-id}").hasRole("ADMIN")

                        .antMatchers(HttpMethod.POST,"/boards").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.PATCH, "/boards/{board-id}").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.GET, "/boards/{board-id}").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.GET, "/boards").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.DELETE, "/boards/{board-id}").hasAnyRole("ADMIN", "USER")

                                .antMatchers(HttpMethod.POST,"/answers/{board-id}").hasAnyRole("ADMIN", "USER")
                                .antMatchers(HttpMethod.PATCH, "/answers/{answer-id}").hasAnyRole("ADMIN", "USER")
                                .antMatchers(HttpMethod.GET, "/answers/{answer-id}").hasAnyRole("ADMIN", "USER")
                                .antMatchers(HttpMethod.GET, "/answers").hasAnyRole("ADMIN", "USER")
                                .antMatchers(HttpMethod.DELETE, "/answers/{answer-id}").hasAnyRole("ADMIN","USER")

                                .antMatchers(HttpMethod.POST, "/comments/{answer-id}").hasAnyRole("ADMIN","USER")
                                .antMatchers(HttpMethod.PATCH,"/comments/{comment-id}").hasAnyRole("ADMIN","USER")
                                .antMatchers(HttpMethod.GET, "/comments/{comment-id}").hasAnyRole("ADMIN","USER")
                                .antMatchers(HttpMethod.GET, "/comments").hasAnyRole("ADMIN","USER")
                                .antMatchers(HttpMethod.DELETE,"/comments/{comment-id}").hasAnyRole("ADMIN","USER")
                        .anyRequest().permitAll());
        return http.build();
    }
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter
                    = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/login");

            jwtAuthenticationFilter.setAuthenticationSuccessHandler
                    (new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler
                    (new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter =
                    new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("POST","GET","PATCH","DELETE"));;

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}