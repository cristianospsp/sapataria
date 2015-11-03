package br.com.dwd.sapataria.controller;


import br.com.dwd.sapataria.model.Pedido;
import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.model.Venda;
import br.com.dwd.sapataria.task.VendaTask;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


/**
 * Created by Cristiano on 19/10/15.
 */
@Named
@ViewScoped
public class VendaController implements Serializable {

	private List<Venda> vendas = new ArrayList<>();
	private List<Pedido> pedidos = new ArrayList<>();
	@Inject
	private VendaTask task;
	private Pedido pedidoSelecionado;


	@PostConstruct
	public void init() {
		refresh();
		pedidos.addAll(geraPedido());
	}

	private List<Pedido> geraPedido() {
		List<Pedido> pedidos = new ArrayList<>();

		Produto adidas = new Produto("Tenis", "Adidas", "Branco", 340.00);
		Produto nike = new Produto("Tenis", "Nike", "Azul", 350.00);
		Produto mizuno = new Produto("Tenis", "Mizuno", "Preto", 450.00);

		Produto chinelo = new Produto("Chinelo", "Havaianas", "Azul", 40.00);
		Produto sapato = new Produto("Sapato", "Nosferato", "Preto", 150.00);
		Produto sandalia = new Produto("Sandália", "Azaléia", "Brancao", 50.00);

		Pedido pedido1 = new Pedido();
		pedido1.setNumero(123456l);
		pedido1.setVendedor("Kebra Barraco");
		pedido1.adicionaProduto(adidas, 1);
		pedido1.adicionaProduto(nike, 1);
		pedido1.adicionaProduto(mizuno, 1);

		Pedido pedido2 = new Pedido();
		pedido2.setNumero(44444l);
		pedido2.setVendedor("Kebra Apartamento");
		pedido2.adicionaProduto(chinelo, 1);
		pedido2.adicionaProduto(sapato, 1);
		pedido2.adicionaProduto(sandalia, 1);

		pedidos.addAll(Arrays.asList(pedido1, pedido2));

		return pedidos;
	}

	public void refresh() {
		vendas = task.findAll();
	}

	public List<Venda> getVendas() {
		return vendas;
	}


	public String vender() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("Teste", new FacesMessage(FacesMessage.SEVERITY_INFO, "Vendeu ! ! !", "Vendeu ! ! !"));
		//FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		//Faces.redirect("/sapataria/restrito/venda/lista.xhtml");
		return "lista?faces-redirect=true";
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void select(Pedido pedido) {
		this.pedidoSelecionado = pedido;
	}

	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void limparSelecao() {
		this.pedidoSelecionado = null;
	}
}
