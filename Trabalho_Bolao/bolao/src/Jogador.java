import java.util.Scanner;

public class Jogador extends Pessoa {
   protected String pix;

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix=pix;
    }

    public Jogador(){
        super();
        Scanner s = new Scanner(System.in);

        System.out.print("Pix: ");
        this.pix = s.nextLine();
        
        
        System.out.println();
        System.out.println("Jogador Cadastrado!");
        
    }
    


	public void listarDados() {
        // super.listarDados();
        // System.out.println("Pix: " + this.pix);
        System.out.println(this.getNome() + " CPF " + super.getCpf() + " PIX: " + this.getPix());
        
        
    }
}

