package br.com.dwd.sapataria.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.deltaspike.core.api.projectstage.ProjectStage.Production;

import java.io.Serializable;

/**
 * Created by Cristiano on 02/10/15.//
 */
@Entity
@Table
public class Produto implements Serializable {

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
	private int qtdMinima;

	@Column
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

	public int getQtdMinima() {
		return qtdMinima;
	}

	public void setQtdMinima(int qtdMinima) {
		this.qtdMinima = qtdMinima;
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
	
}
