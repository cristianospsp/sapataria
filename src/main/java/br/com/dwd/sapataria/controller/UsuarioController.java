package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Usuario;
import br.com.dwd.sapataria.task.UsuarioTask;

import org.omnifaces.util.Faces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String LISTA = "/sapataria/restrito/usuario/lista.xhtml";

	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioTask task;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void salvar()  {
		try {
			task.add(usuario);
			Faces.redirect(LISTA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listar() {

	}

}
