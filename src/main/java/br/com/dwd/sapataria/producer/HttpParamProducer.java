package br.com.dwd.sapataria.producer;

import br.com.dwd.sapataria.qualify.HttpParam;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import java.util.Optional;

/**
 * Created by Cristiano on 04/11/15.
 */
@Dependent
public class HttpParamProducer {

	@Produces
	@HttpParam()
	public Optional<String> getParameter(InjectionPoint point) {
		try {
			String value = point.getAnnotated().getAnnotation(HttpParam.class).value();
			return Optional.ofNullable(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(value));
		} catch (Exception e) {
			e.printStackTrace();//TODO Criar um Observer para exceptions pra logar exceptions
			return Optional.empty();
		}
	}


}
