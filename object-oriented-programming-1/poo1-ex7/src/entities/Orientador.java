package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Orientador {

	private String nome;
	private List<Aluno> alunosOrientados = new ArrayList<>();

	public Orientador(String nome) {
		this.nome = nome;
	}
	
    public boolean addAluno(Aluno aluno) {
        if (aluno != null && !alunosOrientados.contains(aluno) && alunosOrientados.size() < 3) {
            alunosOrientados.add(aluno);
            aluno.addOrientador(this);
            return true;
        }
        return false;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Aluno> getAlunosOrientados() {
		return alunosOrientados;
	}

	@Override
	public String toString() {
		return "Orientador [nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orientador other = (Orientador) obj;
		return Objects.equals(nome, other.nome);
	}	
}
