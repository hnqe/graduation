package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptions.LimiteOrientadorException;

public class Estudante {

	private int matricula;
	private List<Orientador> listaOrientador = new ArrayList<>();
	
	public Estudante(int matricula, Orientador o) {
		this.matricula = matricula;
		this.addOrientador(o);
	}

	public void addOrientador(Orientador o) {
		if (o != null && !listaOrientador.contains(o)) {
			if (listaOrientador.size() < 2) {
				
				listaOrientador.add(o);
				
				if (!o.getListaEstudante().contains(this)) {
					o.addEstudante(this);
				}
			} else {
                throw new LimiteOrientadorException("\nLimite máximo de orientadores para o estudante alcançado.");
			}
		} else {
			System.out.println("\nOrientador vazio (null) ou já existente!");
		}
	}
	
	public void removeOrientador(Orientador o) {
		if (o != null && listaOrientador.contains(o)) {
			if (listaOrientador.size() >= 2) {
				listaOrientador.remove(o);
				
				if (o.getListaEstudante().contains(this)) {
					o.removeEstudante(this);
				}
			} else {
                throw new LimiteOrientadorException("\nOrientador não pode ser removido devido limite mínimo de orientador cadastrado!");
			}
		} else {
			System.out.println("\nOrientador vazio ou não existente na lista!");
		}
	}

	public int getMatricula() {
		return matricula;
	}

	public List<Orientador> getListaOrientador() {
		return listaOrientador;
	}

	@Override
	public String toString() {
		return "Estudante [matricula=" + matricula + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudante other = (Estudante) obj;
		return matricula == other.matricula;
	}
}