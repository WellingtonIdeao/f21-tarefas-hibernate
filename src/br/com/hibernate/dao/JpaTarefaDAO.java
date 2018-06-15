package br.com.hibernate.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.hibernate.model.Tarefa;

@Repository
public class JpaTarefaDAO implements IdefaultDAO<Tarefa>{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void adiciona(Tarefa tarefa) {
			manager.persist(tarefa);
	}
	
	@Override
	public List<Tarefa> lista(){
		return manager.createQuery("select t from Tarefa t").getResultList();
	}
	
	@Override
	public void altera(Tarefa tarefa) {
		manager.merge(tarefa);
	}
	
	@Override
	public Tarefa buscaPorId(Long id) {
		return manager.find(Tarefa.class,id);
		
	}
	@Override
	public void remove(Long id) {
		Tarefa tarefaRemover = buscaPorId(id); 
		manager.remove(tarefaRemover);
	}
	@Override
	public void finaliza(Long id) {
		Tarefa tarefa = buscaPorId(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		manager.merge(tarefa);
		
	}

	@Override
	public boolean existe(Tarefa t) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
