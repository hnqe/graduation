package entities;

import java.util.ArrayList;
import java.util.List;

public class Autor {
	
    private String nome;
    private String nacionalidade;
    private List<Livro> listaLivros = new ArrayList<>();

    public Autor(String nome, String nacionalidade, Livro livro) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.addLivro(livro);
    }

    public boolean addLivro(Livro livro) {
    	
    	if (livro != null) {
            listaLivros.add(livro);
			return true;
    	} 
    	return false;
    }
    
	public boolean removerLivro(Livro livro) {
		
		if (listaLivros.size() > 1 && listaLivros.contains(livro)) {
			listaLivros.remove(livro);
			return true;
		}
		return false;
	}

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public List<Livro> getLivros() {
        return listaLivros;
    }
}
