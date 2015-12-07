package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Produto;
import br.com.dwd.sapataria.model.Usuario;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class UsuarioTask implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Repository<Usuario> repository;

	//private EntityManager entitymanager = ProdutorEntityManager.getEntityManager();

	@Transactional
	public Usuario add(Usuario usuario) {
		usuario = repository.add(usuario);
		return usuario;
	}
	@Transactional
	public void delete(Usuario usuario) {
		repository.delete(repository.find(usuario.getId()));
	}
	public List<Usuario> listAll() {
		List<Usuario> usuarios = repository.listAll();
		return usuarios;
	}

	public List<Usuario> listByName(String nome) {
		Map<String, Object> param = new HashMap<>();
		param.put("nome", "%" + nome + "%");
		return repository.listBy(Usuario.USUARIO_LIST_NAME, param);
	}

	public Usuario findById(long id) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		return repository.findBy(Usuario.USUARIO_FIND_ID, param);
	}

	public Usuario findByName(String name) {
		Map<String, Object> param = new HashMap<>();
		param.put("nome", name);
		try {
			return repository.findBy(Usuario.USUARIO_FIND_NAME, param);
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Usuario update(Usuario usuario) {
		usuario = repository.update(usuario);
		return usuario;
	}

	public List<Usuario> listAvisos() {
		Map<String, Object> param = new HashMap<>();
		//param.put("quantidadeMinima", );
		return repository.listBy(Usuario.USUARIO_LIST_NAME, param);
	}

	public List<Usuario> buscarUSuario(Usuario usuario) {
		Map<String, Object> param = new HashMap<>();
		param.put("nome", usuario.getNome());
		return repository.listBy(Usuario.USUARIO_FIND_BY_NOME, param);
	}

	public List<Usuario> findAll() {
		return repository.listAll();
	}

	/*public Repository<Usuario> getRepository() {
		return repository;
	}

	public void setRepository(Repository<Usuario> repository) {
		this.repository = repository;
	}*/
}
