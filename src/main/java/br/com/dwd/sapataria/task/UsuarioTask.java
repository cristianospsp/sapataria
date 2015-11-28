package br.com.dwd.sapataria.task;

import br.com.dwd.sapataria.dao.Repository;
import br.com.dwd.sapataria.model.Usuario;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;

@Named
public class UsuarioTask implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Repository<Usuario> repository;

	//private EntityManager entitymanager = ProdutorEntityManager.getEntityManager();

	@Transactional
	public Usuario add(Usuario usuario) {
	/*	EntityTransaction transaction = entitymanager.getTransaction();
		transaction.begin();
		entitymanager.persist(usuario);
		transaction.commit();*/
		repository.add(usuario);
		return usuario;
	}

	/*public Repository<Usuario> getRepository() {
		return repository;
	}

	public void setRepository(Repository<Usuario> repository) {
		this.repository = repository;
	}*/
}
