package br.com.hibernate.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hibernate.dao.IdefaultDAO;
import br.com.hibernate.model.Usuario;

@Transactional
@Controller
public class LoginController {
	
	@Qualifier("jpaUsuarioDAO")
	@Autowired
	private IdefaultDAO<Usuario> dao;
	
	@RequestMapping("loginForm")	
	public String loginForm() {
		return "formulario-login";
	}
	@RequestMapping("efetuaLogin")	
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		if(dao.existe(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
			
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
		
	}
}
