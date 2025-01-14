package entities.ex2;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Emprestimo {

	private Aluno responsavel = null;
	private Livro livro_alugado = null;
	private Date data_retirada = null;
	private Date data_devolucao = null; // coloquei a data de devolução para sempre depois de 15 dias.
	
	public Emprestimo(Aluno responsavel, Livro livro_alugado) {
		this.responsavel = responsavel;
		this.responsavel.add_Emprestimo(this);
		this.livro_alugado = livro_alugado;
        this.data_retirada = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data_retirada);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        this.data_devolucao = calendar.getTime();
	}

	public Aluno getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Aluno responsavel) {
		this.responsavel = responsavel;
	}

	public Livro getLivro_alugado() {
		return livro_alugado;
	}

	public void setLivro_alugado(Livro livro_alugado) {
		this.livro_alugado = livro_alugado;
	}

	public Date getData_retirada() {
		return data_retirada;
	}

	public void setData_retirada(Date data_retirada) {
		this.data_retirada = data_retirada;
	}

	public Date getData_devolucao() {
		return data_devolucao;
	}

	public void setData_devolucao(Date data_devolucao) {
		this.data_devolucao = data_devolucao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data_devolucao, data_retirada, livro_alugado, responsavel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		return Objects.equals(data_devolucao, other.data_devolucao)
				&& Objects.equals(data_retirada, other.data_retirada)
				&& Objects.equals(livro_alugado, other.livro_alugado) && Objects.equals(responsavel, other.responsavel);
	}

	@Override
	public String toString() {
		return "Emprestimo [responsavel=" + responsavel + ", livro_alugado=" + livro_alugado + ", data_retirada="
				+ data_retirada + ", data_devolucao=" + data_devolucao + "]";
	}
}
