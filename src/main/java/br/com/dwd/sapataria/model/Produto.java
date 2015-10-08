package br.com.dwd.sapataria.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Cristiano on 02/10/15.
 */
@Entity
public class Produto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome;

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
}
