package br.com.dwd.sapataria.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Cristiano on 19/10/15.
 */
//@Entity
public class Pedido implements Serializable {

	//@Id
	private Long id;
	/*@OneToMany
	private List<Produto> produtos = new ArrayList<>();*/

	private Map<Produto, Integer> produtos = new HashMap<>();
	private String vendedor;
	private Long numero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<Produto, Integer> getProdutos() {
		return produtos;
	}

	public List<Map.Entry<Produto, Integer>> getListaEntrySetComoList() {
		return new ArrayList<>(produtos.entrySet()); //.stream().map(e -> e.getKey()).collect(Collectors.toList());
	}

	public Set<Map.Entry<Produto, Integer>> getListaProdutos() {
		return produtos.entrySet(); //.stream().map(e -> e.getKey()).collect(Collectors.toList());
	}

	public void adicionaProduto(Produto produto, Integer quantidade) {
		this.produtos.put(produto, quantidade);
	}

	/*public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}*/

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Double getTotal() {
		Double total = 0d;
		for (Map.Entry<Produto, Integer> pair : produtos.entrySet()) {
			double vlrVenda = pair.getKey().getValor();
			Integer qtd = pair.getValue();
			total += (qtd * vlrVenda);
		}
		return total;

//		return new BigDecimal(produtos.stream().mapToDouble(p -> p.getVlrVenda()).sum());
	}

}
