package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptions.DataInvalidaException;

public abstract class Evento {
	
	protected String titulo;
	protected String descricao;
	private LocalDate inicio;
	private LocalDate fim;
	private String localizacao;
	private List<Participante> listaParticipante = new ArrayList<>();
	
	public Evento(String titulo, String descricao, LocalDate inicio, LocalDate fim, String localizacao) {
		if(inicio.isAfter(fim)) {
			throw new DataInvalidaException("Data de início do evento não pode ser posterior data de fim.");
		}
		this.titulo = titulo;
		this.descricao = descricao;
		this.inicio = inicio;
		this.fim = fim;
		this.localizacao = localizacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public List<Participante> getListaParticipante() {
		return listaParticipante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, fim, inicio, localizacao, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(fim, other.fim)
				&& Objects.equals(inicio, other.inicio) && Objects.equals(localizacao, other.localizacao)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Evento [titulo=" + titulo + ", descricao=" + descricao + ", inicio=" + inicio + ", fim=" + fim
				+ ", localizacao=" + localizacao + "]";
	}
	
	public boolean adicionarParticipante(Participante participante) {
		
		if ( participante != null && !listaParticipante.contains(participante)) {
			this.listaParticipante.add(participante);
			return true;
		}
		return false;
	}
	
	public boolean removerParticipante(Participante participante) {
		
		if ( participante != null && listaParticipante.size() > 0 && listaParticipante.contains(participante) ) {
			this.listaParticipante.remove(participante);
			return true;
		}
		return false;
	}
	
	public void imprimirListaParticipantes() {
	    System.out.println("Lista de Participantes do Evento: " + this.titulo);
	    if (listaParticipante.isEmpty()) {
	        System.out.println("Nenhum participante cadastrado.");
	    } else {
	        for (Participante participante : listaParticipante) {
	            System.out.println(participante);
	        }
	    }
	}

    public abstract void imprimirDetalhes();
}
