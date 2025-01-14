package entities;

public class Agenda {
	private Pessoa[] pessoas;
    private int qtdPessoas;

    public Agenda(int tamanho) {
        pessoas = new Pessoa[tamanho];
        qtdPessoas = 0;
    }

    public void armazenaPessoa(String nome, int idade, double altura) {
        if (qtdPessoas < pessoas.length) {
            pessoas[qtdPessoas++] = new Pessoa(nome, idade, altura);
        } else {
            System.out.println("Agenda cheia!");
        }
    }

    public void removePessoa(String nome) {
        for (int i = 0; i < qtdPessoas; i++) {
            if (pessoas[i].getNome().equals(nome)) {
                for (int j = i; j < qtdPessoas - 1; j++) {
                    pessoas[j] = pessoas[j + 1];
                }
                qtdPessoas--;
                return;
            }
        }
        System.out.println("Pessoa não encontrada!");
    }

    public int buscaPessoa(String nome) {
        for (int i = 0; i < qtdPessoas; i++) {
            if (pessoas[i].getNome().equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    public void imprimeAgenda() {
        for (int i = 0; i < qtdPessoas; i++) {
            pessoas[i].imprimirDados();
        }
    }

    public void imprimePessoa(int posicao) {
        if (posicao >= 0 && posicao < qtdPessoas) {
            pessoas[posicao].imprimirDados();
        } else {
            System.out.println("Posição inválida!");
        }
    }
}
