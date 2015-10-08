/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Usuario;
import br.com.dwd.sapataria.task.SecurityTask;
import org.omnifaces.util.Faces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class SecurityController implements Serializable {

	private static final String LOGIN = "/sapataria/login.xhtml";
	private static final String PRINCIPAL = "/sapataria/restrito/principal.xhtml";

	@Inject
	private SecurityTask task;

	private String login;
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void logar() throws IOException {

		//TODO precisa aplicar algum esquema de criptografia na senha, tipo md5

		Usuario usuarioEcontrado = task.findUserByLoginAndPss(login, senha);


		if (usuarioEcontrado != null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("login", usuarioEcontrado);
			Faces.redirect(PRINCIPAL);
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "teste", "teste"));
			Faces.redirect(LOGIN);
		}

	}

	public String logout() throws IOException {
		//TODO falta implementar o LOGOUT

	     /* logger.info("fazendo logout...");
	      currentUser.logout();*/
		//usuarioInfo.reload();
		//Faces.invalidateSession();
		return null; /*LOGIN+"?faces-redirect=true";*/
	}
}
