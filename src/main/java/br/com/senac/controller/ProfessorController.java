package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Professor;
import br.com.senac.service.ProfessorService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("professor")
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;

	@GetMapping("/listarProfessores")
	public ModelAndView listaProfessores() {
		ModelAndView mv = new ModelAndView("professor/paginaListaProfessor");
		mv.addObject("professores", professorService.buscarTodosProfessores());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarProfessor() {
		ModelAndView mv = new ModelAndView("professor/cadastraProfessor");
		mv.addObject("professor", new Professor());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarProfessor(Professor professor) {
		professorService.salvar(professor);
		return new ModelAndView("redirect:listarProfessores");
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarProf(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("professor/alteraProfessor");
		mv.addObject("professor", professorService.buscaPorId(id));
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Professor profAlterado) throws ObjectNotFoundException {
		professorService.salvarAlteracao(profAlterado);
		return new ModelAndView("redirect:/professor/listarProfessores");
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Integer id) {
		professorService.excluir(id);
		return new ModelAndView("redirect:/professor/listarProfessores");
	}
}
