package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Blog {

	private String titulo;
	private String autor;

	private List<Postagem> listaPostagens = new ArrayList<>();

	public Blog(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
	}

	public boolean addPostagem(String titulo, String conteudo, Date dataPublicacao) {

		Postagem postagem = new Postagem(titulo, conteudo, dataPublicacao);

		if (!listaPostagens.contains(postagem)) {
			listaPostagens.add(postagem);
			return true;
		}
		return false;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public List<Postagem> getListaPostagens() {
		return listaPostagens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, listaPostagens, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(listaPostagens, other.listaPostagens)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Blog [titulo=" + titulo + ", autor=" + autor + ", listaPostagens=" + listaPostagens + "]";
	}

}
