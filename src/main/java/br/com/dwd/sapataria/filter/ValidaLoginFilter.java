package br.com.dwd.sapataria.filter;

import br.com.dwd.sapataria.model.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Cristiano on 07/10/15.
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class ValidaLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		//HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		String uri = ((HttpServletRequest) request).getRequestURI();

		Usuario usuarioTmp = (Usuario) session.getAttribute("login");

		try {
			if ((uri.indexOf("restrito") > 0) && (usuarioTmp == null)) {
				String url = "/login.xhtml";
				request.getRequestDispatcher(url).forward(request, response);
				return;
			} else
				chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}
}
