package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.task.ProdutoTask;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ProdutoFiltroController extends Controller implements Serializable {

	private Produto produto = new Produto();
	private List<Produto> produtos = new ArrayList<>();

	@Inject
	private ProdutoTask task;

	public void listarProduto() {
		task.listAll();
	}

	public void buscarProduto() {
		produtos = task.listByName(produto.getNome());
	}

	public void delete(Produto produto) {
		if (produto.getQuantidadeTotal() <= 0) {
			task.delete(produto);
			buscarProduto();
			this.produto = new Produto();
			messageSucess(getFacesContext(), "Sucesso !", "Produto Excluído.");
		} else {
			messageWarn(getFacesContext(), "Produto em estoque !", "Indispoível para Exclusão.");
		}
	}

	private FacesContext getFacesContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		return context;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
