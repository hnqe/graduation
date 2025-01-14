package entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nome;
	
	List<Blog> listaBlogs = new ArrayList<>();
	
	public Usuario(String nome) {
		this.nome = nome;
	}
	
	public boolean criarBlog(String titulo, String autor) {
		
		Blog blog = new Blog(titulo, autor);
		
		if (!listaBlogs.contains(blog)) {
			listaBlogs.add(blog);
			return true;
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public List<Blog> getListaBlogs() {
		return listaBlogs;
	}
}
