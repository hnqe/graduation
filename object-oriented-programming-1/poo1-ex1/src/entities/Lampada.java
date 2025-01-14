package entities;

public class Lampada {
	
	private Integer estado = 0;
	
	public Lampada() {
	}

	public Lampada(Integer estado) {
		this.estado = estado;
	}
	
	public void ligar() {
		estado = 1;
		System.out.println("Ligando lâmpada...");
	}
	
	public void desligar() {
		estado = 0;
		System.out.println("Desligando lâmpada...");

	}
	
	public void mostrarEstado() {
		if (estado == 1) {
			System.out.println("A lâmpada está ligada!");
		} else {
			System.out.println("A lâmpada está desligada!");
		}
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
