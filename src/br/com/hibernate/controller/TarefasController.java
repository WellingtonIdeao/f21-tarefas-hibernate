package br.com.hibernate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hibernate.dao.IdefaultDAO;
import br.com.hibernate.model.Tarefa;

@Transactional
@Controller
public class TarefasController {
	
	@Qualifier("jpaTarefaDAO")
	@Autowired
	private IdefaultDAO<Tarefa> dao;
	
	
	@RequestMapping("/adicionaTarefa")
	public String adicionar(@Valid Tarefa tarefa, BindingResult result){
		if(result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
		
	}
	
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
		
	}
	@RequestMapping("listaTarefas")
	public String listar(Model model) {
		model.addAttribute("tarefas", dao.lista());
		return"tarefa/lista";
		
	}
	@ResponseBody
	@RequestMapping("removeTarefa")
	public void remover(Long id) {
		dao.remove(id);
	}
	@RequestMapping("mostraTarefa")
	public String  mostra(Long id, Model model) {
		model.addAttribute("tarefa",dao.buscaPorId(id));
		return "tarefa/mostra";
		
	}
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		dao.altera(tarefa);
		return "redirect:listaTarefas";
		
	}
	@RequestMapping("finalizaTarefa")
	public String finalizar(Long id, Model model) {
		dao.finaliza(id);
		model.addAttribute("tarefa",dao.buscaPorId(id));
		return "tarefa/finalizada";
	}
}
