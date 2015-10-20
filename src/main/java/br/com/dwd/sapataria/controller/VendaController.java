package br.com.dwd.sapataria.controller;


import br.com.dwd.sapataria.model.Venda;
import br.com.dwd.sapataria.task.VendaTask;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Cristiano on 19/10/15.
 */
@Named
@ViewScoped
public class VendaController implements Serializable {

	private List<Venda> vendas = new ArrayList<>();

	@Inject
	private VendaTask task;


	@PostConstruct
	public void init() {
		refresh();
	}

	public void refresh() {
		vendas = task.findAll();
	}

	public List<Venda> getVendas() {
		return vendas;
	}


	public void vender() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vendeu ! ! !", "Vendeu ! ! !"));
		//FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		Faces.redirect("/sapataria/restrito/venda/lista.xhtml");
	}


}
