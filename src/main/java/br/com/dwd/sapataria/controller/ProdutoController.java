package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.qualify.HttpParam;
import br.com.dwd.sapataria.task.ProdutoTask;

import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@Named
@ViewScoped
public class ProdutoController implements Serializable {

	private static final String LISTA = "/sapataria/restrito/produto/lista.xhtml";

	private Produto produto;

	@Inject
	private ProdutoTask task;

	@Inject
	@HttpParam("id-produto")
	private transient Optional<String> idSelecionado;

	@PostConstruct
	public void init() {
		produto = idSelecionado.map(id -> Long.valueOf(id)).map(id -> task.findById(id)).orElse(new Produto());
	}


	public Produto update(Produto produto) {
		try {
			task.update(produto);
			System.out.println("Produto atualizado");
			Faces.redirect(LISTA);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void verificar(){
		//metodo verifica se o ID do produto já exite (para atualizar ou salvar)
		try {
			Long id = produto.getId();
			Produto produtoFindId= task.findById(id);
			if (produtoFindId != null) {	
				this.produto = update(produto);
			} 
		} catch (Exception e) {
			this.salvar(produto);
		}

		
	}

	public void salvar(Produto produto) {
		try {
			//falta implementar verificação do nome e das quantidades
			task.add(produto);
			Faces.redirect(LISTA); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
