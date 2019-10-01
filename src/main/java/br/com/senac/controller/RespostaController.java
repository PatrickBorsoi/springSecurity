package br.com.senac.controller;

import br.com.senac.domain.Resposta;
import br.com.senac.service.PerguntaService;
import br.com.senac.service.ProfessorService;
import br.com.senac.service.RespostaService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("resposta")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping("/listarRespostas")
    public ModelAndView listaRespostas() {
        ModelAndView mv = new ModelAndView("resposta/paginaListaResposta");
        mv.addObject("respostas", respostaService.buscarTodosRespostas());
        return mv;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrarResposta() {
        ModelAndView mv = new ModelAndView("resposta/cadastraResposta");
        mv.addObject("resposta", new Resposta());
        mv.addObject("perguntas", perguntaService.buscarTodosPerguntas());
        mv.addObject("professores", professorService.buscarTodosProfessores());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarResposta(Resposta resposta) {
        respostaService.salvar(resposta);
        return new ModelAndView("redirect:listarRespostas");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterarProf(@PathVariable("id") Integer id) throws ObjectNotFoundException {
        ModelAndView mv = new ModelAndView("resposta/alteraResposta");
        mv.addObject("resposta", respostaService.buscaPorId(id));
        mv.addObject("perguntas", perguntaService.buscarTodosPerguntas());
        mv.addObject("professores", professorService.buscarTodosProfessores());
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Resposta profAlterado) throws ObjectNotFoundException {
        respostaService.salvarAlteracao(profAlterado);
        return new ModelAndView("redirect:/resposta/listarRespostas");
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Integer id) {
        respostaService.excluir(id);
        return new ModelAndView("redirect:/resposta/listarRespostas");
    }

}