package gameforum.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gameforum.encje.Wpis;

public class WpisyDAO {
	private EntityManager em;
	
	public WpisyDAO(EntityManager em) {
		this.em = em;
	}

	public boolean dodajWpis(Wpis w) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(w);
			et.commit();
			return true;
		} catch (Exception e)
		{
			et.rollback();
			e.printStackTrace();
			return false;
		}
	}
}
