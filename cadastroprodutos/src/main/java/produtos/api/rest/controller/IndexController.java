package produtos.api.rest.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import produtos.api.rest.model.Produto;
import produtos.api.rest.repository.ProdutoRepository;
import produtos.api.rest.security.AutenticacaoUsuarioService;

@RestController
@RequestMapping(value = "produto")
public class IndexController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private AutenticacaoUsuarioService autenticacaoUsuarioService;
	
	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity<List<Produto>> todosProdutos() {
		
		return new ResponseEntity(produtoRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public ResponseEntity<Produto> produto(@PathVariable Long id) {
		
		return new ResponseEntity(produtoRepository.findById(id).get(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/",produces = "application/json")
	public ResponseEntity<Produto> cadastroProduto(@RequestBody Produto produto) {
		
		Produto save = produtoRepository.save(produto);
		return new ResponseEntity(save,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}",produces = "application/json")
	public ResponseEntity<String> deletarProduto(@PathVariable(name = "id") Long id) {
		
		produtoRepository.deleteById(id);
		return new ResponseEntity("removido",HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/",produces = "application/json")
	public ResponseEntity<String> editarProduto(@RequestBody Produto produto) {
		
		Produto save = produtoRepository.save(produto);
		return new ResponseEntity(save,HttpStatus.OK);
	}
	
	
//	@PostMapping(value = "/login")
//	public ResponseEntity<Produto> login(@RequestBody User user) {
//		
//	
//	}
}
