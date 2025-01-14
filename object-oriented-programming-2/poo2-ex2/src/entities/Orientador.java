package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptions.LimiteEstudanteException;

public class Orientador {

	String nome;
	private List<Estudante> listaEstudante = new ArrayList<>();
	
	public Orientador(String nome) {
		this.nome = nome;
	}
	
	public void addEstudante(Estudante estudante) {
		if (estudante != null && !listaEstudante.contains(estudante)) {
			if (listaEstudante.size() < 3) {
				
				listaEstudante.add(estudante);
				
				if (!estudante.getListaOrientador().contains(this)) {
					estudante.addOrientador(this);
				}
			} else {
                throw new LimiteEstudanteException("\nLimite máximo de estudantes para o orientador alcançado.");
			} 
		} else {
			System.out.println("\nEstudante vazio (null) ou já existente!");
		}
	}
	
	public void removeEstudante(Estudante estudante) {
		if (estudante != null && !listaEstudante.contains(estudante)) {
			if (listaEstudante.size() > 0) {
				
				listaEstudante.remove(estudante);
				
				if (estudante.getListaOrientador().contains(this)) {
					estudante.removeOrientador(this);
				}
			} else {
                throw new LimiteEstudanteException("\nLista de estudantes orientados vazia!");
			} 
		} else {
			System.out.println("\nAluno vazio ou não existente na lista!");
		}
	}
	
	public String getNome() {
		return nome;
	}

	public List<Estudante> getListaEstudante() {
		return listaEstudante;
	}

	@Override
	public String toString() {
		return "Orientador [nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orientador other = (Orientador) obj;
		return Objects.equals(nome, other.nome);
	}
}
