package produtos.api.rest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import produtos.api.rest.model.Usuario;

public class JWTLoginFilter extends  AbstractAuthenticationProcessingFilter {

	protected JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		
		
//		super(defaultFilterProcessesUrl, authenticationManager);
		super (new AntPathRequestMatcher(defaultFilterProcessesUrl));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		
		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		
		
		
		
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(usuario.getLogin(),usuario.getPassword(),usuario.getAuthorities()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		new JWTTokenService().addAuthentication(response, authResult.getName());
	}

}
