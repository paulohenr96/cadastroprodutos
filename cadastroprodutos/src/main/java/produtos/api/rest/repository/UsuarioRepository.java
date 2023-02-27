package produtos.api.rest.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import produtos.api.rest.model.Usuario;


@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	
	Usuario findByLogin(String login);
}
