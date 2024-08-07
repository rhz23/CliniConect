package br.com.rzaninelli.CliniConect.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSucurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //desabiliar a necessidade de authenticação do usuario
        http.csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(autorizar -> autorizar.requestMatchers(HttpMethod.POST, "/usuario", "/login").permitAll()
                                .anyRequest().authenticated()).cors(Customizer.withDefaults());

        http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
