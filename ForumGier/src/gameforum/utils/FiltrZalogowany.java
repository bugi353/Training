package gameforum.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameforum.dao.UzytkownicyDAO;
import gameforum.encje.Uzytkownik;

/**
 * Servlet Filter implementation class FiltrZalogowany
 */
@WebFilter("/*")
public class FiltrZalogowany implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		req.setCharacterEncoding("UTF-8");
		String login = req.getRemoteUser();
		if (login != null)
		{
			Uzytkownik u = (Uzytkownik)req.getSession().getAttribute("uzytkownik");
			if (u == null)
			{
				UzytkownicyDAO dao = (UzytkownicyDAO)req.getAttribute("uzytkownicyDAO");
				u = dao.pobierzPoLoginie(login);
				req.getSession().setAttribute("uzytkownik", u);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
