package BE.Server_BE.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {


//    private final JwtTokenizer jwtTokenizer;
//    private final UserAuthorityUtils userAuthorityUtils;

//    public SecurityConfiguration(JwtTokenizer jwtTokenizer, UserAuthorityUtils userAuthorityUtils) {
//        this.jwtTokenizer = jwtTokenizer;
//        this.userAuthorityUtils = userAuthorityUtils;
//    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http 요청에 따른 보안 설정을 구성
        http

                .authorizeRequests().antMatchers("/h2/*").permitAll()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()   // 추후 설정 필요
                .cors(withDefaults())
                .formLogin()
//                .loginPage("/url")
//                .loginProcessingUrl("/url")
//                .failureUrl( 로그인 페이지 url?error + 에러메세지 )
                .and()
                .logout()
//                .logoutUrl("/url")
//                .logoutSuccessUrl("로그인 페이지 url")
//                .exceptionHandling().accessDeniedPage("/url") 권한없는 사용자 접근 시 보여줄 페이지
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/boards/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers("/members/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers("/comments/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest()
                        .permitAll()
                );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}