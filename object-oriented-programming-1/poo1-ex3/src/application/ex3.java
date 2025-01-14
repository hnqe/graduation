package application;

import java.util.ArrayList;
import java.util.Scanner;

public class ex3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> valores = new ArrayList<>();
		
		System.out.println("Insira os valores inteiros (insira um número negativo para sair)");
		while(true) {
			
			int valor = sc.nextInt();
			if(valor < 0) {
				break;
			}
			
			valores.add(valor);
		}
		
		System.out.println("Valores inseridos");
		for (int valor : valores) {
			System.out.print(valor + " ");
		}
		
		int contagemUnicos = contarValoresUnicos(valores);
		System.out.println("\nNúmero de valores únicos: " + contagemUnicos);
		sc.close();
	}

	private static int contarValoresUnicos(ArrayList<Integer> valores) {
		
		ArrayList<Integer> unicos = new ArrayList<>();
		for(int valor : valores) {
			if(!unicos.contains(valor)) {
				unicos.add(valor);
			}
		}
		return unicos.size();
	}
}
