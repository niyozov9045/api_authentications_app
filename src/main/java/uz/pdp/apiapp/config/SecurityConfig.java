package uz.pdp.apiapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("director1").password(passwordEncoder().encode("director1")).roles("Director").authorities("GET_ALL_PRODUCT","GET_ONE","ADD_PRODUCT","EDIT_PRODUCT","DELETE_PRODUCT")
                .and()
                .withUser("director2").password(passwordEncoder().encode("director2")).authorities("Director").authorities("GET_ALL_PRODUCT","GET_ONE","ADD_PRODUCT","EDIT_PRODUCT")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("User");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests() // Requestlani authorizatsiya qilish
                .anyRequest()        // Har qanday requestlani autho qilish
                .authenticated();     // Tekshirib keyn utqazadi
//                .and()
//                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}