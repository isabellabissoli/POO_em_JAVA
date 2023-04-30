import java.util.ArrayList;
import java.util.Scanner;

import javax.management.BadAttributeValueExpException;



public class Menu {
    public static int menuInicial(){
        Scanner s = new Scanner(System.in);

        System.out.println("Bem vindo ao Bolão! Escolha uma Opção: ");
        System.out.println("1. Cadastrar Jogador ");
        System.out.println("2. Cadastrar Aposta ");
        System.out.println("3. Inserir Sorteio e listar vencedores ");
        System.out.println("4. Sair ");
        
        return s.nextInt();
    }
    public static void menuCadastroJogador(Bolao b){
        
        b.cadastrarJogador();
        
    }

    public static void menuCadastroAposta(Bolao b){
        b.cadastrarAposta();

    }
    public static void menuSorteio(Bolao b){
        b.inserirSorteio();

    }
    
    public static void main(String[] args)  {
        Bolao b = new Bolao();
        
        
        int op = menuInicial();
        
        while(op != 0 && op != 4){
            if (op == 1){
                menuCadastroJogador(b);
            } 
            if(op == 2){
                menuCadastroAposta(b);

            } 
            if(op == 3){
                menuSorteio(b);
            }

            if(op == 4){
                System.exit(0);
            }
            
            op = menuInicial();
        }
        
    
       
    }
}
        

 
