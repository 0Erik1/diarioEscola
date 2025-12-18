package parte1;

import java.util.ArrayList;

public class Diario {
    private int codigo;
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private Disciplina disciplina;
    private CalcularMedia calcular;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Professor getProfessor() {
        return disciplina.getProfessor();
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public CalcularMedia getCalcular() {
        return calcular;
    }

    public void setCalcular(CalcularMedia calcularMedia) {
        this.calcular = calcularMedia;
    }

    public void calcularMedia() {
        if (disciplina != null && calcular != null) {
            for (Aluno a : alunos) {
                Disciplina d = a.searchDisciplina(disciplina.getNome());

                if (d != null) {
                    double media = calcular.calc(d.getN1(), d.getN2());

                    d.setMedia(media);
                    d.setAprovacao(media >= 6.0);
                }
            }
        }
    }
}
