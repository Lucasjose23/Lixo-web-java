package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Empresa;

public class EmpresaDAO {
	public void create(Empresa empresa) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(Empresa empresa) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(empresa);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(Empresa empresa) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(empresa);
		em.getTransaction().commit();
		em.close();
	}
	
	public Empresa readId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Empresa consultaEmpresa = em.find(Empresa.class, id);
		em.close();
		return consultaEmpresa;
	}
	
	public List<Empresa> getReadAll(){
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("from Empresa");
		List<Empresa> empresas = query.getResultList();
		return empresas;
	}
	
	//Verifica Login e senha
		public Empresa getEmpresa(String nomeUsuario, String senha) {
			
			try {
			EntityManager em = JPAUtil.getEntityManager();                                                
			
			Empresa consultaEmpresa = (Empresa) em.createQuery("SELECT l from Empresa l where l.login = :login and l.senha = :senha")
					.setParameter("login", nomeUsuario)
					.setParameter("senha", senha).getSingleResult();
			return consultaEmpresa;
					
			}catch (NoResultException e) {
				return null;
			}
		}
		
		//Verifica Login e senha
		public Empresa verificaEmpresa(String nomeUsuario) {
			
			try {
			EntityManager em = JPAUtil.getEntityManager();                                                
			
			Empresa consultaEmpresa = (Empresa) em.createQuery("SELECT l from Empresa l where u.login = :login")
					.setParameter("login", nomeUsuario).getSingleResult();
			return consultaEmpresa;
					
			}catch (NoResultException e) {
				return null;
			}
		}
}
