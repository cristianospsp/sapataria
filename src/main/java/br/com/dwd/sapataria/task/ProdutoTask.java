package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.ProdutorEntityManager;
import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Produto;

import java.io.Serializable;
import java.security.cert.TrustAnchor;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@Named
@Resource
public class ProdutoTask implements Serializable {

	/*@Inject
	private Repository<Produto> repository;*/

	private EntityManager entitymanager = ProdutorEntityManager.getEntityManager();
	
	@Transactional
	public Produto add(Produto produto) {
		EntityTransaction transaction = entitymanager.getTransaction();
		transaction.begin();
		entitymanager.persist(produto);
		transaction.commit();
		//repository.add(produto);
		return produto;
	}
	
	 /*public Produto buscarProduto(Produto descricao){ Map<String, Object> prod
	  = new HashMap<>(); prod.put("descicao", descricao); List<Produto>
	  produtos =
	 
	 Produto.stream().findFirst().orElse(null); return null; }
	 */
}
