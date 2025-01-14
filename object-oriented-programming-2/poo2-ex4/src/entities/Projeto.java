package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Projeto {

	private String nome;
	private Date dtInicio;
	private Date dtTermino;
	private List<Contratacao> listaContratacao = new ArrayList<>();
	
	public Projeto(String nome, Date dtInicio, Date dtTermino) {
		this.nome = nome;
		this.dtInicio = dtInicio;
		this.dtTermino = dtTermino;
	}

	public String getNome() {
		return nome;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public Date getDtTermino() {
		return dtTermino;
	}

	public List<Contratacao> getListaContratacao() {
		return listaContratacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtInicio, dtTermino, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		return Objects.equals(dtInicio, other.dtInicio) && Objects.equals(dtTermino, other.dtTermino)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Projeto [nome=" + nome + ", dtInicio=" + dtInicio + ", dtTermino=" + dtTermino + "]";
	}
	
	public boolean adicionarContratacao(Contratacao contratacao) {

		if ( contratacao != null && !listaContratacao.contains(contratacao)) {
			
			listaContratacao.add(contratacao);
			return true;
		} 
		return false;
	}
	
	public boolean removerContratacao(Contratacao contratacao) {
		
		if ( contratacao != null && listaContratacao.size() > 0 && listaContratacao.contains(contratacao)) {
			listaContratacao.remove(contratacao);
			return true;
		} 
		return false;
	}
	
	public void listarContratacoes() {
		
		System.out.println();
		if (listaContratacao.isEmpty()) {
			System.out.println("Projeto " + nome + " não possui contratações!");
		} else {
			System.out.println("***Contratações***");
			System.out.println("Projeto: \n" + "\t" + nome);
			System.out.println("Funcionario(s):");
			for (Contratacao contratacao : listaContratacao) {
				System.out.println("\t" + contratacao.getFuncionario().getNome()
									+ " [Status: " + contratacao.getStatus() + "]"
						);
			}
		}
	}
}
