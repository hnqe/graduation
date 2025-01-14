package entities;

import java.util.Objects;

public class Comentario {

	private String autor;

	private String mensagem;

	public Comentario(String autor, String mensagem) {
		this.autor = autor;
		this.mensagem = mensagem;
	}

	public String getAutor() {
		return autor;
	}

	public String getMensagem() {
		return mensagem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, mensagem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(mensagem, other.mensagem);
	}

	@Override
	public String toString() {
		return "Comentário [autor=" + autor + ", mensagem=" + mensagem + "]";
	}
}
