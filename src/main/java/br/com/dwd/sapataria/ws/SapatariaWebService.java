package br.com.dwd.sapataria.ws;


import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.task.ProdutoTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

/**
 * Created by Cristiano on 13/10/15.
 */
@Path("/produto")
public class SapatariaWebService {

	@Inject
	private ProdutoTask task;

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String dizOla() {
		return "Hello Teste ! ! !";
	}

	@GET
	@Path("id/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String produtoById(@PathParam("id") String id) {
		return "Devolver Produto com ID: " + id;
	}


	@GET
	@Path("codigo/{codigo}")
	@Produces(MediaType.TEXT_PLAIN)
	public String produtoByCodigo(@PathParam("codigo") String codigo) {

		Produto produto = (codigo != null && !codigo.isEmpty()) ? task.findById(Long.valueOf(codigo)) : new Produto();

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("fabricante", produto.getFabricante());
			jsonObject.put("nome", produto.getNome());
			jsonObject.put("cor", produto.getCor());
			jsonObject.put("tamanho", produto.getTamanho());
			jsonObject.put("vlrVenda", produto.getValor());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new Gson().toJson(produto);
	}


	@GET
	@Path("all")
	@Produces(MediaType.TEXT_PLAIN)
	public String allProducts() {
		/*		List<Produto> prods = new Gson().fromJson(new Gson().toJson(produtos), new TypeToken<List<Produto>>(){}.getType());*/
		return new Gson().toJson(task.findAll());
	}


}
