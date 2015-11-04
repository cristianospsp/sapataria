package br.com.dwd.sapataria.qualify;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import javax.inject.Qualifier;


	@Qualifier
	@Retention(RUNTIME)
	@Target({TYPE, METHOD, PARAMETER})
	public @interface HttpParam{
		@Nonbinding public String value() default"";
	}
