package application;

import java.util.Scanner;

import entities.Agenda;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda(20); 

        while (true) {
            System.out.println("\nAgenda - Menu\n1 – Cadastrar Pessoa\n2 – Remover Pessoa\n3 – Buscar Pessoa\n4 – Imprimir Pessoa\n5 – Imprimir Agenda\n6 – Encerrar");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); 
            switch (opcao) {
                case 1:
                    System.out.print("Informe o nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Informe a idade: ");
                    int idade = sc.nextInt();
                    System.out.print("Informe a altura: ");
                    double altura = sc.nextDouble();
                    agenda.armazenaPessoa(nome, idade, altura);
                    break;
                case 2:
                    System.out.print("Informe o nome para remover: ");
                    nome = sc.nextLine();
                    agenda.removePessoa(nome);
                    break;
                case 3:
                    System.out.print("Informe o nome para buscar: ");
                    nome = sc.nextLine();
                    int posicao = agenda.buscaPessoa(nome);
                    if (posicao != -1) {
                        System.out.println("Pessoa encontrada na posição: " + posicao);
                    } else {
                        System.out.println("Pessoa não encontrada!");
                    }
                    break;
                case 4:
                    System.out.print("Informe a posição para imprimir: ");
                    posicao = sc.nextInt();
                    agenda.imprimePessoa(posicao);
                    break;
                case 5:
                    agenda.imprimeAgenda();
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
            sc.close();
        }
	}
}