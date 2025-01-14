package entities;

import java.util.Objects;

public class Livro {

	private String titulo;
	private Integer ano;

	public Livro(String titulo, Integer ano) {
		this.titulo = titulo;
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(ano, other.ano) && Objects.equals(titulo, other.titulo);
	}
}
