class AlunoNota implements Comparable<AlunoNota> {
    private Aluno aluno;
    private double nota;

    public AlunoNota(Aluno aluno, double nota) {
        this.aluno = aluno;
        this.nota = nota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public double getNota() {
        return nota;
    }

    public int compareTo(AlunoNota alunoNota) {
        return Double.compare(alunoNota.nota, nota);
    }
}