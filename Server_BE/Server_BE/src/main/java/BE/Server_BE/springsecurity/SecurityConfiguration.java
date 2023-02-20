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


                        // [회원 등록] -> 아무나 가능
                        .antMatchers(HttpMethod.POST,"/members").permitAll()
                        // [회원 수정] -> 회원 당사자, 관리자
                        .antMatchers(HttpMethod.PATCH,"/members/{member-id}").hasAnyRole("USER","ADMIN")
                        // [회원 단일 조회] -> 당사자 유저, 관리자
                        .antMatchers(HttpMethod.GET,"/members/{member-id}").hasAnyRole("USER","ADMIN")
                        // [회원 전체 조회] -> 관리자
                        .antMatchers(HttpMethod.GET,"/members").hasRole("ADMIN")
                        // [회원 삭제] -> 관리자
                        .antMatchers(HttpMethod.DELETE, "/members/{member-id}").hasRole("ADMIN")


                        // [게시글 등록] -> 유저, 관리자
                        .antMatchers(HttpMethod.POST,"/boards").hasRole("USER")
                        // [게시글 수정] -> 본인 [작성자], 관리자
                        .antMatchers(HttpMethod.PATCH, "/boards/{board-id}").hasRole("USER")
                        // [게시글 조회] -> 유저, 관리자
                        // [게시글 삭제] -> 본인 [작성자], 관리자

                        // [답글 등록] -> 회원인 유저, 관리자
                        // [답글 수정] -> 회원인 유저[작성자], 관리자
                        // [답글 조회] -> 회원인 유저, 관리자
                        // [답글 삭제] -> 회원인 유저[작성자], 관리자

                        // [댓글 등록] -> 회원인 유저, 관리자
                        // [댓글 수정] -> 회원인 유저[작성자], 관리자
                        // [댓글 조회] -> 회원인 유저, 관리자
                        // [댓글 삭제] -> 회원인 유저[작성자], 관리자

                        .antMatchers(HttpMethod.PATCH,"/boards/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers("/members/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers("/comments/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()

                );
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