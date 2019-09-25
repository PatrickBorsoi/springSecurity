package br.com.senac.service;

import br.com.senac.domain.Materia;
import br.com.senac.repository.MateriaRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository repository;

    public List<Materia> buscarTodosMaterias(){
        return repository.findAll();
    }

    public Materia salvar(Materia materia) {
        return repository.save(materia);
    }

    public Materia buscaPorId(Integer id) throws ObjectNotFoundException {
        Optional<Materia> materia = repository.findById(id);
        return materia.orElseThrow(() -> new ObjectNotFoundException("Materia não encontrado, id "+id));
    }

    public Materia salvarAlteracao(Materia materiaAlterado) throws ObjectNotFoundException {
        Materia materia = buscaPorId(materiaAlterado.getId());
        materia.setId(materiaAlterado.getId());
        materia.setNome(materiaAlterado.getNome());
        return salvar(materia);
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

}
