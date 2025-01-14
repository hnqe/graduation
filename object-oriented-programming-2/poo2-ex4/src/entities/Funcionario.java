package entities;

import enums.TipoDeDocumento;

public class Funcionario {
	
	private String nome;
	private TipoDeDocumento documento;
	private String numeroDocumento;
	
	public Funcionario(String nome, TipoDeDocumento documento, String numeroDocumento) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.numeroDocumento = numeroDocumento;
	}

	public String getNome() {
		return nome;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public TipoDeDocumento getDocumento() {
		return documento;
	}

	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", numeroDocumento=" + numeroDocumento + ", documento=" + documento + "]";
	}
}
