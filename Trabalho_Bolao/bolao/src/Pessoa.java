import java.util.Scanner; 

public class Pessoa {
    protected String nome;
    protected String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public Pessoa() {
        Scanner s = new Scanner(System.in);
        System.out.printf("Nome: ");
        this.nome = s.nextLine();
        System.out.printf("Cpf: ");
        this.cpf = s.nextLine();

    }

    Pessoa(String n, String c){
        this.nome = n;
        this.cpf = c;
    }

    void listarDados(){
        System.out.println(this.nome);
        System.out.println(this.cpf);
    }

    
}
