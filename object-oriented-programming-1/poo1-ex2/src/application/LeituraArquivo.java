package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LeituraArquivo {
	public static void main(String[] args) {
        File arquivo = new File("teste.txt");
        
        // DAR REFRESH NA PASTA RAIZ CASO ELE NAO EXISTA PARA APAREFCER O ARQUIVO TEXT.
        if (!arquivo.exists()) {
            try {
                // Cria o arquivo
                arquivo.createNewFile();
                // Escreve alguns dados de exemplo no arquivo
                try (FileWriter writer = new FileWriter(arquivo)) {
                    writer.write("Maria;10.0;6.9\n");
                    writer.write("João;8.5;7.3\n");
                } catch (IOException e) {
                    System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
                }
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo: " + e.getMessage());
            }
        }


        try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");

                if (campos.length == 3) {
                    String nome = campos[0].trim();
                    double nota1 = Double.parseDouble(campos[1].trim());
                    double nota2 = Double.parseDouble(campos[2].trim());

                    double media = (nota1 + nota2) / 2;

                    System.out.println("Nome: " + nome);
                    System.out.println("Nota 1: " + nota1);
                    System.out.println("Nota 2: " + nota2);
                    System.out.println("Média: " + media);
                    System.out.println("--------------------");
                } else {
                    System.out.println("Linha inválida: " + linha);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + arquivo.getAbsolutePath());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número: " + e.getMessage());
        }
    }
}