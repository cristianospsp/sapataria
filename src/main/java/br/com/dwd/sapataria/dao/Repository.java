package br.com.dwd.sapataria.dao;


import javax.enterprise.inject.Vetoed;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Cristiano on 06/10/15.
 */
@Vetoed
public interface Repository<T> {

	T add(T t);

	T update(T t);

	void delete(T t);

	T find(Serializable id);

	List<T> listAll();

	List<T> listBy(String nameQuery, Map<String, Object> params);

	T findBy(String nameQuery, Map<String, Object> params);

	<E> List<E> listOtherResultBy(Class<E> other, String nameQuery, Map<String, Object> params);

	<E> E findOtherResultBy(Class<E> other, String nameQuery, Map<String, Object> params);

	List<?> list(String nameQuery, Map<String, Object> params);
}
