package br.com.nutriguacu.mig_recicla_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            // Mantém a configuração de CORS liberada para o Next.js conseguir conversar com o Spring
            .cors(Customizer.withDefaults())
            
            .authorizeHttpRequests(auth -> auth
                // Libera o Swagger para você poder continuar lendo a documentação
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                
                // EXIGE AUTENTICAÇÃO para qualquer outra rota (/api/clientes, /api/notas-fiscais, etc)
                .anyRequest().authenticated()
            )
            // Ativa o leitor de JWT que configuramos no application.properties
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}