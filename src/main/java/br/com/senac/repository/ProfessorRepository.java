package br.com.senac.repository;

import org.springframework.stereotype.Repository;

import br.com.senac.domain.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

}
