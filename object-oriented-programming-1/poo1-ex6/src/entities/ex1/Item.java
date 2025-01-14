package entities.ex1;

import java.util.Objects;

public class Item {

	private Integer quantidade;

	private Produto produto;

	public Item(Integer quantidade, Produto produto) {
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void aumentaQtde(int qtde) {
		this.quantidade += qtde;
	}

	public void diminuiQtde(int qtde) {
		if (quantidade > qtde) {
			this.quantidade -= qtde;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(produto, other.produto);
	}

	@Override
	public String toString() {
		return "Item [quantidade=" + quantidade + ", produto=" + produto + "]";
	}
}
