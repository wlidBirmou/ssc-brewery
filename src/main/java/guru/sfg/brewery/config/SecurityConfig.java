package guru.sfg.brewery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Created by jt, Spring Framework Guru.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        return http
                .authorizeHttpRequests((authRequest) -> {
                    authRequest.requestMatchers(mvc.pattern("/"),
                                    mvc.pattern("/webjars/**"), mvc.pattern("/resources/**"),
                                    mvc.pattern("/login"), mvc.pattern("/beers/find"),
                                    mvc.pattern(HttpMethod.GET, "/api/v1/beer"),
                                    mvc.pattern(HttpMethod.GET, "/api/v1/beer/{beerId}"),
                                    mvc.pattern(HttpMethod.GET, "/api/v1/beerUpc/{upc}"))
                            .permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
