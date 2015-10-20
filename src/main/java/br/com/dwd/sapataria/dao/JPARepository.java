package br.com.dwd.sapataria.dao;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * Created by cristiano on 6/10/15.
 */
public class JPARepository<T> implements Repository<T> {

	private EntityManager em = ProdutorEntityManager.getEntityManager();
	private Class<T> clazz;

	public JPARepository(EntityManager em) {
		this.em = em;
		this.clazz = (Class<T>) ((ParameterizedType)
			 getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public JPARepository(EntityManager em, Class<T> clazz) {
		this.em = em;
		this.clazz = clazz;
	}

	@Transactional(REQUIRED)
	@Override
	public T add(T t) {
		em.persist(t);
		return t;
	}

	@Transactional(REQUIRED)
	@Override
	public T update(T t) {
		return em.merge(t);
	}

	@Transactional(REQUIRED)
	@Override
	public void delete(T t) {
		em.remove(em.merge(t));
	}

	@Transactional(SUPPORTS)
	@Override
	public T find(Serializable id) {
		return em.find(clazz, id);
	}

	@Transactional(SUPPORTS)
	@Override
	public List<T> listAll() {
		UaiCriteria<T> criteria = getQueryCriteria();
		return criteria.getResultList();
	}

	@Transactional(SUPPORTS)
	@Override
	public List<T> listBy(String nameQuery, Map<String, Object> params) {
		final TypedQuery<T> query = em.createNamedQuery(nameQuery, clazz);
		applyParams(params, query);
		return query.getResultList();
	}

	@Transactional(SUPPORTS)
	@Override
	public T findBy(String nameQuery, Map<String, Object> params) {
		final TypedQuery<T> query = em.createNamedQuery(nameQuery, clazz);
		applyParams(params, query);
		return query.getSingleResult();
	}

	@Transactional(SUPPORTS)
	@Override
	public <E> List<E> listOtherResultBy(Class<E> other, String nameQuery, Map<String, Object> params) {
		final TypedQuery<E> query = em.createNamedQuery(nameQuery, other);
		applyParams(params, query);
		return query.getResultList();
	}

	@Transactional(SUPPORTS)
	@Override
	public <E> E findOtherResultBy(Class<E> other, String nameQuery, Map<String, Object> params) {
		final TypedQuery<E> query = em.createNamedQuery(nameQuery, other);
		applyParams(params, query);
		return query.getSingleResult();
	}

	@Transactional(SUPPORTS)
	@Override
	public List<?> list(String nameQuery, Map<String, Object> params) {
		final Query query = em.createNamedQuery(nameQuery);
		applyParams(params, query);
		return query.getResultList();
	}

	private void applyParams(Map<String, Object> params, Query query) {
		params.forEach((k, v) -> query.setParameter(k, v));
	}

	protected UaiCriteria<T> getQueryCriteria() {
		return UaiCriteriaFactory.createQueryCriteria(em, clazz);
	}

	protected EntityManager getEntityManager() {
		return em;
	}

	protected <E> UaiCriteria<E> getQueryCriteria(Class<E> eClazz) {
		return UaiCriteriaFactory.createMultiSelectCriteria(em, eClazz);
	}

}
