package gameforum.servlets;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameforum.dao.UzytkownicyDAO;
import gameforum.encje.Uzytkownik;

/**
 * Servlet implementation class RejestracjaServlet
 */
@WebServlet("/rejestracja")
public class RejestracjaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/widok/rejestracja.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String haslo = request.getParameter("haslo");
		String haslo2 = request.getParameter("haslo2");
		if (login != null && haslo != null && haslo2 !=null && haslo.equals(haslo2) && !"".equals(haslo))
		{
			UzytkownicyDAO dao = (UzytkownicyDAO)request.getAttribute("uzytkownicyDAO");
			try
			{
				dao.pobierzPoLoginie(login);
				request.setAttribute("blad", "Taki login już istnieje!");
				doGet(request,response);
				return;
			}
			catch (NoResultException nre)
			{
				Uzytkownik u = new Uzytkownik();
				u.setLogin(login);
				u.setHaslo(haslo);
				if (dao.dodajUzytkownika(u))
				{
					response.sendRedirect(request.getContextPath() + "/index");
				}
				else
					request.setAttribute("blad", "Błąd danych logowania!");
			}
		}
		else {
			request.setAttribute("blad", "Błąd ddanych logowania!");
			doGet(request, response);
		}
	}

}
