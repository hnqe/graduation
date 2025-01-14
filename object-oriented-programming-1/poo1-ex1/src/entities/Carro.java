package entities;

public class Carro {
	
	private Double velocidade = 0.0;
	
	public Carro() {
	}

	public Carro(Double velocidade) {
		this.velocidade = velocidade;
	}
	
	public void acelerar() {
		velocidade += 10;
		System.out.println("Acelerando...");
	}
	
	public void freiar() {
		velocidade -= 10;
		System.out.println("Freiando...");
	}
	
	public void mostrarVelocidade() {
		System.out.println("A velocidade carro é: " + velocidade);
	}

	public Double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade;
	}
}
