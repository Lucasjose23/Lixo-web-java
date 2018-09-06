package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Endereco;
import modelo.Equipamento;
import modelo.LocalDescarte;

public class EnderecoDAO {

	public void create(Endereco endereco) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(endereco);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Endereco endereco) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(endereco);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(Endereco endereco) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(endereco);
		em.getTransaction().commit();
		em.close();
	}

	public Endereco getReadId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Endereco consultaEndereco = em.find(Endereco.class, id);
		em.close();
		return consultaEndereco;
	}

	public List<Endereco> getReadAll() {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("from Endereco");
		List<Endereco> enderecos = query.getResultList();
		return enderecos;
	}


	public Endereco getReadAllId(int id) {
		
			EntityManager em = JPAUtil.getEntityManager();
			Endereco endereco = (Endereco) em.createQuery("select e from Endereco as e "
					+ "inner join e.localDescarte as localDescarte " + "where localDescarte.id=:localid").setParameter("localid", id).getSingleResult();
			return endereco;
	}
}
