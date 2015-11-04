package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.ProdutorEntityManager;
import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Produto;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class ProdutoTask implements Serializable {

	@Inject
	private Repository<Produto> repository;

	private EntityManager entitymanager = ProdutorEntityManager.getEntityManager();

	public Produto add(Produto produto) {
		EntityTransaction transaction = entitymanager.getTransaction();
		transaction.begin();
		entitymanager.persist(produto);
		transaction.commit();
		return produto;
	}

	public void delete(Produto produto) {
		EntityTransaction transaction = entitymanager.getTransaction();
		transaction.begin();
		entitymanager.remove(entitymanager.merge(produto));
		transaction.commit();
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


	public Produto update(Produto produto) {
		EntityTransaction transaction = entitymanager.getTransaction();
		transaction.begin();
		entitymanager.merge(produto);
		transaction.commit();
		// return repository.update(produto);
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

	public Produto findByCodigoBarra(Long codigoBarras) {
		Map<String, Object> param = new HashMap<>();
		param.put("codigoBarras", codigoBarras);
		return repository.listBy(Produto.PRODUTO_FIND_BY_CODIGO_BARRA, param).stream().findFirst().orElse(null);
	}

	public List<Produto> findAll() {
		return repository.listAll();
	}


}
