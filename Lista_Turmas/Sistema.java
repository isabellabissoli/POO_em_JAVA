import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Sistema {
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Turma> turmas;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public void carregarDados() throws ProfessorNotFoundException {
        try {
            carregarAlunos();
            carregarProfessores();
            carregarTurmas();
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
        }
    }

    private void carregarAlunos() throws FileNotFoundException {
        try {
            File arquivoAlunos = new File("alunos.txt");
            Scanner scanner = new Scanner(arquivoAlunos);

            int quantidadeAlunos = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < quantidadeAlunos; i++) {
                String nome = scanner.nextLine();
                String cpf = scanner.nextLine();
                String matricula = scanner.nextLine();
                Aluno aluno = new Aluno(nome, cpf, matricula);
                alunos.add(aluno);
            }

            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("arquivo não encontrado");
        } catch (NumberFormatException e){
            System.out.println("erro ao converter a quantidade de alunos");
        }
    }

    private void carregarProfessores() throws FileNotFoundException {
        try {
            File arquivoProfessores = new File("professores.txt");
            Scanner scanner = new Scanner(arquivoProfessores);

            int quantidadeProfessores = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < quantidadeProfessores; i++) {
                String nome = scanner.nextLine();
                String cpf = scanner.nextLine();
                Professor professor = new Professor(nome, cpf);
                professores.add(professor);
            }

            scanner.close();
        }  catch (FileNotFoundException e){
        System.out.println("arquivo não encontrado");
        } catch (NumberFormatException e){
        System.out.println("erro ao converter a quantidade de alunos");
        }
    }

    private void carregarTurmas() throws FileNotFoundException, ProfessorNotFoundException {
        try {
            File arquivoTurmas = new File("turmas.txt");
            Scanner scanner = new Scanner(arquivoTurmas);

            int quantidadeTurmas = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < quantidadeTurmas; i++) {
                String disciplina = scanner.nextLine();
                int ano = Integer.parseInt(scanner.nextLine());
                int semestre = Integer.parseInt(scanner.nextLine());
                String cpfProfessor = scanner.nextLine();
                Professor professor = buscarProfessorPorCpf(cpfProfessor);

                int quantidadeAlunos = Integer.parseInt(scanner.nextLine());
                for (int j = 0; j < quantidadeAlunos; j++) {
                    String matricula = scanner.nextLine();
                    double nota = Double.parseDouble(scanner.nextLine());
                    try {
                        Aluno aluno = buscarAlunoPorMatricula(matricula);
                        AlunoNota alunoNota = new AlunoNota(aluno, nota);
                        Turma turma = buscarTurma(disciplina, ano, semestre, professor);
                        turma.adicionarAlunoNota(alunoNota);
                    } catch (AlunoNotFoundException e) {
                        System.out.println("Aviso: Aluno não encontrado.");
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e){
        System.out.println("arquivo não encontrado");
        } catch (NumberFormatException e){
        System.out.println("erro ao converter a quantidade de alunos");
        }
    }

    private Aluno buscarAlunoPorMatricula(String matricula) throws AlunoNotFoundException {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        throw new AlunoNotFoundException();
    }

    private Professor buscarProfessorPorCpf(String cpf) throws ProfessorNotFoundException {
        for (Professor professor : professores) {
            if (professor.getCpf().equals(cpf)) {
                return professor;
            }
        }
        throw new ProfessorNotFoundException();
    }

    private Turma buscarTurma(String disciplina, int ano, int semestre, Professor professor) {
        for (Turma turma : turmas) {
            if (turma.getDisciplina().equals(disciplina) && turma.getAno() == ano
                    && turma.getSemestre() == semestre && turma.getProfessor().equals(professor)) {
                return turma;
            }
        }
        Turma novaTurma = new Turma(disciplina, ano, semestre, professor);
        turmas.add(novaTurma);
        return novaTurma;
    }

    public void exibirNotas() {
        ordenarTurmas();

        try {
            FileWriter arquivoSaida = new FileWriter("saida.txt");
            PrintWriter gravador = new PrintWriter(arquivoSaida);

            for (Turma turma : turmas) {
                gravador.println(turma.getDisciplina() + " (" + turma.getAno() + "/" + turma.getSemestre() + ") - "
                        + turma.getProfessor().getNome());
                List<AlunoNota> notas = turma.getNotasOrdenadas();
                for (AlunoNota alunoNota : notas) {
                    Aluno aluno = alunoNota.getAluno();
                    gravador.println("- " + aluno.getNome() + " (" + aluno.getMatricula() + "): " + alunoNota.getNota());
                }
            }
            System.out.println("Dados salvos no arquivo saida.txt");
            arquivoSaida.close();

        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de saída.");
        }
    }

    private void ordenarTurmas() {
        Collections.sort(turmas);
    }

}

