package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Equipamento;

public class EquipamentoDAO {
	
	public void create(Equipamento equipamento) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(equipamento);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(Equipamento equipamento) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(equipamento);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(Equipamento equipamento) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		equipamento = em.merge(equipamento);
		em.remove(equipamento);
		em.getTransaction().commit();
		em.close();
	}
	
	public Equipamento readId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Equipamento consultaEquipamento = em.find(Equipamento.class, id);
		em.close();
		return consultaEquipamento;
	}
	
	public List<Equipamento> getReadAll(){
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("from Equipamento");
		List<Equipamento> equipamentos = query.getResultList();
		return equipamentos;
	}
	
	public List<Equipamento> getReadAllId(int id) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("select e from Equipamento as e "
					+ "inner join e.usuario as usuario " + "where usuario.id=:usuarioid").setParameter("usuarioid", id);
			List<Equipamento> equipamentos = query.getResultList();
			return equipamentos;

		} catch (Exception e) {
			return null;
		}
	}
	

}
