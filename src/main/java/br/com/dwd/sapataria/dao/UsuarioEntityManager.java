package br.com.dwd.sapataria.dao;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Elizeu on 24/10/15.
 */
@Singleton
public class UsuarioEntityManager {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

	private static EntityManager entityManager;
	
	@Produces
	public EntityManager criaEntityManager() {
		entityManager = factory.createEntityManager();
		return entityManager;
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
	
}
