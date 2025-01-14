package entities;

public class Participante {

	private String nome;

	public Participante(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Participante [nome=" + nome + "]";
	}
	
	
}
