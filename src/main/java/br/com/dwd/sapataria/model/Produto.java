package br.com.dwd.sapataria.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.deltaspike.core.api.projectstage.ProjectStage.Production;

import java.io.Serializable;

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
})
@Table
public class Produto implements Serializable {
	public static final String PRODUTO_LIST_NAME = "Produto.listName";
	public static final String PRODUTO_FIND_NAME = "Produto.findName";
	public static final String PRODUTO_LIST_ALL = "Produto.listAll";
	public static final String PRODUTO_FIND_ID = "Produto.findId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private String nome;
	
	@NotNull
	@Column
	private String fabricante;
	
	@Column
	@NotNull
	private long codigoBarras;
	
	@Column
	@NotNull
	private int tamanho;
	
	@Column
	@NotNull
	private String cor;
	
	@Column
	@NotNull
	private int quantidadeMinima;

	@Column
	@NotNull
	private int quantidadeTotal;
	
	@Column
	@NotNull
	private double valorVenda;
	
	@Column
	@NotNull
	private double valorCompra;
	
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
	
	public long getCodigoBarras(){
		return codigoBarras;		
	}
	
	public void setCodigoBarras(long codigoBarras){
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

	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(int quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	
}
