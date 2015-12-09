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

	public List<Venda> findAll() {
		return repository.listAll();
	}

	@Transactional
	public Venda insertOrUpdate(Venda venda) {
		if (venda.getId() == null){
			repository.add(venda);
		}else {
			venda = repository.update(venda);
		}
		return venda;
	}

}
