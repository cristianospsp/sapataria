package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Pedido;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Cristiano on 09/12/15.
 */
@Named
public class PedidoTask  implements Serializable {

	@Inject
	private Repository<Pedido> repository;

	@Transactional
	public Pedido add(Pedido pedido) {
		return repository.add(pedido);
	}

	@Transactional
	public List<Pedido> findAll() {
		return repository.listAll();
	}

	@Transactional
	public void delete(Pedido pedidoSelecionado) {
		repository.delete(repository.find(pedidoSelecionado.getId()));
	}
}
