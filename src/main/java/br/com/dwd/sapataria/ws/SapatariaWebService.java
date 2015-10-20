package br.com.dwd.sapataria.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Cristiano on 13/10/15.
 */
@Path("/produto")
public class SapatariaWebService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String dizOla() {
		String teste = "Hello Kebra Barraco ! ! !";
		return teste;
	}

	@GET
	@Path("id/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String produtoById(@PathParam("id") String id) {
		return "Devolver Produto com ID: " + id;
	}

}
