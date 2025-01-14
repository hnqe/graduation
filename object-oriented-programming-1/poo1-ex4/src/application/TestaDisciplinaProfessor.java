package application;

import entities.Disciplina;
import entities.Professor;

public class TestaDisciplinaProfessor {

    public static void main(String[] args) {
    	
        Professor prof1 = new Professor("Dr. Smith", "Biologia");
        Professor prof2 = new Professor("Dr. Johnson", "Química");

        Disciplina disciplina = new Disciplina("Biologia Molecular", "BIO301", prof1);

        disciplina.addProfessor(prof2);

        System.out.println("Disciplina: " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
        System.out.println("Professores:");
        for (Professor professor : disciplina.getProfessores()) {
            System.out.println(" - " + professor.getNome() + " (" + professor.getDepartamento() + ")");
        }
    }
}
