package br.com.senac.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Aluno implements Serializable{
	
	private static final long serialVersionUID = -8525398130973019510L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@OneToMany(
			mappedBy = "aluno"
	)
	private List<Pergunta> perguntas;

	@Column(name="nomeAluno")
	private String nome;			
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
