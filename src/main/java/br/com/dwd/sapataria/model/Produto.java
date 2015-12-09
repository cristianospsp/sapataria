package br.com.dwd.sapataria.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Cristiano on 02/10/15.//
 */
@Entity
@NamedQueries({
	 @NamedQuery(
			name = Produto.PRODUTO_LIST_NAME,
			query = "select p from Produto p where p.nome like :nome"),
	 @NamedQuery(
			name = Produto.PRODUTO_LIST_ALL,
			query = "select p from Produto p"),
	 @NamedQuery(
			name = Produto.PRODUTO_FIND_NAME,
			query = "select p from Produto p where p.nome = :nome"),
	 @NamedQuery(
			name = Produto.PRODUTO_FIND_ID,
			query = "select p from Produto p where p.id = :id"),
	 @NamedQuery(
			name = Produto.PRODUTO_FIND_BY_NOME,
			query = "select p from Produto p where p.nome = :nome"
	 ),
	 @NamedQuery(
			name = Produto.PRODUTO_FIND_QTD_ESTOQUE_MENOR_QUE,
			query = "select p from Produto p where p.quantidadeTotal < :qtd"
	 )
})
public class Produto implements Serializable {

	public static final String PRODUTO_LIST_NAME = "Produto.listName";
	public static final String PRODUTO_FIND_NAME = "Produto.findName";
	public static final String PRODUTO_LIST_ALL = "Produto.listAll";
	public static final String PRODUTO_FIND_ID = "Produto.findId";
	public static final String PRODUTO_FIND_BY_NOME = "Produto.findByNome";
	public static final String PRODUTO_FIND_QTD_ESTOQUE_MENOR_QUE = "Produto.FindQtdEstoqueMenorQue";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private String fabricante;

	@NotNull
	private int tamanho;

	@NotNull
	private String cor;

	@NotNull
	private int quantidadeTotal;

	@NotNull
	private int quantidadeMinima;

	@NotNull
	private double valor;

	public Produto() {
	}

	public Produto(String nome, String fabricante, String cor, double valorVenda) {
		this.nome = nome;
		this.fabricante = fabricante;
		this.cor = cor;
		this.valor = valorVenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(int qtdTotal) {
		this.quantidadeTotal = qtdTotal;
	}

	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Produto)) return false;
		Produto produto = (Produto) o;
		return Objects.equals(getId(), produto.getId()) &&
			 Objects.equals(getNome(), produto.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getNome());
	}
}
