package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.model.Usuario;
import br.com.dwd.sapataria.qualify.HttpParam;
import br.com.dwd.sapataria.task.UsuarioTask;

import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String LISTA = "/sapataria/restrito/funcionario/lista.xhtml";

	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioTask task;
	@Inject
	@HttpParam("id-usuario")
	private transient Optional<String> idSelecionado;

	@PostConstruct
	public void init() {
		usuario = idSelecionado.map(id -> Long.valueOf(id)).map(id -> task.findById(id)).orElse(new Usuario());
	}
	
	public void verificar(){
		try {
			Long id = usuario.getId();
			Usuario usuarioFindId= task.findById(id);
			if (usuarioFindId != null) {	
				this.usuario = update(usuario);
			} 
		} catch (Exception e) {
			this.verificarEmail(usuario);
		}
	}
	
	public Usuario update(Usuario usuario) {
		try {
			task.update(usuario);
			System.out.println("Usuário atualizado");
			Faces.redirect(LISTA);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void verificarEmail(Usuario usuario){
		try{
			String email = usuario.getEmail();
			Usuario usuarioFindEmail = task.findByEmail(email);
			if(usuarioFindEmail == null){
				this.salvar(usuario);
			}else{
				System.out.println("Já tem um cadastro com esse email");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salvar(Usuario usuario)  {
		try {
			task.add(usuario);
			Faces.redirect(LISTA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void listar() {

	}

}
