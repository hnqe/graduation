package application;

import entities.Aluno;
import entities.Orientador;
import entities.Turma;

public class TestaRelacaoBidirecional {

	public static void main(String[] args) {
        Orientador orientador1 = new Orientador("Prof. Dory");
        Orientador orientador2 = new Orientador("Prof. Frederico");
        Orientador orientador3 = new Orientador("Prof. Sirlon");

        Aluno a1 = new Aluno("1", "Caroline", orientador1);
        Aluno a2 = new Aluno("2", "Isabelle", orientador2);
        Aluno a3 = new Aluno("3", "Lucas", orientador1);
        
		Turma t1 = new Turma("1", "Informática");
		Turma t2 = new Turma("2", "Medicina", a2);
		
		t1.addAluno(a1);
		t1.addAluno(a2);
		t2.addAluno(a3);

		System.out.println("Turmas do aluno(a): " + a1.getNome());
		System.out.println(a1.getListaTurma());
		System.out.println();
		System.out.println("Turmas do aluno(a): " + a2.getNome());
		System.out.println(a2.getListaTurma());
		System.out.println();
		System.out.println("Turmas do aluno(a): " + a3.getNome());
		System.out.println(a2.getListaTurma());
		System.out.println();
		System.out.println("Alunos(as) da turma: " + t1.getNome());
		System.out.println(t1.getListaAluno());
		System.out.println();
		System.out.println("Alunos(as) da turma: " + t2.getNome());
		System.out.println(t2.getListaAluno());
		
        a1.addOrientador(orientador2);
        a2.addOrientador(orientador1);
        a2.addOrientador(orientador3);
        
        System.out.println();

        System.out.println("Orientadores do aluno(a) " + a1.getNome() + ":");
        for (Orientador orientador : a1.getOrientadores()) {
            System.out.println(orientador.getNome());
        }
        System.out.println();

        System.out.println("Orientadores do aluno(a) " + a2.getNome() + ":");
        for (Orientador orientador : a2.getOrientadores()) {
            System.out.println(orientador.getNome());
        }
        System.out.println();
        
        System.out.println("Orientadores do aluno(a) " + a3.getNome() + ":");
        for (Orientador orientador : a3.getOrientadores()) {
            System.out.println(orientador.getNome());
        }
        System.out.println();

        System.out.println("Alunos orientados pelo " + orientador1.getNome() + ":");
        for (Aluno aluno : orientador1.getAlunosOrientados()) {
            System.out.println(aluno.getNome());
        }
        System.out.println();

        System.out.println("Alunos orientados pelo " + orientador2.getNome() + ":");
        for (Aluno aluno : orientador2.getAlunosOrientados()) {
            System.out.println(aluno.getNome());
        }
	}
}
