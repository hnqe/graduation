package entities;

public class Telefone {
	
	private Integer numMinutos = 0;
	private Double preco = 2.0;
	
	public Telefone() {
	}

	public Telefone(Integer numMinutos, Double preco) {
		this.numMinutos = numMinutos;
		this.preco = preco;
	}
	
	public void duracaoLigacao(int a) {
		numMinutos = a;
		System.out.println("Alterando duração da ligação...");
	}
	
	public void alterarPreco(double p) {
		preco = p;
		System.out.println("Alterando preço...");
	}
	
	public void mostrarConta() {
		System.out.println("A conta é: " + numMinutos * preco);
	}

	public Integer getNumMinutos() {
		return numMinutos;
	}

	public void setNumMinutos(Integer numMinutos) {
		this.numMinutos = numMinutos;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
