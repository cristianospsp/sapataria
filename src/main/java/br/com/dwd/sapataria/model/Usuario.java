package br.com.dwd.sapataria.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Cristiano on 06/10/15.
 */
@Entity
@NamedQueries(
	 @NamedQuery(
			name = Usuario.USUARIO_LOGIN,
			query = "select u from Usuario u where u.email = :email and u.senha = :senha"
	 )
)
public class Usuario implements Serializable {

	public static final String USUARIO_LOGIN = "Usuario.login";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String cpf;
	@NotNull
	private String email;
	@NotNull
	private String senha;
	@NotNull
	private String perfil;
	@NotNull
	private String cargo;
	@NotNull
	private boolean cadAtivo;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean getCadAtivo() {
		return cadAtivo;
	}

	public void setCadAtivo(boolean cadAtivo) {
		this.cadAtivo = cadAtivo;
	}

}
