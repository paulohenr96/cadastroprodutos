package produtos.api.rest.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenService {
	
	
	
	
	
	public void addAuthentication(HttpServletResponse response,String username) throws IOException {
		String jwt = Jwts.builder()
		.setSubject(username)
		.setExpiration(new Date((9999L+System.currentTimeMillis())))
		.signWith(SignatureAlgorithm.HS256, "!SenhaSecreta").compact();
		
		
		String token = "Bearer "+jwt;
		
		response.addHeader("Authorization", token);
		
		response.getWriter().write("{\"Authorization\":\""+token+"\"}");
	}
	
	public Authentication getAuthentication(HttpServletRequest request,HttpServletResponse response) {
		return null;
		
	}

}
