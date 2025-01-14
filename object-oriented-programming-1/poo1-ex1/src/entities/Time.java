package entities;

public class Time {
	
	private Integer pontosGanhos = 0;
	
	public Time() {
	}

	public Time(Integer pontosGanhos) {
		this.pontosGanhos = pontosGanhos;
	}
	
	public void ganharPartida() {
		pontosGanhos += 3;
		System.out.println("Você ganhou a partida! Adicionando pontos...");
	}
	
	public void empatarPartida() {
		pontosGanhos += 1;
		System.out.println("Você empatou a partida! Adicionando ponto...");
	}
	
	public void perderPartida() {
		pontosGanhos += 0;
		System.out.println("Você perdeu a partida! Infelizmente não será adicionado nenhum ponto...");
	}
	
	public void mostrarPontos() {
		System.out.println("O time tem " + pontosGanhos + " pontos!");
	}

	public Integer getPontosGanhos() {
		return pontosGanhos;
	}

	public void setPontosGanhos(Integer pontosGanhos) {
		this.pontosGanhos = pontosGanhos;
	}
}
