/*class Aluno extends Pessoa implements Comparable<Aluno> {
    private String matricula;

    public Aluno(String nome, String cpf, String matricula) {
        super(nome, cpf);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    
}*/

public class Aluno extends Pessoa {

    private String matricula;

    public Aluno(String nome, String cpf, String matricula) {
        super(nome, cpf);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public int compareTo(Aluno outroAluno) {
        int comparacaoNome = this.getNome().compareTo(outroAluno.getNome());
        if (comparacaoNome != 0) {
            return comparacaoNome;
        } else {
            return this.matricula.compareTo(outroAluno.getMatricula());
        }
    }
}