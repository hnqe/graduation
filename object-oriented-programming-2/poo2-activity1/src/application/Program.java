package application;

import java.time.LocalDate;

import entities.Evento;
import entities.EventoPeriodico;
import entities.EventoRecorrente;
import entities.EventoUnico;
import entities.Participante;
import enums.DiaSemana;
import exceptions.DataInvalidaException;

public class Program {

    public static void main(String[] args) {
    	try {
            Evento hackathon = new EventoUnico("Hackathon de Inovação", "Competição de desenvolvimento de software", LocalDate.of(2024, 3, 10), LocalDate.of(2024, 3, 12), "Espaço InovarTech");
            Evento conferenciaDevOps = new EventoRecorrente("Conferência DevOps", "Conferência anual focada em práticas DevOps", LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 17), "Centro de Convenções TechNova");
            Evento workshopIA = new EventoPeriodico("Workshop de Inteligência Artificial", "Série de workshops sobre IA e machine learning", LocalDate.of(2024, 9, 1), LocalDate.of(2024, 11, 1), "Online via TechEdu Platform", DiaSemana.SEXTA);

            Participante participante1 = new Participante("Dory Gonzaga");
            Participante participante2 = new Participante("Henrique Sampaio");

            hackathon.adicionarParticipante(participante1);
            conferenciaDevOps.adicionarParticipante(participante2);
            workshopIA.adicionarParticipante(participante1);
            workshopIA.adicionarParticipante(participante2);

            hackathon.imprimirDetalhes();
            hackathon.imprimirListaParticipantes();

            conferenciaDevOps.imprimirDetalhes();
            conferenciaDevOps.imprimirListaParticipantes();

            workshopIA.imprimirDetalhes();
            workshopIA.removerParticipante(participante2);
            workshopIA.imprimirListaParticipantes();

            System.out.println("Tentando criar um evento com data de fim anterior à data de início...");
            Evento eventoDataInvalida = new EventoUnico("Evento Data Inválida", "Este evento não será criado", LocalDate.of(2024, 12, 1), LocalDate.of(2024, 11, 30), "Local Fantasma");

        } catch (DataInvalidaException e) {
            System.err.println("Erro ao criar evento: " + e.getMessage());
        }
    }
}
