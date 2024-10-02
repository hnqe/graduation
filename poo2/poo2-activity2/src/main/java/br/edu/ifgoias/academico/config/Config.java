package br.edu.ifgoias.academico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.edu.ifgoias.academico.repositories.CursoRepository;
import br.edu.ifgoias.academico.repositories.DisciplinaRepository;

@Configuration
public class Config implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRep;

    @Autowired
    private DisciplinaRepository disciplinaRep;

    @Override
    public void run(String... args) throws Exception {

//        // Inserir cursos de teste OU TESTAR NO POSTMAN
//        Curso c1 = new Curso(null, "Spring Boot");
//        Curso c2 = new Curso(null, "Microservices");
//
//        cursoRep.save(c1);
//        cursoRep.save(c2);
//
//        // Inserir disciplinas de teste
//        Disciplina d1 = new Disciplina(null, "Desenvolvimento Web", 60);
//        Disciplina d2 = new Disciplina(null, "Estrutura de Dados", 80);
//        Disciplina d3 = new Disciplina(null, "Engenharia de Software", 70);
//
//        disciplinaRep.save(d1);
//        disciplinaRep.save(d2);
//        disciplinaRep.save(d3);

        // Exibe o número de registros inseridos para cursos e disciplinas
        System.out.println("Qtde Cursos: " + cursoRep.count());
        System.out.println("Qtde Disciplinas: " + disciplinaRep.count());
    }
}
