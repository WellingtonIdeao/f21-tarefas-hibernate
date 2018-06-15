package br.com.hibernate.tarefas.crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.hibernate.tarefas.model.Tarefa;

public class ConsultaJpql {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		Query pesquisa = manager.createQuery("select t from Tarefa as t where t.finalizado = true");
		List<Tarefa> tarefas = pesquisa.getResultList();
		for(Tarefa t: tarefas) {
			System.out.println(t.getDescricao());
		}
		manager.close();
		factory.close();
		
	}

}
