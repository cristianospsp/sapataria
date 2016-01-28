package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Usuario;
import br.com.dwd.sapataria.controller.UsuarioController;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Convert;
import javax.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cristiano on 07/10/15.
 */
@Named
public class SecurityTask {

	@Inject
	private Repository<Usuario> usuarioRepository;

	public Usuario findUserByLoginAndPss(String login, String senha) {
		Map<String, Object> param = new HashMap<>();
		param.put("email", login);
		param.put("senha", UsuarioController.criptografiaMd5(senha));

		List<Usuario> usuarios1 = usuarioRepository.listAll();

		List<Usuario> usuarios = usuarioRepository.listBy(Usuario.USUARIO_LOGIN, param);
		return usuarios.stream().findFirst().orElse(null);
	}
	
}
