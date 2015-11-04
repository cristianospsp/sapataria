package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.qualify.HttpParam;
import br.com.dwd.sapataria.task.ProdutoTask;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
public class ProdutoFiltroController implements Serializable {

	private Produto produto = new Produto();
	private List<Produto> produtos = new ArrayList<>();

	/*@Inject
	@HttpParam("id-produto")*/
	private transient Optional<String> idSelecionado;
	
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
		} else {
			JOptionPane.showMessageDialog(null, "ESTE PRODUTO AINDA ESTÁ COM ESTOQUE >0");
		}
	}

/*	public void update(Produto produto) {
		long id = produto.getId();
		Produto produtoFindId = task.findById(id);
		this.produto = produtoFindId;
	}
	*/
	@PostConstruct 
	public void init(){
		produto = idSelecionado
				.map(id -> Long.valueOf(id))
				.map(id -> task.findById(id))
				.orElse(new Produto());
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
