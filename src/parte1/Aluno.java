package parte1;

import java.util.ArrayList;

public class Aluno extends Pessoa {
    private int matricula;
    private ArrayList<Disciplina> disciplinas = new ArrayList<>();

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Disciplina searchDisciplina(String nomeDisicplina) {
        for (Disciplina d : disciplinas) {
            if (d.getNome().equalsIgnoreCase(nomeDisicplina)) {
                return d;
            }
        }

        return null;
    }

    public void addDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }
}
