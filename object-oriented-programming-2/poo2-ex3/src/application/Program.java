package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Aluno;
import entities.Curso;
import entities.Matricula;
import exception.IdadeException;

public class Program {

    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Curso> cursos = new ArrayList<>();
    private static List<Matricula> matriculas = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		        
        boolean sair = false;
        while (!sair) {
            System.out.println("\nSistema de Matrículas");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Curso");
            System.out.println("3. Realizar Matrícula");
            System.out.println("4. Listar Alunos");
            System.out.println("5. Listar Cursos");
            System.out.println("6. Listar Matrículas");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = sc.nextInt();
            
            
            switch (opcao) {
            	case 1:
            		cadastrarAluno();
            		break;
            	case 2:
            		cadastrarCurso();
            		break;
            	case 3:
            		realizarMatricula();
            		break;
                case 4:
                    listarAlunos();
                    break;
                case 5:
                    listarCursos();
                    break;
                case 6:
                    listarMatriculas();
                    break;
            	case 7:
            		sair = true;
            		System.out.println("Saindo do sistema...");
            		break;
            	default:
            		System.out.println("Opcao inválida. Tente novamente!");
            }
        }

	}

	private static void cadastrarAluno() {
		System.out.println("\nCadastro de Aluno");
	    System.out.print("Número da matrícula: ");
	    int numMatricula = sc.nextInt();
	    sc.nextLine(); 

	    for (Aluno aluno : alunos) {
	        if (aluno.getMatricula() == numMatricula) {
	            System.out.println("Erro: Número de matrícula já cadastrado.");
	            return; 
	        }
	    }

	    System.out.print("Nome: ");
	    String nome = sc.nextLine();
	    System.out.print("Data de Nascimento (YYYY-MM-DD): ");
	    LocalDate dataNascimento = LocalDate.parse(sc.nextLine());
	    alunos.add(new Aluno(numMatricula, nome, dataNascimento));
	    System.out.println("Aluno cadastrado com sucesso!");
	}

	
    private static void cadastrarCurso() {
        System.out.println("\nCadastro de Curso");
        System.out.print("Código do curso: ");
        int codigoCurso = sc.nextInt();
        sc.nextLine();

        for (Curso curso : cursos) {
            if (curso.getCodigo() == codigoCurso) {
                System.out.println("Erro: Código do curso já cadastrado.");
                return; 
            }
        }

        System.out.print("Nome do curso: ");
        String nomeCurso = sc.nextLine();
        System.out.print("Data de Início (YYYY-MM-DD): ");
        LocalDate dataInicio = LocalDate.parse(sc.nextLine());
        cursos.add(new Curso(codigoCurso, nomeCurso, dataInicio));
        System.out.println("Curso cadastrado com sucesso!");
    }
    
    private static void realizarMatricula() {
        System.out.println("\nRealizar Matrícula");
        System.out.print("Número da matrícula do aluno: ");
        int numMatricula = sc.nextInt();
        System.out.print("Código do curso: ");
        int codigoCurso = sc.nextInt();
        sc.nextLine(); 

        Aluno alunoEncontrado = null;
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == numMatricula) {
                alunoEncontrado = aluno;
                break;
            }
        }

        Curso cursoEncontrado = null;
        for (Curso curso : cursos) {
            if (curso.getCodigo() == codigoCurso) {
                cursoEncontrado = curso;
                break;
            }
        }

        if (alunoEncontrado == null || cursoEncontrado == null) {
            System.out.println("Aluno ou Curso não encontrado.");
            return;
        }

        try {
            Matricula novaMatricula = new Matricula(alunoEncontrado, cursoEncontrado, LocalDate.now());
            matriculas.add(novaMatricula);
            System.out.println("Matrícula realizada com sucesso!");
        } catch (IdadeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void listarAlunos() {
        System.out.println("\nLista de Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println("Matrícula: " + aluno.getMatricula() + ", Nome: " + aluno.getNome() + ", Data de Nascimento: " + aluno.getDataNascimento());
        }
    }

    private static void listarCursos() {
        System.out.println("\nLista de Cursos:");
        for (Curso curso : cursos) {
            System.out.println("Código: " + curso.getCodigo() + ", Nome: " + curso.getNome() + ", Data de Início: " + curso.getDataInicio());
        }
    }

    private static void listarMatriculas() {
        System.out.println("\nLista de Matrículas:");
        for (Matricula matricula : matriculas) {
            System.out.println("Aluno: " + matricula.getAluno().getNome() + ", Curso: " + matricula.getCurso().getNome() + ", Data de Matrícula: " + matricula.getDataMatricula());
        }
    }
}
