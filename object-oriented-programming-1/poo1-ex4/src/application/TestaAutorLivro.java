package application;

import entities.Autor;
import entities.Livro;

public class TestaAutorLivro {
	
 public static void main(String[] args) {
	 
        Livro livro1 = new Livro("Harry Potter e a Pedra Filosofal", 1997);
        Livro livro2 = new Livro("Harry Potter e a Câmara Secreta", 1998);

        Autor autora = new Autor("J.K. Rowling", "Britânica", livro1);

        autora.addLivro(livro2);

        System.out.println("Autora: " + autora.getNome() + " (" + autora.getNacionalidade() + ")");
        System.out.println("Livros:");
        for (Livro livro : autora.getLivros()) {
            System.out.println(" - " + livro.getTitulo() + " (" + livro.getAno() + ")");
        }
    }
}
