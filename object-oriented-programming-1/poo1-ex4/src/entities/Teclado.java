package entities;

public class Teclado {

	private String marca;
	private String tipo; 

	public Teclado(String marca, String tipo) {
		this.marca = marca;
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Teclado [marca=" + marca + ", tipo=" + tipo + "]";
	}
}
