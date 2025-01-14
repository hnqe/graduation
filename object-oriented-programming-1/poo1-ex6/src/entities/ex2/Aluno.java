package entities.ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aluno {
	
	private String matricula;
	private String nome;
	
	private List<Emprestimo> listaEmprestimo = new ArrayList<>();
	
	public Aluno(String matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
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
	
	public List<Emprestimo> getListaEmprestimo() {
		return listaEmprestimo;
	}
	
	public boolean add_Emprestimo(Emprestimo e) {
		boolean sucesso = false;
		if (e != null && !listaEmprestimo.contains(e)) {
			listaEmprestimo.add(e);
			e.setResponsavel(this);
			sucesso = true;
		}
		return sucesso;
	}
	
	public boolean remove_Emprestimo(Emprestimo e) {
		boolean sucesso = false;
		if( e != null && listaEmprestimo.size() > 0 && listaEmprestimo.contains(e)) {
			listaEmprestimo.remove(e);
			e.setResponsavel(null);
			sucesso = true;
		}
		return sucesso;
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

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + "]";
	}

}
