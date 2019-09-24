package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Professor;
import br.com.senac.repository.AlunoRepository;
import br.com.senac.repository.ProfessorRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;
	
	public List<Professor> buscarTodosProfessores(){
		return professorRepository.findAll();
	}
	
	public Professor salvar(Professor professor) {
		return professorRepository.save(professor);
	}
	
	public Professor buscaPorId(Integer id) throws ObjectNotFoundException {
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.orElseThrow(() -> new ObjectNotFoundException("Professor não encontrado, id "+id));
	}
	
	public Professor salvarAlteracao(Professor profAlterado) throws ObjectNotFoundException {
		Professor prof = buscaPorId(profAlterado.getId());
		prof.setId(profAlterado.getId());
		prof.setNome(profAlterado.getNome());
		return salvar(prof);
	}
	
	public void excluir(Integer id) {
		professorRepository.deleteById(id);
	}
}
