package entities;

public class PC {

	private String modelo;

	private Teclado teclado;
	
	private Webcam webcam;

	public PC(String modelo, Teclado teclado) {
		this.modelo = modelo;
		this.teclado = teclado;
	}

	public PC(String modelo, Teclado teclado, Webcam webcam) {
		this.modelo = modelo;
		this.teclado = teclado;
		this.webcam = webcam;
	}

	public String getModelo() {
		return modelo;
	}

	public Teclado getTeclado() {
		return teclado;
	}

	public Webcam getWebcam() {
		return webcam;
	}

	public void setWebcam(Webcam webcam) {
		this.webcam = webcam;
	}

	@Override
	public String toString() {
		if (webcam != null) {
			return "PC [modelo=" + modelo + ", teclado=" + teclado + ", webcam=" + webcam + "]";
		} else {
			return "PC [modelo=" + modelo + ", teclado=" + teclado + "]";
		}
	}
}