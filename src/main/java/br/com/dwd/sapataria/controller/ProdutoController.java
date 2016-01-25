package br.com.dwd.sapataria.controller;

import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.qualify.HttpParam;
import br.com.dwd.sapataria.task.ProdutoTask;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@Named
@ViewScoped
public class ProdutoController extends Controller implements Serializable {

	private static final String LISTA = "/sapataria/restrito/produto/lista.xhtml";

	private Produto produto;

	@Inject
	private ProdutoTask task;

	@Inject
	@HttpParam("id-produto")
	private transient Optional<String> idSelecionado;

	@PostConstruct
	public void init() {
		produto = idSelecionado.map(id -> Long.valueOf(id)).map(id -> task.findById(id)).orElse(new Produto());
	}

	public String salvar() throws IOException {
		String retorno = null;
		// verificar se é um novo produto ou uma atualizacao
		if (produto.getId() != null) {
			if (task.findbyCodigo(produto.getCodigo()) == null) {
				task.update(produto);
				messageSucess(getFacesContext(), "Salvo", "Dados Salvo Com Sucesso!");
				retorno = "lista.xhtml?faces-redirect=true";
			} else {
				messageError(getFacesContext(), "Código inválido!", "Existe um produto cadastrado com esse código.");
			}
	}else

	{
		if (task.findByName(produto.getNome()) == null) {
			task.add(produto);
			messageSucess(getFacesContext(), "Salvo", "Dados Salvo Com Sucesso!");
			retorno = "lista.xhtml?faces-redirect=true";
		} else {
			messageError(getFacesContext(), "Nome Inválido", "Existe um produto cadastrado com esse nome.");
		}
	} return retorno;

	}

	private FacesContext getFacesContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		return context;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
