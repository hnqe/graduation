package entities;

public class Elevador {
	
	private Integer andarAtual;
	private Integer totalAndares;
	private Integer capacidadeElevador;
	private Integer pessoasPresentes;
	
	public Elevador() {
	}

	public Elevador(Integer andarAtual, Integer totalAndares, Integer capacidadeElevador, Integer pessoasPresentes) {
		super();
		this.andarAtual = andarAtual;
		this.totalAndares = totalAndares;
		this.capacidadeElevador = capacidadeElevador;
		this.pessoasPresentes = pessoasPresentes;
	}
	
	public void inicializa(int capacidadeElevador, int totalAndares) {
		this.andarAtual = 0;
		this.pessoasPresentes = 0;
		this.capacidadeElevador = capacidadeElevador;
		this.totalAndares = totalAndares;
		System.out.println("Inicializando...");
	}
	
	public void entra() {
		if (pessoasPresentes < capacidadeElevador) {
			pessoasPresentes += 1;
			System.out.println("Entrou uma pessoa!");
		} else {
			System.out.println("Não há espaço. Capacidade máxima!");
		}
	}

	public void sai() {
		if (pessoasPresentes >= 1) {
			pessoasPresentes -= 1;
			System.out.println("Saiu uma pessoa!");
		} else {
			System.out.println("Não há pessoas para sair!");
		}
	}
	
	public void sobe() {
		if (andarAtual < totalAndares) {
			andarAtual += 1;
			System.out.println("Subindo um andar...");
		} else {
			System.out.println("Já estamos no último andar!");
		}
	}
	
	public void desce() {
		if (andarAtual > 0) {
			andarAtual -= 1;
			System.out.println("Descendo um andar...");
		} else {
			System.out.println("Estamos no térreo, não é possível descer mais!");
		}
	}

	public Integer getAndarAtual() {
		return andarAtual;
	}

	public Integer getTotalAndares() {
		return totalAndares;
	}

	public Integer getCapacidadeElevador() {
		return capacidadeElevador;
	}

	public Integer getPessoasPresentes() {
		return pessoasPresentes;
	}
}
