package br.com.senac.service;

import br.com.senac.domain.Pergunta;
import br.com.senac.repository.PerguntaRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntaService {
    @Autowired
    PerguntaRepository perguntaRepository;

    public List<Pergunta> buscarTodosPerguntas(){
        return perguntaRepository.findAll();
    }

    public Pergunta salvar(Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }

    public Pergunta buscaPorId(Integer id) throws ObjectNotFoundException {
        Optional<Pergunta> pergunta = perguntaRepository.findById(id);
        return pergunta.orElseThrow(() -> new ObjectNotFoundException("Pergunta não encontrado, id "+id));
    }

    public Pergunta salvarAlteracao(Pergunta perguntaAlterado) throws ObjectNotFoundException {
        Pergunta pergunta = buscaPorId(perguntaAlterado.getId());
        pergunta.setId(perguntaAlterado.getId());
        pergunta.setDescricao(perguntaAlterado.getDescricao());
        pergunta.setResposta(perguntaAlterado.getResposta());
        return salvar(pergunta);
    }

    public void excluir(Integer id) {
        perguntaRepository.deleteById(id);
    }
}