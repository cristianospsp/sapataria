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
					name = Produto. PRODUTO_FIND_ID,
					query = "select p from Produto p where p.id = :id"),
	 @NamedQuery(
			name = Produto.PRODUTO_FIND_BY_NOME,
			query = "select p from Produto p where p.nome = :nome"
	 ),
	 @NamedQuery(
			name = Produto.PRODUTO_FIND_BY_CODIGO_BARRA,
			query = "select p from Produto p where p.codigoBarras = :codigoBarras"
	 )
})
public class Produto implements Serializable {

	public static final String PRODUTO_LIST_NAME = "Produto.listName";
	public static final String PRODUTO_FIND_NAME = "Produto.findName";
	public static final String PRODUTO_LIST_ALL = "Produto.listAll";
	public static final String PRODUTO_FIND_ID = "Produto.findId";
	public static final String PRODUTO_FIND_BY_NOME = "Produto.findByNome";
	public static final String PRODUTO_FIND_BY_CODIGO_BARRA = "Produto.findByCodigoBarra";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@NotNull
	private String fabricante;

	@NotNull
	private long codigoBarras;

	@NotNull
	private int tamanho;

	@NotNull
	private String cor;

	@NotNull
	private int qtdTotal;

	@Column
	@NotNull
	private double vlrVenda;

	@Column
	@NotNull
	private double vlrCompra;

	@Column
	@NotNull
	private boolean status;

	public Produto() {
	}

	public Produto(String nome, String fabricante, String cor, double vlrVenda) {
		this.nome = nome;
		this.fabricante = fabricante;
		this.cor = cor;
		this.vlrVenda = vlrVenda;
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

	public long getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(long codigoBarras) {
		this.codigoBarras = codigoBarras;
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

	public int getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(int qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public double getVlrVenda() {
		return vlrVenda;
	}

	public void setVlrVenda(double vlrVenda) {
		this.vlrVenda = vlrVenda;
	}

	public double getVlrCompra() {
		return vlrCompra;
	}

	public void setVlrCompra(double vlrCompra) {
		this.vlrCompra = vlrCompra;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
