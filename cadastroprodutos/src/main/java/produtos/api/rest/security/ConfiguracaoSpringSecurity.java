package produtos.api.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSpringSecurity extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private AutenticacaoUsuarioService autenticacaoUsuarioService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.userDetailsService(autenticacaoUsuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable()
		.authorizeRequests()
		
		.antMatchers("/login").permitAll()
		.anyRequest().authenticated()
//		Filtra a requisição de login para a autenticação
		.and().addFilterBefore(new JWTLoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class);
	
	
//   	Filtra as demais requisições para identificar a presença de TOKEN no HEADER	
//		.addFilterBefore(new JwtApiAutenticacaoFilter(),UsernamePasswordAuthenticationFilter.class);
	}
}
