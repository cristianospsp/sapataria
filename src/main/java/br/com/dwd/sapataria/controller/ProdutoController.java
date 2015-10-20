package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.task.ProdutoTask;
import org.omnifaces.util.Faces;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import java.io.Serializable;

@Named
@ViewScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String LISTA = "/sapataria/restrito/produto/lista.xhtml";

	private Produto produto = new Produto();

	@Inject
	private ProdutoTask task;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void salvar()  {
		try {
			task.add(produto);
			Faces.redirect(LISTA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listar() {

	}

}
