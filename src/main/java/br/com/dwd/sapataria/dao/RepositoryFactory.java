package br.com.dwd.sapataria.dao;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Cristiano on 06/10/15.
 */
@Dependent
public class RepositoryFactory {

	@Inject
	private EntityManager em;

	@Produces
	@Dependent
	@Default
	public <T> Repository<T> getRepository(InjectionPoint point){
		ParameterizedType type = (ParameterizedType) point.getType();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		return new JPARepository<>(em,clazz);
	}
}
