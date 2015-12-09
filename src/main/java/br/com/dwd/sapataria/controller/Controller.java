package br.com.dwd.sapataria.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by Cristiano on 08/12/15.
 */
public class Controller {


	public void messageSucess(FacesContext context, String titulo, String message) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, message));
	}

	public void messageError(FacesContext context, String titulo, String message) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, message));
	}

	public void messageFatal(FacesContext context, String titulo, String message) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, message));
	}

	public void messageWarn(FacesContext context, String titulo, String message) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, message));
	}

}
