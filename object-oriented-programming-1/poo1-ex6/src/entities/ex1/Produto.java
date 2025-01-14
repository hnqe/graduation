package entities.ex1;

import java.util.Objects;

public class Produto {

	private Integer codigo;

	private Double valor;

	private String descricao;

	public Produto(Integer codigo, Double valor, String descricao) {
		this.codigo = codigo;
		this.valor = valor;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descricao, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", valor=" + valor + ", descricao=" + descricao + "]";
	}
}