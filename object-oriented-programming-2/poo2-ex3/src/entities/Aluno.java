package entities;

import java.time.LocalDate;

public class Aluno {

	private int matricula;
	private String nome;
	private LocalDate dataNascimento;
	
	public Aluno(int matricula, String nome, LocalDate dataNascimento) {
		this.matricula = matricula;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public int getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", dataNascimento=" + dataNascimento + "]";
	}
}
