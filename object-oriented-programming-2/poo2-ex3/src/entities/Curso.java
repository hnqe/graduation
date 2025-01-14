package entities;

import java.time.LocalDate;

public class Curso {

	private int codigo;
	private String nome;
	private LocalDate dataInicio;
	
	public Curso(int codigo, String nome, LocalDate dataInicio) {
		this.codigo = codigo;
		this.nome = nome;
		this.dataInicio = dataInicio;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nome=" + nome + ", dataInicio=" + dataInicio + "]";
	}
}
