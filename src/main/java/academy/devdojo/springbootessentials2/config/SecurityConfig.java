package academy.devdojo.springbootessentials2.config;

import academy.devdojo.springbootessentials2.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/animes/admin/**").hasRole("ADMIN")
                .antMatchers("animes/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password Encoded {}", passwordEncoder.encode("academy"));

//        auth.inMemoryAuthentication()
//                .withUser("monia")
//                .password(passwordEncoder.encode("academy"))
//                .roles("USER", "ADMIN")
//                .and()
//                .withUser("user")
//                .password(passwordEncoder.encode("academy"))
//                .roles("USER");

            auth.userDetailsService(service)
                    .passwordEncoder(passwordEncoder);
    }
}
