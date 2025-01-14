package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Postagem {
	
	private String titulo;
	private String conteudo;
	private Date dataPublicacao;
	
	private List<Comentario> listaComentarios = new ArrayList<>();

	public Postagem(String titulo, String conteudo, Date dataPublicacao) {
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataPublicacao = dataPublicacao;
	}
	
	public boolean addComentario(String autor, String mensagem) {
		
		Comentario comentario = new Comentario(autor, mensagem);
		
		if(!listaComentarios.contains(comentario)) {
			listaComentarios.add(comentario);
			return true;
		}
		return false;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conteudo, dataPublicacao, listaComentarios, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		return Objects.equals(conteudo, other.conteudo) && Objects.equals(dataPublicacao, other.dataPublicacao)
				&& Objects.equals(listaComentarios, other.listaComentarios) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Postagem [titulo=" + titulo + ", conteudo=" + conteudo + ", dataPublicacao=" + dataPublicacao
				+ ", listaComentarios=" + listaComentarios + "]";
	}
}
