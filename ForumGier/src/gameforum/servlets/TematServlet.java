package gameforum.servlets;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import gameforum.dao.TematyDAO;
import gameforum.dao.WpisyDAO;
import gameforum.encje.Temat;
import gameforum.encje.Uzytkownik;
import gameforum.encje.Wpis;

/**
 * Servlet implementation class TematServlet
 */
@WebServlet("/temat")
public class TematServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringId = request.getParameter("id");
		if (stringId != null)
		{
			int id = Integer.parseInt(stringId);
			TematyDAO dao = (TematyDAO)request.getAttribute("tematyDAO");
			Temat t = dao.pobierzTemat(id);
			request.setAttribute("temat", t);
			request.getRequestDispatcher("WEB-INF/widok/temat.jsp").forward(request, response);
		}
		else
			response.sendRedirect(request.getContextPath() + "/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tresc = request.getParameter("tresc");
		String stringId = request.getParameter("id");
		if (tresc !=null && stringId !=null)
		{
			int id = Integer.parseInt(stringId);
			WpisyDAO wpisyDAO = (WpisyDAO)request.getAttribute("wpisyDAO");
			TematyDAO tematyDAO	= (TematyDAO)request.getAttribute("tematyDAO");
			Uzytkownik zalogowany = (Uzytkownik)request.getSession().getAttribute("uzytkownik");
			Temat temat = tematyDAO.pobierzTemat(id);
			Wpis wpis = new Wpis();
			wpis.setData(new Timestamp(new Date().getTime()));
			wpis.setTresc(tresc);
			wpis.setUzytkownik(zalogowany);
			wpis.setTemat(temat);
			wpisyDAO.dodajWpis(wpis);
		}
		doGet(request, response);
	}

}
