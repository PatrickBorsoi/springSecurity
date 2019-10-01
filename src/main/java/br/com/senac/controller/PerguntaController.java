package br.com.senac.controller;

import br.com.senac.domain.Pergunta;
import br.com.senac.service.AlunoService;
import br.com.senac.service.PerguntaService;
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
@RequestMapping("pergunta")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/listarPerguntas")
    public ModelAndView listaPerguntas() {
        ModelAndView mv = new ModelAndView("pergunta/paginaListaPergunta");
        mv.addObject("perguntas", perguntaService.buscarTodosPerguntas());
        return mv;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrarPergunta() {
        ModelAndView mv = new ModelAndView("pergunta/cadastraPergunta");
        mv.addObject("pergunta", new Pergunta());
        mv.addObject("respostas", respostaService.buscarTodosRespostas());
        mv.addObject("alunos", alunoService.buscarTodosAlunos());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarPergunta(Pergunta pergunta) {
        perguntaService.salvar(pergunta);
        return new ModelAndView("redirect:listarPerguntas");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterarProf(@PathVariable("id") Integer id) throws ObjectNotFoundException {
        ModelAndView mv = new ModelAndView("pergunta/alteraPergunta");
        mv.addObject("pergunta", perguntaService.buscaPorId(id));
        mv.addObject("respostas", respostaService.buscarTodosRespostas());
        mv.addObject("alunos", alunoService.buscarTodosAlunos());
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Pergunta profAlterado) throws ObjectNotFoundException {
        perguntaService.salvarAlteracao(profAlterado);
        return new ModelAndView("redirect:/pergunta/listarPerguntas");
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Integer id) {
        perguntaService.excluir(id);
        return new ModelAndView("redirect:/pergunta/listarPerguntas");
    }

}