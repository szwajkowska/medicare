package pl.ania.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.ania.security.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth. inMemoryAuthentication()
                .withUser("user1").password("1").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.csrf().disable();
        security
                .authorizeRequests()
                .antMatchers("/login", "/signIn").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user1").password("1").roles("USER");
//        auth
//                .inMemoryAuthentication()
//                .withUser("user2").password("2").roles("USER");
//        auth.authenticationProvider(authProvider);
//
//    }
}
