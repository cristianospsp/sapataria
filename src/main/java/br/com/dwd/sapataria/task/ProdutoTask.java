package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Produto;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Named
//@Resource
public class ProdutoTask implements Serializable {

	@Inject
	private Repository<Produto> repository;

	//private EntityManager entitymanager = ProdutorEntityManager.getEntityManager();

	@Transactional
	public Produto add(Produto produto) {
		/*EntityTransaction transaction = entitymanager.getTransaction();
		transaction.begin();
		entitymanager.persist(produto);
		transaction.commit();*/
		produto = repository.add(produto);
		return produto;
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

	public void delete(Produto produto) {
		EntityTransaction transaction = entitymanager.getTransaction();
		transaction.begin();
		entitymanager.delete(entitymanager.merge(produto);
		transaction.commit();
	}

}
