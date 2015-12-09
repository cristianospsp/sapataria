package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Usuario;
import br.com.dwd.sapataria.qualify.HttpParam;
import br.com.dwd.sapataria.task.UsuarioTask;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Named
@ViewScoped
public class UsuarioController extends Controller implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	@Inject
	private UsuarioTask task;
	@Inject
	@HttpParam("id-usuario")
	private transient Optional<String> idSelecionado;

	@PostConstruct
	public void init() {
		usuario = idSelecionado.map(id -> Long.valueOf(id)).map(id -> task.findById(id)).orElse(new Usuario());
	}

	public String salvar() {
		String retorno = null;
		if (usuario.getId() != null) {
			task.update(usuario);
			messageSucess(getFacesContext(), "Salvo", "Dados Salvo Com Sucesso!");
			retorno = "lista.xhtml?faces-redirect=true";
		} else {
			if (task.findByEmail(usuario.getEmail()) == null) {
				task.add(usuario);
				messageSucess(getFacesContext(), "Salvo", "Dados Salvo Com Sucesso!");
				retorno = "lista.xhtml?faces-redirect=true";
			} else {
				messageError(getFacesContext(), "E-mail Inválido", "Existe um usuário cadastrado com esse e-mail.");
			}
		}

		return retorno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private FacesContext getFacesContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		return context;
	}

}
