package entities;

public class Relogio {
	
	private Integer hora = 6;
	
	public Relogio() {
	}

	public Relogio(Integer hora) {
		this.hora = hora;
	}
	
	public void adicionarHora() {
		if (hora < 23) {
			hora += 1;
			System.out.println("Aumentando hora...");
		} else {
			hora = 0;
			System.out.println("Aumentando hora... A hora foi aumentada com o horário máximo. Horário ajustado!");
		}
	}
	
	public void subtrairHora() {
		if (hora > 0) {
			hora -= 1;
			System.out.println("Diminuindo hora...");
		} else {
			hora = 23;
			System.out.println("Diminuindo hora... A hora foi diminuida com o horário zerado. Horário ajustado!");
		}
	}
	
	public void mostrarHora() {
		System.out.println("Hora atual: " + hora);
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}
}
