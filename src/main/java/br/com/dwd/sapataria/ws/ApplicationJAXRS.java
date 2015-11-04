package br.com.dwd.sapataria.ws;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cristiano on 14/10/15.
 */
public class ApplicationJAXRS extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(SapatariaWebService.class);
		classes.add(UserSecurityWS.class);
		return classes;
	}

}
