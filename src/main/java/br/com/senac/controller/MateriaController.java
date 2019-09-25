package br.com.senac.controller;

import br.com.senac.domain.Materia;
import br.com.senac.service.MateriaService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/listarMaterias")
    public ModelAndView listaTodosMateria() {
        ModelAndView mv = new ModelAndView("materia/paginaListaMaterias");
        mv.addObject("materias", materiaService.buscarTodosMaterias());
        return mv;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrarMateria() {
        ModelAndView mv = new ModelAndView("materia/cadastraMateria");
        mv.addObject("materia", new Materia());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarMateria(Materia materia) {
        materiaService.salvar(materia);
        return new ModelAndView("redirect:listarMaterias");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterarMateria(@PathVariable("id") Integer idMateria) throws ObjectNotFoundException {
        ModelAndView mv = new ModelAndView("materia/alteraMateria");
        mv.addObject("materia", materiaService.buscaPorId(idMateria));
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Materia materiaAlterado) throws ObjectNotFoundException {
        materiaService.salvarAlteracao(materiaAlterado);
        return new ModelAndView("redirect:listarMaterias");
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluirMateria(@PathVariable("id") Integer id) {
        materiaService.excluir(id);
        return new ModelAndView("redirect:/materia/listarMaterias");
    }

}
