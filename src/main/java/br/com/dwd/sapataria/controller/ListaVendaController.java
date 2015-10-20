package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Venda;
import br.com.dwd.sapataria.task.VendaTask;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristiano on 19/10/15.
 */
@Named
@RequestScoped
public class ListaVendaController {

	private List<Venda> vendas = new ArrayList<>();
	@Inject
	private VendaTask task;


	public void findAll() {
		//vendas = task.findAll();
	}

}
