package application;

import entities.Estudante;
import entities.Orientador;
import exceptions.LimiteEstudanteException;
import exceptions.LimiteOrientadorException;

public class Program {
    public static void main(String[] args) {
        Orientador orientador1 = new Orientador("Prof Dory");
        Orientador orientador2 = new Orientador("Prof Sirlon");
        Orientador orientador3 = new Orientador("Prof Ariel");
        
        Estudante estudante1 = null;
        Estudante estudante2 = null;
        Estudante estudante3 = null;
        Estudante estudante4 = null;
        
        try {
            System.out.println("Adicionando estudantes ao Orientador 1...");
            estudante1 = new Estudante(101, orientador1);
            System.out.println("Estudante 101 adicionado.");
            estudante2 = new Estudante(102, orientador1);
            System.out.println("Estudante 102 adicionado.");
            estudante3 = new Estudante(103, orientador1);
            System.out.println("Estudante 103 adicionado.");
            
            System.out.println("Tentando adicionar estudante 104 ao Orientador 1...");

            // Esta próxima linha deve lançar LimiteEstudanteException
            estudante4 = new Estudante(104, orientador1);
            System.out.println("Estudante 104 adicionado.");

        } catch (LimiteEstudanteException | LimiteOrientadorException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println();
        
        try {
            System.out.println("Adicionando estudante 104 ao Orientador 2...");
            estudante4 = new Estudante(104, orientador2); // Novo orientador
            System.out.println("Estudante 104 adicionado ao Orientador 2.");
            
            System.out.println("Adicionando estudante 101 ao Orientador 2...");
            estudante1.addOrientador(orientador2);
            System.out.println("Estudante 101 adicionado ao Orientador 2.");
            
            System.out.println("Adicionando estudante 101 ao Orientador 3...");
            
            // Esta próxima linha deve lançar LimiteOrientadorException
            estudante1.addOrientador(orientador3);
            System.out.println("Estudante 101 adicionado ao Orientador 3.");

        } catch (LimiteEstudanteException | LimiteOrientadorException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println();
        System.out.println("ORIENTADOR 1 " + orientador1.getListaEstudante());
        System.out.println("ORIENTADOR 2 " + orientador2.getListaEstudante());
        System.out.println();
        System.out.println("ESTUDANTE 1 " + estudante1.getListaOrientador());
        System.out.println("ESTUDANTE 2 " + estudante2.getListaOrientador());
        System.out.println("ESTUDANTE 3 " + estudante3.getListaOrientador());
        System.out.println("ESTUDANTE 4 " + estudante4.getListaOrientador());
    }
}
