package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.task.ProdutoTask;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristiano on 08/12/15.
 */
@Named
@ViewScoped
public class EstoqueController implements Serializable {

	private List<Produto> produtos = new ArrayList<>();

	@Inject
	private ProdutoTask task;

	public void atualizar() {
		produtos = task.findQtdMenorQueCinco();
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

}
