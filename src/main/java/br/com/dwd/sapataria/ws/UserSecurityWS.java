package br.com.dwd.sapataria.ws;

import br.com.dwd.sapataria.model.Usuario;
import br.com.dwd.sapataria.task.SecurityTask;
import com.google.gson.Gson;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Cristiano on 29/10/15.
 */
@Path("/security")
public class UserSecurityWS {

	@Inject
	private SecurityTask task;

	@GET
	@Path("login/{login}/senha/{senha}")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@PathParam("login") String login, @PathParam("senha") String senha) {

		Usuario usuarioEcontrado = task.findUserByLoginAndPss(login, senha);

		if (usuarioEcontrado == null)
			return null;

		String usuarioJson = new Gson().toJson(usuarioEcontrado);

		return usuarioJson;
	}

}
