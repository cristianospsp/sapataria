package br.com.dwd.sapataria.controller;


import br.com.dwd.sapataria.model.Pedido;
import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.model.Venda;
import br.com.dwd.sapataria.task.PedidoTask;
import br.com.dwd.sapataria.task.ProdutoTask;
import br.com.dwd.sapataria.task.VendaTask;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Cristiano on 19/10/15.
 */
@Named
@ViewScoped
public class VendaController extends Controller implements Serializable {

	private List<Venda> vendas = new ArrayList<>();
	private List<Pedido> pedidos = new ArrayList<>();
	@Inject
	private VendaTask task;
	private Pedido pedidoSelecionado;
	@Inject
	private PedidoTask pedidoTask;
	@Inject
	private ProdutoTask produtoTask;

	@PostConstruct
	public void init() {
		refresh();
		atualizaPedidos();
	}

	public void grerarPedidoAleatorio() {

		pedidoTask.add(getPedido());
		atualizaPedidos();
	}


	private Pedido getPedido() {
		Pedido pedido = new Pedido();

		List<Produto> produtos = produtoTask.findAll();

		int inicial = 0;
		boolean added = false;
		for (Produto p : produtos) {
			Integer qtdAtual = p.getQuantidadeTotal();
			Integer qtd = (qtdAtual == 1 || qtdAtual <= 5) ? 1 : (qtdAtual > 5 || qtdAtual <= 8 ) ? 2 : (qtdAtual > 8 || qtdAtual < 15 ) ? 3 : qtdAtual >= 15 ? 3 : 1;

			if (inicial < 1) {
				pedido.adicionaProduto(p, qtd);
				added = true;
			} else if ((inicial % 2) == 0) {
				pedido.adicionaProduto(p, qtd);
				added = true;
			}
			inicial++;
		}

		if (added && ((new Random().nextInt(10) % 2) == 0)) {
			pedido.setVendedor(((new Random().nextInt(10) % 2) == 0) ? "João Donato" : "Ivonete de Holanda");
		} else if(added) {
			pedido.setVendedor(((new Random().nextInt(10) % 2) == 0) ? "Mariza Mouritano" : "Maria Aleluia");
		}


		String numeroPedidoString = LocalDateTime.now().getSecond() + "" + new Random().nextInt(100000);

		Long numeroPedido = Long.valueOf(numeroPedidoString + (pedido.getListaProdutos().size() + new Random().nextInt(100000)));

		pedido.setNumero(numeroPedido);

		return pedido;
	}

	public void refresh() {
		vendas = task.findAll();
	}

	public void atualizaPedidos() {
		pedidos = pedidoTask.findAll();
	}

	public List<Venda> getVendas() {
		return vendas;
	}


	public String vender() {
		Venda venda = new Venda(pedidoSelecionado.getVendedor(), pedidoSelecionado.getTotal());
		task.insertOrUpdate(venda);
		pedidoSelecionado.getListaProdutos().stream().forEach(p -> produtoTask.baixaEstoque(p));
		pedidoTask.delete(pedidoSelecionado);
		messageSucess(getFacesContext(), "Produto Vendido", "Baixa no estoque realizada com sucesso !");
		return "lista?faces-redirect=true";
	}

	private FacesContext getFacesContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		return context;
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
