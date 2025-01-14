package application;

import entities.*;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        // Criar um usuário
        Usuario usuário = new Usuario("João");

        // Criar um blog para o usuário
        boolean criouBlog = usuário.criarBlog("Meu Blog", "João");
        if (criouBlog) {
            System.out.println("Blog criado com sucesso!");
        } else {
            System.out.println("Falha ao criar o blog. Blog já existe.");
        }

        // Adicionar postagens ao blog
        Blog blog = usuário.getListaBlogs().get(0); // Obter o blog criado
        blog.addPostagem("Primeira Postagem", "Este é o conteúdo da primeira postagem.", new Date());
        blog.addPostagem("Segunda Postagem", "Este é o conteúdo da segunda postagem.", new Date());

        // Exibir as postagens do blog
        System.out.println("Postagens no blog \"" + blog.getTitulo() + "\":");
        for (Postagem postagem : blog.getListaPostagens()) {
            System.out.println(postagem);
        }

        // Adicionar comentários às postagens
        Postagem primeiraPostagem = blog.getListaPostagens().get(0); // Obter a primeira postagem
        primeiraPostagem.addComentario("Maria", "Ótima postagem!");
        primeiraPostagem.addComentario("Carlos", "Gostei muito do conteúdo.");

        // Exibir os comentários da primeira postagem
        System.out.println("\nComentários na primeira postagem:");
        for (Comentario comentário : primeiraPostagem.getListaComentarios()) {
            System.out.println(comentário);
        }
    }
}
