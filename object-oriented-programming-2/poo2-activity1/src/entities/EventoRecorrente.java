package entities;

import java.time.LocalDate;

public class EventoRecorrente extends Evento{

	public EventoRecorrente(String titulo, String descricao, LocalDate inicio, LocalDate fim, String localizacao) {
		super(titulo, descricao, inicio, fim, localizacao);
	}

    public void imprimirDetalhes() {
        System.out.println("Evento Recorrente: " + titulo + " - " + descricao);
    }
}
