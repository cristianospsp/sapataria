package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Produto;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class ProdutoTask implements Serializable {
 
	@Inject
	private Repository<Produto> repository;

	@Transactional
	public Produto add(Produto produto) {
		produto = repository.add(produto);
		return produto;
	}

	@Transactional
	public void delete(Produto produto) {
		repository.delete(repository.find(produto.getId()));
	}

	public List<Produto> listAll() {
		List<Produto> produtos = repository.listAll();
		return produtos;
	}

	public List<Produto> listByName(String nome) {
		Map<String, Object> param = new HashMap<>();
		param.put("nome", "%" + nome + "%");
		return repository.listBy(Produto.PRODUTO_LIST_NAME, param);
	}

	public Produto findById(long id) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		return repository.findBy(Produto.PRODUTO_FIND_ID, param);
	}

	public Produto findByName(String name) {
		Map<String, Object> param = new HashMap<>();
		param.put("nome", name);
		try {
			return repository.findBy(Produto.PRODUTO_FIND_NAME, param);
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Produto update(Produto produto) {
		produto = repository.update(produto);
		return produto;
	}

	public List<Produto> listAvisos() {
		Map<String, Object> param = new HashMap<>();
		//param.put("quantidadeMinima", );
		return repository.listBy(Produto.PRODUTO_LIST_NAME, param);
	}

	public List<Produto> buscarProduto(Produto produto) {
		Map<String, Object> param = new HashMap<>();
		param.put("nome", produto.getNome());
		return repository.listBy(Produto.PRODUTO_FIND_BY_NOME, param);
	}

	public List<Produto> findAll() {
		return repository.listAll();
	}


}
