package application;

import java.text.SimpleDateFormat;

import entities.ex2.Aluno;
import entities.ex2.Emprestimo;
import entities.ex2.Livro;

public class Test2 {
	
	public static void main(String[] args) {

        Aluno aluno = new Aluno("2021001", "Maria Silva");

        Livro livro = new Livro(101, "O Pequeno Príncipe");

        // Criação de um empréstimo do livro para o aluno
        // O empréstimo é automaticamente adicionado à lista de empréstimos do aluno
        Emprestimo emprestimo = new Emprestimo(aluno, livro);

        // Formatador de data para exibir as datas
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Detalhes do Empréstimo:");
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Código: " + livro.getCodigo());
        System.out.println("Data de retirada: " + sdf.format(emprestimo.getData_retirada()));
        System.out.println("Data prevista de devolução: " + sdf.format(emprestimo.getData_devolucao()));

        // Supondo que o empréstimo é devolvido
        boolean removido = aluno.remove_Emprestimo(emprestimo);
        System.out.println("Empréstimo devolvido: " + removido);
    }
}
