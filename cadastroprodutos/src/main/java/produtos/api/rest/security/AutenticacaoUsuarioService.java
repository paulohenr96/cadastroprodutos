package produtos.api.rest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import produtos.api.rest.model.Usuario;
import produtos.api.rest.repository.UsuarioRepository;

@Service
public class AutenticacaoUsuarioService implements UserDetailsService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioRepository.findByLogin(username);
		
		List<GrantedAuthority> permissoes= new ArrayList<>();
		
		
		usuario.getRoles().forEach((r)->{
			permissoes.add(new SimpleGrantedAuthority(r.getNomeRole()));
		});
		
		
		
		return new User(usuario.getLogin(),usuario.getPassword(),permissoes) ;
	}
	
	
	
}
