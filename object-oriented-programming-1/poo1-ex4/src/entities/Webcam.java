package entities;

public class Webcam {
	
    private String marca;
    private int resolucao;

    public Webcam(String marca, int resolucao) {
        this.marca = marca;
        this.resolucao = resolucao;
    }

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getResolucao() {
		return resolucao;
	}

	public void setResolucao(int resolucao) {
		this.resolucao = resolucao;
	}

	@Override
	public String toString() {
		return "Webcam [marca=" + marca + ", resolucao=" + resolucao + "]";
	}
}
