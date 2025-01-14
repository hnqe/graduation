package entities.ex1;

import java.util.ArrayList;
import java.util.List;

public class NotaFiscal {

	private Double valorTotal = 0.0;

	private List<Item> listaItem = new ArrayList<>();

	public NotaFiscal() {
	}

	public NotaFiscal(int quantidade, Produto produto) {
		this.addItem(quantidade, produto);
	}

	public void addItem(int quantidade, Produto produto) {
		Item i = new Item(quantidade, produto);

		if (listaItem.contains(i)) {
			int posicao = listaItem.indexOf(i);
			listaItem.get(posicao).aumentaQtde(quantidade);
		} else {
			listaItem.add(i);
		}
	}

	public Double totalNotaFiscal() {
		
		Double total = 0.0;
		
		for (Item i : listaItem) {
			total += (i.getProduto().getValor() * i.getQuantidade());
		}
		return total;
	}
	
	public void mostraItens() {		
		int p = 1;
		System.out.println("Item \tQuantidade\tValor\tDescrição");
		for (Item i : listaItem) {
			
			System.out.println(p + "\t"
								 + i.getQuantidade() + "\t\t" 
								 + i.getProduto().getValor() + "\t"
								 + i.getProduto().getDescricao());
			p++;
		}
	}
	
	public List<Item> getListaItem() {
		return listaItem;
	}

	public void imprimirNotaFiscal() {
		this.valorTotal = totalNotaFiscal();
		System.out.println("***** NOTA FISCAL *****");
		this.mostraItens();
		System.out.println("***********************");
		System.out.println("Total da Nota: " + this.valorTotal);
	}
}
