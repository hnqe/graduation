package application;

import entities.ex1.NotaFiscal;
import entities.ex1.Produto;

public class Test1 {

    public static void main(String[] args) {
    	
        Produto p1 = new Produto(1, 200.00, "Cadeira");
        Produto p2 = new Produto(2, 1100.00, "Mesa");
        Produto p3 = new Produto(3, 3200.00, "Computador");
        Produto p4 = new Produto(4, 3500.00, "Celular");

        NotaFiscal nf1 = new NotaFiscal(2, p3);
        
        nf1.addItem(1, p4);
        
        nf1.addItem(1, p3);
        
        nf1.imprimirNotaFiscal();
    }
}
