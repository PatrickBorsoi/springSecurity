package br.com.senac.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Professor implements Serializable{
	
	private static final long serialVersionUID = 1888891549045983467L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;

	@ManyToMany
	@JoinTable(
			name ="professor_materia",
			joinColumns = { @JoinColumn(name = "professor_id") },
			inverseJoinColumns = { @JoinColumn(name = "materia_id") }
	)
	private List<Materia> materiaList;

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

	public List<Materia> getMateriaList() {
		return materiaList;
	}

	public void setMateriaList(List<Materia> materiaList) {
		this.materiaList = materiaList;
	}
}
