package entities.ex2;

import java.util.Objects;

public class Livro {

	private Integer codigo;
	private String titulo;

	public Livro(Integer codigo, String titulo) {
		this.codigo = codigo;
		this.titulo = titulo;
	}

	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, titulo);
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
		return Objects.equals(codigo, other.codigo) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Livro [codigo=" + codigo + ", titulo=" + titulo + "]";
	}
}
