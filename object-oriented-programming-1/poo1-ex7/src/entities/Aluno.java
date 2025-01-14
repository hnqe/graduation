package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aluno {

	private String matricula;
	private String nome;
	private List<Turma>  listaTurma = new ArrayList<>();
	private List<Orientador> orientadores = new ArrayList<>();
	
	public Aluno(String matricula, String nome, Orientador orientador) {
		this.matricula = matricula;
		this.nome = nome;
		this.addOrientador(orientador);
	}
	
	public Aluno(String matricula, String nome, Turma turma, Orientador orientador) {
		this.matricula = matricula;
		this.nome = nome;
		this.addTurma(turma);
		this.addOrientador(orientador);
	}


	public void addTurma(Turma turma) {
		if (turma != null && !listaTurma.contains(turma) ) {
			listaTurma.add(turma);
			if (!turma.getListaAluno().contains(this))
			     turma.addAluno(this);
		}		
	}
	
	public boolean addOrientador(Orientador orientador) {
	    if (orientador != null && !orientadores.contains(orientador) && orientadores.size() < 2) {
	        orientadores.add(orientador);
	        if (!orientador.getAlunosOrientados().contains(this)) {
	            return orientador.addAluno(this);
	        }
	    }
	    return false;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public List<Turma> getListaTurma() {
		return listaTurma;
	}
	
	public List<Orientador> getOrientadores() {
		return orientadores;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(matricula, other.matricula) && Objects.equals(nome, other.nome);
	}
	
}