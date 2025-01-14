package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;
import jdbc.db;


public class Program {

	public static void main(String[] args) throws SQLException {
        Scanner console = new Scanner(System.in);
		
		try {
        	
            int opcao = 0;
            
            do {
            	System.out.println("####### Menu #######"
            						+ "\n1 - Cadastrar"
            						+ "\n2 - Listar"
            						+ "\n3 - Alterar"
            						+ "\n4 - Excluir"
            						+ "\n5 - Sair");
            	System.out.print("\nOpcao: ");
            	opcao = Integer.parseInt(console.nextLine());
            	            	
                AlunoJDBC acao = new AlunoJDBC();
            	
        	    switch (opcao) {
	                case 1:
	                    Aluno a = new Aluno();

	                    System.out.println("\n ### Cadastrar Aluno ### \r");
	                    
	                    System.out.print("Nome: ");
	                    a.setNome(console.nextLine());
	                    
	                    System.out.print("Sexo: ");
	                    a.setSexo(console.nextLine());
	                
	                    System.out.print("Data de Nascimento (dd-mm-aaaa): ");
	                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                    a.setDt_nasc(LocalDate.parse(console.nextLine(), formato));
	                    
	                    acao.salvar(a);
	                    
	                    System.out.println("\nAluno cadastrado com sucesso!\n");
	                    break;
	
	                case 2:
	                    System.out.print("\n ### Listar Aluno ###\n\r");
	                    
	                    for (Aluno al : acao.listar()) {
	                        System.out.println(al);
	                    }
	                    
	                    System.out.println("\nListagem realizada com sucesso!\n");
	                    break;
	                    
                    case 3:
                        System.out.print("\n ### Alterar Aluno ###\n\r");
                        
                        System.out.print("Id do aluno a ser alterado: ");
                        int idParaAlterar = Integer.parseInt(console.nextLine());
                        
                        Aluno alunoAlterar = new Aluno();
                        alunoAlterar.setId(idParaAlterar);
                        
                        System.out.print("Novo Nome: ");
                        alunoAlterar.setNome(console.nextLine());
                        
                        System.out.print("Novo Sexo: ");
                        alunoAlterar.setSexo(console.nextLine());
                    
                        System.out.print("Nova Data de Nascimento (dd-MM-aaaa): ");
                        alunoAlterar.setDt_nasc(LocalDate.parse(console.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        
                        acao.alterar(alunoAlterar);
                        
                        System.out.println("\nAluno alterado com sucesso!\n");
                        break;	
                        
	                case 4:
	                	System.out.print("\n ### Deletar Aluno ###\n\r");
	                	
	                	System.out.print("Id do aluno: ");
	                	int idParaDeletar = Integer.parseInt(console.nextLine());
	                	
	                	acao.apagar(idParaDeletar);
	                	
	                	System.out.println("\nDeleção realizada com sucesso!\n");
	                	break;
	                    
	                case 5:
	                    System.out.println("Saindo...");
	                    break;
	
	                default:
	                    System.out.println("Opção inválida. Tente novamente.\n");
	                    break;
	            }
            	
            } while(opcao != 5);
            
            console.close();
        } catch (Exception e){
            System.out.println("Erro: " + e);
        } finally {
            db.closeConexao(); 
            console.close();
        }
	} 
}
