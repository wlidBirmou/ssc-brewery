package guru.sfg.brewery.config;


import guru.sfg.brewery.security.SfgPasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequest -> {
                authorizeRequest.antMatchers("/","/login","/webjars/**","/resources/**", "/beers/find").permitAll();
                })
                .authorizeRequests().anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(getPasswordEncoder().encode("password")).roles("USER")
                .and().withUser("admin").password(getPasswordEncoder().encode("password")).roles("ADMIN");

    }
}
