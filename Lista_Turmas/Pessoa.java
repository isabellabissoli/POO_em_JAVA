abstract class Pessoa implements Comparable<Pessoa> {
    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int compareTo(Pessoa pessoa) {
        int nomeComparison = nome.compareTo(pessoa.nome);
        if (nomeComparison != 0) {
            return nomeComparison;
        }
        return cpf.compareTo(pessoa.cpf);
    }
}