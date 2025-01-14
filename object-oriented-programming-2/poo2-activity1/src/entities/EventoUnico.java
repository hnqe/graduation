package entities;

import java.time.LocalDate;

public class EventoUnico extends Evento {

	public EventoUnico(String titulo, String descricao, LocalDate inicio, LocalDate fim, String localizacao) {
		super(titulo, descricao, inicio, fim, localizacao);
	}

    @Override
    public void imprimirDetalhes() {
        System.out.println("Evento Único: " + titulo + " - " + descricao);
    }
}
