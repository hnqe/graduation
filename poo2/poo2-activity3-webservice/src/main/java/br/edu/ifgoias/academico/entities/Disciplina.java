package br.edu.ifgoias.academico.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddisciplina;

    @Column(name = "nomedisciplina", nullable = false)
    private String nomedisciplina;

    @Column(name = "cargahoraria", nullable = false)
    private Integer cargahoraria;

    public Disciplina() {
    }

    public Disciplina(Integer iddisciplina, String nomedisciplina, Integer cargahoraria) {
        this.iddisciplina = iddisciplina;
        this.nomedisciplina = nomedisciplina;
        this.cargahoraria = cargahoraria;
    }

    public Integer getIddisciplina() {
        return iddisciplina;
    }

    public void setIddisciplina(Integer iddisciplina) {
        this.iddisciplina = iddisciplina;
    }

    public String getNomedisciplina() {
        return nomedisciplina;
    }

    public void setNomedisciplina(String nomedisciplina) {
        this.nomedisciplina = nomedisciplina;
    }

    public Integer getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(Integer cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddisciplina, nomedisciplina, cargahoraria);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Disciplina other = (Disciplina) obj;
        return Objects.equals(iddisciplina, other.iddisciplina) && Objects.equals(nomedisciplina, other.nomedisciplina)
                && Objects.equals(cargahoraria, other.cargahoraria);
    }

    @Override
    public String toString() {
        return "Disciplina [iddisciplina=" + iddisciplina + ", nomedisciplina=" + nomedisciplina
                + ", cargahoraria=" + cargahoraria + "]";
    }
}
