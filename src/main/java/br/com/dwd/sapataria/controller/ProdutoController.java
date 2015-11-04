package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.task.ProdutoTask;

import org.omnifaces.util.Faces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;

import java.io.Serializable;

@Named
@ViewScoped
public class ProdutoController implements Serializable {
 
	private static final String LISTA = "/sapataria/restrito/produto/lista.xhtml";

	private Produto produto = new Produto();

	@Inject
	private ProdutoTask task;

	public void salvar() {
		/*passa a 'quantidade' para o metodo que verifica...
		Produto quantidade = quantidade(produto.getQuantidadeTotal(), produto.getQuantidadeMinima());*/
		try {
			String name = produto.getNome();
			Produto produtoFindName = task.findByName(name);
			if (produtoFindName != null) {
				JOptionPane.showMessageDialog(null, "JÁ TEM UM PRODUTO CADASTRADO COM ESTE NOME");
			} else {
				task.add(produto);
				Faces.redirect(LISTA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// verificar se a quantidade minina é menor que a qtd total
/*	public Produto quantidade(int total, int minimo) {
		if (minimo <= total) {
			return produto;
		}
		return null;
	}*/

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
