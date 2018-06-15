package br.com.hibernate.tarefas.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.hibernate.tarefas.model.Tarefa;

public class CarregarTarefa {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		Tarefa encontrada = manager.find(Tarefa.class, 3L);
		System.out.println(encontrada.getDescricao());
		manager.close();
		factory.close();
	}

}
