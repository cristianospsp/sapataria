package br.com.dwd.sapataria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeVendedor;
	private LocalDateTime dataVenda;
	private BigDecimal totalVenda;

	public Venda() {
	}

	public Venda(String nomeVendedor, Double totalVenda) {
		this.nomeVendedor = nomeVendedor;
		this.totalVenda = new BigDecimal(totalVenda);
		this.dataVenda = LocalDateTime.now();
	}

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

	public Date getDataVendaToDate() {
		LocalDateTime now = dataVenda.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date dateFromOld = Date.from(instant);
		return dateFromOld;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Venda)) return false;
		Venda venda = (Venda) o;
		return Objects.equals(getNomeVendedor(), venda.getNomeVendedor()) &&
			 Objects.equals(getDataVenda(), venda.getDataVenda()) &&
			 Objects.equals(getTotalVenda(), venda.getTotalVenda());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNomeVendedor(), getDataVenda(), getTotalVenda());
	}
}
