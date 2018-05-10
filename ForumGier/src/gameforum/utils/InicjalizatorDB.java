package gameforum.utils;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import gameforum.dao.TematyDAO;
import gameforum.dao.UzytkownicyDAO;
import gameforum.dao.WpisyDAO;

/**
 * Application Lifecycle Listener implementation class InicjalizatorDB
 *
 */
@WebListener
public class InicjalizatorDB implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public InicjalizatorDB() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
         EntityManager em = DBConfig.createEntityManager();
         UzytkownicyDAO uzytkownicyDAO = new UzytkownicyDAO(em);
         TematyDAO tematyDAO = new TematyDAO(em);
         WpisyDAO wpisyDAO = new WpisyDAO(em);
         ServletRequest req = sre.getServletRequest();
         req.setAttribute("wpisyDAO", wpisyDAO);
         req.setAttribute("tematyDAO", tematyDAO);
         req.setAttribute("uzytkownicyDAO", uzytkownicyDAO);
    }
	
}
