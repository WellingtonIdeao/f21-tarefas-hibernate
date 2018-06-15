package br.com.hibernate.tarefas.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.hibernate.tarefas.model.Tarefa;

public class RemoveTarefa {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Tarefa encontrada = manager.find(Tarefa.class,3L);
		manager.remove(encontrada);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}

}
