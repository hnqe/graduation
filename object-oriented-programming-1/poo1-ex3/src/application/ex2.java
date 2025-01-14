package application;

import java.util.ArrayList;
import java.util.Scanner;

public class ex2 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		
		System.out.println("Insira os números da lista (insira um número negativo para terminar)");
		while(true) {
			Integer num = sc.nextInt();
			
			if (num < 0) {
				break;
			}
			
			list.add(num);
		}
		
		Integer pares = calcularPares(list);
		System.out.println("A quantidade de pares na lista é: " + pares);
		sc.close();
	}

	private static Integer calcularPares(ArrayList<Integer> list) {
		Integer pares = 0;
		for (Integer numero : list) {
			if (numero % 2 == 0) {
				pares++;
			}
		}
		return pares;
	}
}
