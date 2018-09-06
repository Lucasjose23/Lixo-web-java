package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Usuario;
import DAO.JPAUtil;

public class UsuarioDAO {
	public void create(Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
		em.close();
	}
	
	public Usuario readId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Usuario consultaUsuario = em.find(Usuario.class, id);
		em.close();
		return consultaUsuario;
	}
	
	public List<Usuario> getReadAll(){
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("from Usuario");
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}
	
	//Verifica Login e senha
	public Usuario getUsuario(String nomeUsuario, String senha) {
		
		try {
		EntityManager em = JPAUtil.getEntityManager();                                                
		
		Usuario consultaUsuario = (Usuario) em.createQuery("SELECT l from Usuario l where l.login = :login and l.senha = :senha")
				.setParameter("login", nomeUsuario)
				.setParameter("senha", senha).getSingleResult();
		return consultaUsuario;
				
		}catch (NoResultException e) {
			return null;
		}
	}
	
	//Verifica Login e senha
	public Usuario verificaUsuario(String nomeUsuario) {
		
		try {
		EntityManager em = JPAUtil.getEntityManager();                                                
		
		Usuario consultaUsuario = (Usuario) em.createQuery("SELECT l from Usuario l where u.login = :login")
				.setParameter("login", nomeUsuario).getSingleResult();
		return consultaUsuario;
				
		}catch (NoResultException e) {
			return null;
		}
	}

}
