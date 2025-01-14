package application;

import java.util.ArrayList;
import java.util.Scanner;

public class ex1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<Float> notas = new ArrayList<>();
		
        System.out.println("Insira as notas dos alunos (insira um número negativo para terminar):");
        while(true) {
        	float nota = sc.nextFloat();
        	
        	if (nota < 0) {
        		break;
        	} else if (nota > 10) {
        		System.out.println("Nota inválida. Insira uma nota entre 0 e 10");	
        		continue;
        	}
        	
        	notas.add(nota);
        }
        
        float media = calcularMedia(notas);
		System.out.println("Media do aluno: " + media);
		sc.close();
	}
	
	public static float calcularMedia(ArrayList<Float> notas) {
		float soma = 0;
		for(float nota: notas) {
			soma += nota;
		}
		return soma / notas.size();
	}
}
