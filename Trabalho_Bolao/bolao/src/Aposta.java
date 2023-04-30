import java.util.ArrayList;
import java.util.Scanner;


public class Aposta{
    private static ArrayList<Integer> numeros;
    private Jogador organizador;
    private ArrayList<Jogador> jogadores;

    public ArrayList<Integer> getNumeros(){
        return numeros;
    }

    public ArrayList<Jogador> getJogadores(){
        return jogadores;
    }

    public Jogador getOrganizador(){
        return organizador;
    }

    public void setNumeros(ArrayList<Integer> numeros){
        this.numeros = numeros;
    }

    public void setJogadores(ArrayList<Jogador> jogadores){
        this.jogadores = jogadores;
    }

    public void setOrganizador(Jogador organizador){
        this.organizador = organizador;
    }
    
    public Aposta() {
        this.numeros = new ArrayList<Integer>();
        this.jogadores = new ArrayList<>();

    }
    
    //Compara os numeros com os sorteados
    static boolean vencedora (ArrayList<Integer> sorteado){
        boolean result = true;
        for(int i = 0; i < 6; i++){
            result = result && numeros.contains(sorteado.get(i));
        }
        return result;
    }
    

    
    //Inserir organizador na aposta
    public void inserirOrganizador(ArrayList<Jogador> jogadores) {
        if(jogadores.isEmpty()){
            System.out.println("Adicione Jogadores na opção 1");
            System.out.println();
           
        }
        else{
            Scanner sc = new Scanner(System.in);
            
            
            System.out.println("Escolha o organizador da aposta:");
            for (Jogador pessoa : jogadores){
                System.out.println(pessoa.getNome() + " CPF: " + pessoa.getCpf());
            }
            
            

            String cpf = sc.nextLine();
            
            for (Jogador pessoa : jogadores) {
                if (pessoa.getCpf().equals(cpf)) {
                    this.organizador = pessoa;
                    System.out.println("Organizador da aposta: " + this.organizador.getNome());
                    System.out.println();
                    break;
                } else {
                    System.out.println("CPF inválido! O organizador da aposta não foi escolhido.");
                    System.out.println();
                    

                }
            }
        }
    }       
    

    //Inserir jogadores na aposta
    public void inserirJogadores(ArrayList<Jogador> jogadores) {
        if(jogadores.isEmpty()){
            return;
        } else {
            Scanner s = new Scanner(System.in);
            System.out.println("Quantos jogadores irão Participar?");
            int n = s.nextInt();
            if(n > jogadores.size()){
                System.out.println("Aposta não possui essa quantidade de jogadores!");
                System.out.println();
            } else {
                System.out.println("Jogadores Cadastrados: ");
        
                for (int i = 0; i < jogadores.size(); i++) {
                    jogadores.get(i).listarDados();

                }  
                
                for(int i = 1; i <= n; i++){
                    Scanner cpf_jogador = new Scanner(System.in);
                    System.out.println("Digite o CPF do " + i + "° Jogador");
                    String cpf = cpf_jogador.nextLine();
                    
                    for (Jogador pessoa : jogadores) {
                        if (pessoa.getCpf().equals(cpf)) {
                            this.jogadores.add(pessoa);
                            System.out.println("Participante: " + pessoa.getNome());
                            System.out.println();
                            break;
                        }
                    }
                } 
                
                
            } 
        }   
    }

    //listar os vencedores da aposta e quanto cada um ganhou
    public void listarVencedores(double premio){
    
        double premio_organizador = (10*premio)/100;
        double premio_jogadores = (premio - premio_organizador)/ this.jogadores.size() - 1;
        System.out.println("O/A organizador/a " + this.organizador.getNome() + " receberá R$" + premio_organizador + " pelo pix: " + this.organizador.getPix());
        for(Jogador jg: jogadores){
            System.out.println("O jogador " + jg.getNome() +" receberá R$"+ premio_jogadores + " pelo pix: " + jg.getPix());
            System.out.println();
        }

}

    //A pessoa insere os números que desaja apostar
    public void inserirNumeros(){
        if(jogadores.isEmpty()){
            return;
        } else {

            Scanner s = new Scanner(System.in);
            
            
            System.out.println("Quantos números deseja digitar? ");
            int n = s.nextInt();

            if(n < 6 || n > 15){
                System.out.println("Quantidade inválida!");
                System.out.println();
            } else {

                System.out.println("Digite os números da aposta:");
                
                for(int i = 0; i < n; i++){
                    int num = s.nextInt();
                    if (num > 60){
                    System.out.println("O máximo é 60. Número inválido!");
                    } else{
                    numeros.add(num);
                    }
                    
                }
                System.out.println("Numeros adicionados!");
                System.out.println();
            }
        }

    }

}



