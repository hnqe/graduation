package entities.ex3;

public class CaixaEletronico {
	
	private Integer codigo;
	
	public CaixaEletronico(Integer codigo) {
		this.codigo = codigo;
	}
	
	public void saque(Conta c, Double valor) {
		if (c != null && c.getSaldo() >= valor) {
			c.saldo -= valor;
		}
	}
	
	public void deposito(Conta c, Double valor) {
		if (c != null) {
			c.saldo += valor;
		}
	}
}
