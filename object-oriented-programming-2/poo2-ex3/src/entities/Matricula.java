package entities;

import java.time.LocalDate;
import java.time.Period;

import exception.IdadeException;

public class Matricula {

	private Aluno aluno;
	private Curso curso;
	private LocalDate dataMatricula;
	
	public Matricula(Aluno aluno, Curso curso, LocalDate dataMatricula) {

		this.aluno = aluno;
		this.curso = curso;
		this.dataMatricula = dataMatricula;
		
		if (calcularIdade() < 18) {
			throw new IdadeException("O aluno não pode fazer a matrícula pois o mesmo é menor de idade");
		}
	}
	
	public int calcularIdade() {
		return Period.between(aluno.getDataNascimento(), dataMatricula).getYears();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	@Override
	public String toString() {
		return "Matricula [aluno=" + aluno + ", curso=" + curso + ", dataMatricula=" + dataMatricula + "]";
	} 
}
