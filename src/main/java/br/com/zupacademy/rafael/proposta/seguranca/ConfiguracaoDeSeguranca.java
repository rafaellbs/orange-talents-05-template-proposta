package br.com.zupacademy.rafael.proposta.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.POST, "/propostas/**")
                        .hasAuthority("SCOPE_propostas:write")
                        .antMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated())
                        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
