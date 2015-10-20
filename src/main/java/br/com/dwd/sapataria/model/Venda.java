package br.com.dwd.sapataria.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Cristiano on 19/10/15.
 */
@Entity
@NamedQueries(
	 @NamedQuery(
			name = Venda.VENDA_FIND_ALL,
			query = "select v from Venda v"
	 )
)
public class Venda implements Serializable {

	public static final String VENDA_FIND_ALL = "Venda.findAll";
	@Id
	private Long id;
	private String nomeVendedor;
	private LocalDateTime dataVenda;
	private BigDecimal totalVenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public BigDecimal getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(BigDecimal totalVenda) {
		this.totalVenda = totalVenda;
	}
}
