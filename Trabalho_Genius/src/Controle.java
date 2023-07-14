import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

public class Controle {
    private ArrayList<Jogador> jogadores;
    private Jogador atual;
    private String correta;


    public Controle() throws IOException {
        this.correta = "";
        this.jogadores = new ArrayList<>();
        try {
            carregarArq();
        } catch (FileNotFoundException e) {
            this.salvarArq();
        }
        this.atual = bemvindo();

    }

    public int sortear() {

        Random r = new Random();
        int numero = r.nextInt(9) + 1;
        this.correta += String.valueOf(numero);
        return numero;

    }


    public Jogador bemvindo() {

        try {
            String nome = JOptionPane.showInputDialog(null, "Qual é o seu nome?", "Bem Vindo!", JOptionPane.PLAIN_MESSAGE);
            while (nome == null || nome.equals("")) {
                nome = JOptionPane.showInputDialog(null, "Nome Invalido", "Bem-Vindo!", JOptionPane.PLAIN_MESSAGE);
            }
            return localizarJogador(nome);

        } catch (InputMismatchException e) {
            String nome = JOptionPane.showInputDialog(null, "Qual é o seu nome?", "Nome Inválido!", JOptionPane.PLAIN_MESSAGE);
            return localizarJogador(nome);

        } catch (NumberFormatException e) {
            String nome = JOptionPane.showInputDialog(null, "Qual é o seu nome?", "Nome Inválido!", JOptionPane.PLAIN_MESSAGE);
            return localizarJogador(nome);
        }

    }

    public Jogador localizarJogador(String nome) {
        Jogador controle = null;
        for (Jogador j : this.jogadores) {
            if (nome.equals(j.getNome())) {
                controle = j;
                break;
            }
        }

        if (controle != null) {
            return controle;
        } else {
            Jogador jogador = new Jogador(nome);
            this.jogadores.add(jogador);
            return jogador;
        }
    }

    public boolean errou() {
        try {
            int cont = 0;
            int x = JOptionPane.showConfirmDialog(null, "Você acertou " + (this.correta.length() - 1) + " números. Deseja começar um novo jogo?", " FIM DE JOGO ", JOptionPane.YES_NO_OPTION);
            if (x == 0) {

                this.atual = bemvindo();
                this.correta = "";
                jogo();
                return true;

            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
            ;
        }
        return false;
    }

    public Jogador recordista() {
        if (this.jogadores.isEmpty()) {
            return null; // Retorna null se a lista de jogadores estiver vazia
        }
        Jogador recordista = this.jogadores.get(0);
        int pontuacaoMaxima = recordista.getPontos();

        for (Jogador j : this.jogadores) {
            int pontuacaoAtual = j.getPontos();
            if (pontuacaoAtual > pontuacaoMaxima) {
                pontuacaoMaxima = pontuacaoAtual;
                recordista = j;

            }
        }

        return recordista;
    }


    public void bye() {

        JOptionPane.showMessageDialog(null, "Record da sessão: " + this.atual.getNome() + " - " + this.atual.getPontos() + " Geral: " + this.recordista().getNome() + " - " + this.recordista().getPontos() + " ponto(s)", "RECORDES", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);


    }


    public void salvarArq() throws FileNotFoundException {
        try {
            FileWriter f = new FileWriter("placar.txt");
            BufferedWriter buff = new BufferedWriter(f);
            buff.write(String.valueOf(this.jogadores.size()) + "\n");

            for (Jogador j : this.jogadores) {
                j.salvarArq(buff);
            }

            buff.close();
        } catch (IOException e) {
            System.out.println("Erro ao Salvar");
            ;
        }


    }

    public void carregarArq() throws FileNotFoundException {

        try {
            FileReader f = new FileReader("placar.txt");
            BufferedReader buff = new BufferedReader(f);

            int leituraString = Integer.parseInt(buff.readLine());

            for (int i = 0; i < leituraString; i++) {
                Jogador jogador = new Jogador(buff);
                this.jogadores.add(jogador);

            }
            buff.close();


        } catch (IOException e) {
            System.out.println("ERRO AO CARREGAR ARQUIVO. Outro irá ser criado...");
            this.salvarArq();
        } catch (NumberFormatException e) {
            System.out.println("O placar está vazio");
        }
    }


    public void jogo() throws FileNotFoundException {

        boolean continua = true;
        Integer sorteado = sortear();
        while (continua) {
            try {
                int numero_digitado = Integer.parseInt(JOptionPane.showInputDialog(null, "O novo número é: " + sorteado, "Digite a sequencia completa", JOptionPane.PLAIN_MESSAGE));
                String numeros = String.valueOf(numero_digitado);
                if (numeros.equals(this.correta)) {
                    sorteado = sortear();
                    continua = true;
                } else {
                    int pontos_total = (this.correta.length() - 1);
                    atual.atualizarRecord(pontos_total);
                    salvarArq();
                    continua = false;
                }
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo Não encontrado");
            } catch (NumberFormatException e) {
                bye();
                System.exit(0);
            }
        }

        errou();
        continua = false;
    }
}










