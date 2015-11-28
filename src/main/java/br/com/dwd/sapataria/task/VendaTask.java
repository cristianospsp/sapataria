package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Venda;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cristiano on 19/10/15.
 */
@Named
public class VendaTask implements Serializable {

	@Inject
	private Repository<Venda> repository;
	//private EntityManager entitymanager = ProdutorEntityManager.getEntityManager();

	public List<Venda> findAll() {
		/*Map<String, Object> param = new HashMap<>();
		List<Venda> vendas = repository.listBy(Venda.VENDA_FIND_ALL, param);*/
		//List<Venda> vendas = entitymanager.listAll();
//		return vendas;
		return Collections.emptyList();
	}

	@Transactional
	public Venda insertOrUpdate(Venda venda) {
		/*EntityTransaction transaction = entitymanager.getTransaction();
		if (venda.getId() == null){
			transaction.begin();
			entitymanager.persist(venda);
			transaction.commit();
		}else {
			venda = entitymanager.merge(venda);
		}*/
		return null;
	}

}
