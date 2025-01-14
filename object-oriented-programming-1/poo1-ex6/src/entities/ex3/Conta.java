package entities.ex3;

public class Conta {
	
	private Integer agencia;
	private Integer numero;
	protected Double saldo;
	
	public Conta(Integer agencia, Integer numero, Double saldo) {
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	@Override
	public String toString() {
		return "Conta [agencia=" + agencia + ", numero=" + numero + ", saldo=" + saldo + "]";
	}
}