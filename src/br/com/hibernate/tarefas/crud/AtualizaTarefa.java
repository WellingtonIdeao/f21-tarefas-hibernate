package br.com.hibernate.tarefas.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.hibernate.tarefas.model.Tarefa;

public class AtualizaTarefa {

	public static void main(String[] args) {
		Tarefa tarefa = new Tarefa();
		tarefa.setId(2L); // esse id já existe no BD
		tarefa.setDescricao("Atualizar curso de Inglês");
		tarefa.setFinalizado(false);
		tarefa.setDataFinalizacao(null);
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(tarefa);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
