package br.edu.ifgoias.academico.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Curso implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcurso;
	
	@Column(name= "nomecurso", nullable=false)
	private String nomecurso;
	
	@ManyToMany
	@JoinTable(name = "aluno_curso",
				joinColumns = @JoinColumn(name = "idcurso"),
				inverseJoinColumns = @JoinColumn(name="idaluno"))
	private List<Aluno> listaAluno = new ArrayList<>();
	
	public Curso() {
		
	}

	public Curso(Integer idcurso, String nomecurso) {
		this.idcurso = idcurso;
		this.nomecurso = nomecurso;
	}

	
	public List<Aluno> getListaAluno() {
		return listaAluno;
	}

	public void adicionarAluno (Aluno a) {
		
		if (!listaAluno.contains(a)) {
			listaAluno.add(a);
			a.adicionarCurso(this);
		}
		
	}

	public Integer getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public String getNomecurso() {
		return nomecurso;
	}

	public void setNomecurso(String nomecurso) {
		this.nomecurso = nomecurso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idcurso, nomecurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(idcurso, other.idcurso) && Objects.equals(nomecurso, other.nomecurso);
	}

	@Override
	public String toString() {
		return "Curso [idcurso=" + idcurso + ", nomecurso=" + nomecurso + "]";
	}

	
}
