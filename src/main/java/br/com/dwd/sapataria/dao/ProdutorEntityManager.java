package br.com.dwd.sapataria.dao;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Cristiano on 06/10/15.
 */
@Singleton
public class ProdutorEntityManager {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

	@Produces
	public EntityManager criaEntityManager() {
		return factory.createEntityManager();
	}

}
