package br.com.hibernate.tarefas.crud;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.hibernate.tarefas.model.Tarefa;

public class AdicionaTarefa {
	
	
	public static void main(String[] args) {
		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao("Arrumar a casa");
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(tarefa);
		manager.getTransaction().commit();
		System.out.println("id da tarefa: "+ tarefa.getId());
		manager.close();
		factory.close();
	}
	
	
}
