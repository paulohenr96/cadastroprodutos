package produtos.api.rest.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import produtos.api.rest.ApplicationContextLoad;
import produtos.api.rest.model.Usuario;
import produtos.api.rest.repository.UsuarioRepository;

@Service
public class JWTTokenService {

	public void addAuthentication(HttpServletResponse response, String username) throws IOException {
		String jwt = Jwts.builder().setSubject(username).setExpiration(new Date((172800000L + System.currentTimeMillis())))
				.signWith(SignatureAlgorithm.HS256, "!SenhaSecreta").compact();

		String token = "Bearer " + jwt;

		response.addHeader("Authorization", token);

		response.getWriter().write("{\"Authorization\":\"" + token + "\"}");
	}

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {

		String token = request.getHeader("Authorization");
		System.out.println(token);

		try {
			if (token != null) {
				String subject = Jwts.parser().setSigningKey("!SenhaSecreta")
						.parseClaimsJws(token.replace("Bearer ", "")).getBody().getSubject();

				if (subject != null) {
					Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
							.findByLogin(subject);

					if (usuario != null) {
						return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getPassword(),
								usuario.getAuthorities());
					}
				}
			}
		} catch (ExpiredJwtException e) {
			// TODO Auto-generated catch block
			try {
				response.getOutputStream()
						.println("Seu token está expirado, faça o login ou informe um novo Token para a autenticação");
			} catch (IOException e1) {
			}
		} 

		return null;

	}

}
