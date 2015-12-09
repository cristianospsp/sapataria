package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Usuario;
import br.com.dwd.sapataria.task.UsuarioTask;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UsuarioFiltroController extends Controller implements Serializable {

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<>();

	@Inject
	private UsuarioTask task;

	public void buscarUsuario() {
		usuarios = task.listByName(usuario.getNome());
	}

	public void delete(Usuario usuario) {
		task.delete(usuario);
		buscarUsuario();
		this.usuario = new Usuario();
		messageSucess(getFacesContext(), "Sucesso !", "Usuário Excluído.");
	}

	private FacesContext getFacesContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		return context;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
