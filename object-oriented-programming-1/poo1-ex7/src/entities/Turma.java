package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turma {

	private String codigo;
	private String nome;
	private List<Aluno> listaAluno = new ArrayList<>();
	
	
	public Turma(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public Turma(String codigo, String nome, Aluno aluno) {
		this.codigo = codigo;
		this.nome = nome;
		this.addAluno(aluno);
	}

	public void addAluno(Aluno aluno) {
		if( aluno != null && !listaAluno.contains(aluno) ) {
			listaAluno.add(aluno);
			if (!aluno.getListaTurma().contains(this))
			     aluno.addTurma(this);
		}
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public List<Aluno> getListaAluno() {
		return listaAluno;
	}

	@Override
	public String toString() {
		return "Turma [codigo=" + codigo + ", nome=" + nome + "]";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(codigo, nome);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nome, other.nome);
	}
	
}