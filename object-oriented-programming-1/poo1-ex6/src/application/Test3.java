package application;

import entities.ex3.CaixaEletronico;
import entities.ex3.Conta;

public class Test3 {
	
	public static void main(String[] args) {

		Conta c1 = new Conta(1, 1, 1000.00);
		Conta c2 = new Conta(1, 2, 5000.00);

		CaixaEletronico cx1 = new CaixaEletronico(50);
		
		cx1.deposito(c1, 500.00);
		cx1.saque(c2, 4000.50);
		
		System.out.println(c1);
		System.out.println(c2);
	}
}
