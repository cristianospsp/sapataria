package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Usuario;
import br.com.dwd.sapataria.model.Venda;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cristiano on 19/10/15.
 */
@Named
public class VendaTask implements Serializable {

	@Inject
	private Repository<Venda> repository;

	public List<Venda> findAll() {
		/*Map<String, Object> param = new HashMap<>();
		List<Venda> vendas = repository.listBy(Venda.VENDA_FIND_ALL, param);*/
		List<Venda> vendas = repository.listAll();
		return vendas;
	}

	public Venda insertOrUpdate(Venda venda) {
		if (venda.getId() == null){
			repository.add(venda);
		}else {
			venda = repository.update(venda);
		}
		return venda;
	}

}
