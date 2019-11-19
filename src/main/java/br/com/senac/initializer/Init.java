package br.com.senac.initializer;

import br.com.senac.domain.Usuario;
import br.com.senac.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.senac.domain.Aluno;
import br.com.senac.domain.Professor;
import br.com.senac.service.AlunoService;
import br.com.senac.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoService alunoService;

	@Autowired
	ProfessorService professorService;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Professor prof1 =  new Professor();
		prof1.setNome("Saramargo");
		professorService.salvar(prof1);

		Aluno aluno1 = new Aluno();
		aluno1.setNome("Lucas");
		aluno1.setProfessor(prof1);
		alunoService.salvar(aluno1);

		Aluno aluno2 = new Aluno();
		aluno2.setNome("Bruno");
		aluno2.setProfessor(prof1);
		alunoService.salvar(aluno2);


		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setNomeCompleto("Admin");
		usuario.setSenha(new BCryptPasswordEncoder().encode("123"));
		usuarioRepository.save(usuario);



	}
}
