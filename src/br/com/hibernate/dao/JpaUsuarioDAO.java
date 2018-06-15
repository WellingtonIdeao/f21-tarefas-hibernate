package br.com.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.hibernate.model.Usuario;

@Repository
public class JpaUsuarioDAO implements IdefaultDAO<Usuario> {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void adiciona(Usuario user) {
		manager.persist(user);
		
	}

	@Override
	public List<Usuario> lista() {
		return manager.createQuery("select u from Usuario u").getResultList();
	}

	@Override
	public void altera(Usuario user) {
		manager.merge(user);
		
	}

	@Override
	public Usuario buscaPorId(Long id) {
		return manager.find(Usuario.class,id);
	}

	@Override
	public void remove(Long id) {
		Usuario userRemover = buscaPorId(id); 
		manager.remove(userRemover);
	}

	@Override
	public void finaliza(Long id) {
		
	}
	
	@Override
	public boolean existe(Usuario usuario){
		Query query = manager.createQuery("select u from Usuario as u where u.login=:login and u.senha=:senha");
		query.setParameter("login",usuario.getLogin());
		query.setParameter("senha",usuario.getSenha());
		List<Usuario> users = query.getResultList();
		if(users.size()>0)
		 	return true;
		return false;
	}
}
