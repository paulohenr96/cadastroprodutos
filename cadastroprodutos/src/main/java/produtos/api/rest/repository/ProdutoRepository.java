package produtos.api.rest.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import produtos.api.rest.model.Produto;


@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	
}
