package entities;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
	
    private String nome;
    private String codigo;
    private List<Professor> listaProfessores = new ArrayList<>();

    public Disciplina(String nome, String codigo, Professor professor) {
        this.nome = nome;
        this.codigo = codigo;
        this.addProfessor(professor);
    }

    public boolean addProfessor(Professor professor) {
        if (professor != null && listaProfessores.size() < 10) {
            listaProfessores.add(professor);
            return true;
        }
        return false;
    }
    
	public boolean removerProfessor(Professor professor) {
		
		if (listaProfessores.size() > 1 && listaProfessores.contains(professor)) {
			listaProfessores.remove(professor);
			return true;
		}
		return false;
	}

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Professor> getProfessores() {
        return listaProfessores;
    }
}
