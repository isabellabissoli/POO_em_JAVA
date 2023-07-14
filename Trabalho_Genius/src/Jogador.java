import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Jogador {
    protected String nome;
    protected Integer pontos;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public Integer getPontos(){
        return pontos;
    }

    public void setPontos(Integer pontos){
        this.pontos = pontos;
    }

    Jogador (String nome){
        this.nome = nome;
        this.pontos = 0;
    }
    Jogador(BufferedReader b){
        try {
            this.setNome(b.readLine());
            this.setPontos(Integer.parseInt(b.readLine()));
        } catch (IOException e){
            System.out.println("ERRO");
        }

    }

    public Integer atualizarRecord(Integer p){
        if (p > this.pontos){
            this.pontos = p;
            this.setPontos(p);
        }
        return pontos;
    }

    public void salvarArq(BufferedWriter b) throws IOException {
        b.write(this.nome + "\n");
        b.write(this.pontos + "\n");
    }

}

