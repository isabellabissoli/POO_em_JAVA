import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Turma implements Comparable<Turma> {
    private String disciplina;
    private int ano;
    private int semestre;
    private Professor professor;
    private List<AlunoNota> alunosNotas;

    public Turma(String disciplina, int ano, int semestre, Professor professor) {
        this.disciplina = disciplina;
        this.ano = ano;
        this.semestre = semestre;
        this.professor = professor;
        this.alunosNotas = new ArrayList<>();
    }

    public void adicionarAlunoNota(AlunoNota alunoNota) {
        alunosNotas.add(alunoNota);
    }

    public String getDisciplina() {
        return disciplina;
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<AlunoNota> getAlunosNotas() {
        return alunosNotas;
    }


    public int compareTo(Turma turma) {
        if (ano != turma.ano) {
            return Integer.compare(turma.ano, ano);
        }
        if (semestre != turma.semestre) {
            return Integer.compare(turma.semestre, semestre);
        }
        int disciplinaComparison = disciplina.compareTo(turma.disciplina);
        if (disciplinaComparison != 0) {
            return disciplinaComparison;
        }
        return professor.compareTo(turma.professor);
    }

    public List<AlunoNota> getNotasOrdenadas() {
        List<AlunoNota> notasOrdenadas = new ArrayList<>(alunosNotas);
        Collections.sort(notasOrdenadas);
        return notasOrdenadas;
    }

}