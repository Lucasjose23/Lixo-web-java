package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Equipamento;
import modelo.LocalDescarte;

public class LocalDescarteDAO {

	public void create(LocalDescarte localDescarte) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(localDescarte);
		em.getTransaction().commit();
		em.close();
	}

	public void update(LocalDescarte localDescarte) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(localDescarte);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(LocalDescarte localDescarte) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(localDescarte);
		em.getTransaction().commit();
		em.close();
	}

	public LocalDescarte readId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		LocalDescarte consultaLocalDescarte = em.find(LocalDescarte.class, id);
		em.close();
		return consultaLocalDescarte;
	}

	public List<LocalDescarte> getReadAll() {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("from LocalDescarte");
		List<LocalDescarte> localDescartes = query.getResultList();
		return localDescartes;
	}


	public List<LocalDescarte> getReadAllId(int id) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("select l from LocalDescarte as l "
					+ "inner join l.empresa as empresa " + "where empresa.id=:empresaid").setParameter("empresaid", id);
			List<LocalDescarte> locais = query.getResultList();
			return locais;

		} catch (Exception e) {
			return null;
		}
	}
}
