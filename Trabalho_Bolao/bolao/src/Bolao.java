import java.util.ArrayList;
import java.util.Scanner;


public class Bolao {
    private ArrayList<Aposta> apostas;
    private ArrayList<Jogador> jogadores;

    public ArrayList<Aposta> getAposta(){
        return apostas;
    }

    public ArrayList<Jogador> getJogadores(){
        return jogadores;
    }

    public void setAposta(ArrayList<Aposta> aposta){
        this.apostas = aposta;
    }

    public void setJogadores(ArrayList<Jogador> jogadores){
        this.jogadores = jogadores;
    }
    
    public Bolao(){
        this.jogadores = new ArrayList<>();
        this.apostas = new ArrayList<>();
    }

    
    public void cadastrarJogador(){
        Scanner s = new Scanner(System.in);
        Jogador jo = new Jogador();
        jogadores.add(jo);
        System.out.println(jogadores);
    }

    

    public void cadastrarAposta(){
        Aposta aposta = new Aposta();
        aposta.inserirOrganizador(jogadores);
        aposta.inserirJogadores(jogadores);
        aposta.inserirNumeros();
        this.apostas.add(aposta);

    }

    //Escrever os números sorteados e comparar
    void inserirSorteio(){
        if(apostas.isEmpty()){
            System.out.println("Cadastre aposta na opção 2");
            System.out.println();
        } else {
            Scanner s = new Scanner(System.in);
            ArrayList<Integer> numeros_sorteados = new ArrayList<>();
            
            
            System.out.println("Digite os 6 números da aposta:");
            for(int i = 0; i < 6; i++){
                numeros_sorteados.add(s.nextInt());
                
            }
            System.out.println("Numeros adicionados!");
            // Aposta.vencedora(numeros_sorteados);

            System.out.println("Digite o valor do premio: ");
            double premio = s.nextDouble();
            ArrayList<Aposta> aposta_vencedora = vencedoras(numeros_sorteados);
            if(aposta_vencedora.isEmpty()){
                System.out.println("Sem vencedores :(");
                System.out.println();
            } else {
                for (Aposta a : aposta_vencedora){
                    a.listarVencedores(premio);
                }
                System.out.println("Sorteio finalizado! Parabéns aos vencedores!");
                System.out.println();
                


            }
        }
    }
    
    //inserir na Array os jogadores que ganharam
    private ArrayList<Aposta> vencedoras(ArrayList<Integer> sorteado){
        ArrayList<Aposta> sorteados = new ArrayList<>();

        for(Aposta a: this.apostas){
            if(a.vencedora(sorteado)){
                sorteados.add(a);
            }
        
        }
        return sorteados;
    }
}
