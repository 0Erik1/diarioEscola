import projetoEscola.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    static Scanner sc = new Scanner(System.in);
    static List<Aluno> alunos = new ArrayList<>();
    static List<Professor> professores = new ArrayList<>();
    static List<Disciplina> disciplinas = new ArrayList<>();
    static List<Diario> diarios = new ArrayList<>();
    static int codigoTurma = 0;

    public static void main(String[] args) {
            int opcao = -1;

        do {
            System.out.println("====Escola====");
            System.out.println("1. cadastrar aluno");
            System.out.println("2. cadastrar professor");
            System.out.println("3. cadastrar disciplina");
            System.out.println("4. Diario");
            System.out.println("0. Sair");
            System.out.println("==============");



            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarProfessor();
                    break;
                case 3:
                    cadastrarDisciplina();
                    break;
                case 4:
                    diario();
                    break;
                case 5:
                    mostrarProfessores();
                    break;
                case 6:
                    mostrarDisciplinas();
                    break;
                case 7:
                    mostrarAlunos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }while (opcao != 0);
    }

    public static void cadastrarAluno(){
        Aluno a = new Aluno();

        System.out.println("==============");
        System.out.println("Nome:");
        String nome = sc.nextLine();
        a.setNome(nome);

        System.out.println("Matricula:");
        int matricula = Integer.parseInt(sc.nextLine());
        a.setMatricula(matricula);

        System.out.println("Data de nascimento. EX: AAAA-MM-DD:");
        LocalDate data = LocalDate.parse(sc.nextLine());
        a.setNascimento(data);

        alunos.add(a);
        System.out.println("Aluno cadastrado...");
    }

    public static void cadastrarProfessor(){
        Professor p = new Professor();

        System.out.println("==============");
        System.out.println("Nome:");
        String nome = sc.nextLine();
        p.setNome(nome);

        System.out.println("Siape:");
        int siape = Integer.parseInt(sc.nextLine());
        p.setSiape(siape);

        System.out.println("Formação:");
        String formacao = sc.nextLine();
        p.setFormacao(formacao);

        System.out.println("Data de nascimento. EX: AAAA-MM-DD:");
        LocalDate data = LocalDate.parse(sc.nextLine());
        p.setNascimento(data);

        professores.add(p);
        System.out.println("Professor(a) cadastrado(a)...");
    }

    public static void cadastrarDisciplina(){
        Disciplina d = new Disciplina();

        System.out.println("==============");
        System.out.println("Nome:");
        String nome = sc.nextLine();
        d.setNome(nome);

        mostrarProfessores();
        System.out.println("Digite o índice do professor(a) escolhido(a):");
        int professor = Integer.parseInt(sc.nextLine());
        d.setProfessor(professores.get(professor));

        disciplinas.add(d);
    }

    public static void matricularAluno(){
        System.out.println("==============");

        if (diarios.isEmpty()) {
            System.out.println("Não há turmas criadas.");
            return;
        }

        System.out.println("Escolha a turma pelo código:");
        mostrarTurmas();
        int codigo = Integer.parseInt(sc.nextLine());
        Diario diario = buscarTurmaPorCodigo(codigo);
        if (diario == null) {
            System.out.println("Turma não encontrada.");
            return;
        }

        System.out.println("Escolha o aluno");
        mostrarAlunos();
        int idaluno = Integer.parseInt(sc.nextLine());
        Aluno aluno = alunos.get(idaluno);

        Disciplina discAluno = new Disciplina();
        discAluno.setNome(diario.getDisciplina().getNome());
        discAluno.setProfessor(diario.getProfessor());
        discAluno.setN1(0);
        discAluno.setN2(0);
        discAluno.setMedia(0);
        discAluno.setAprovacao(false);

        aluno.addDisciplina(discAluno);
        diario.addAluno(aluno);

        System.out.println("Aluno matriculado com sucesso na turma " + diario.getCodigo() + "...");


    }

    public static void mostrarProfessores(){
        System.out.println("==============");
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            System.out.println(i + ". Nome:" + p.getNome() + " SIAPE:" + p.getSiape() + " Formação:" + p.getFormacao());
        }
    }

    public static void mostrarDisciplinas(){
        System.out.println("==============");
        for(int i = 0; i < disciplinas.size(); i++){
            Disciplina d = disciplinas.get(i);
            System.out.println(i + ". Nome:" + d.getNome() + " Professor:" + d.getProfessor().getNome());
        }
    }

    public static void mostrarAlunos(){
        System.out.println("==============");
        for(int i = 0; i< alunos.size(); i++){
            Aluno a = alunos.get(i);
            System.out.println(i + ". Nome:" + a.getNome() + " Matricula:" + a.getMatricula() +
                    " Nascimento:" + a.getNascimento() + " Disciplinas:" + a.getDisciplinas()) ;
        }
    }

    public static void mostrarTurmas() {
        System.out.println("==============");
        if (diarios.isEmpty()) {
            System.out.println("Nenhuma turma criada.");
            return;
        }

        for (Diario d : diarios) {
            System.out.println(
                    "Código: " + d.getCodigo() + " | Disciplina: " + d.getDisciplina().getNome() +
                            " | Professor: " + d.getProfessor().getNome()
            );
        }
    }

    public static void criarTurma(){
        System.out.println("==============");
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas cadastradas.");
            return;
        }

        Diario turma = new Diario();
        turma.setCodigo(codigoTurma);
        codigoTurma++;

        System.out.println("Escolha a disciplina:");
        mostrarDisciplinas();
        int idx = Integer.parseInt(sc.nextLine());
        Disciplina disc = disciplinas.get(idx);
        turma.setDisciplina(disc);
        turma.setProfessor(disc.getProfessor());



        System.out.println("Escolha o tipo de média:");
        System.out.println("1. Ponderada");
        System.out.println("2. Aritmética");

        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 1) {
            System.out.println("peso da N1: ");
            double p1 = Double.parseDouble(sc.nextLine());
            System.out.println("Peso da N2");
            double p2 = Double.parseDouble(sc.nextLine());

            mediaPonderada mp = new mediaPonderada();
            mp.setPesos(p1,p2);

            turma.setCalcular(mp);

        } else if (tipo == 2) {
            turma.setCalcular(new mediaAritmetica());
        } else {
            System.out.println("Opção inválida");
            return;
        }

        diarios.add(turma);
        System.out.println("Turma criada cod: "+ turma.getCodigo());
    }

    public static void diario(){
        System.out.println("==============");
        System.out.println("Diario");
        System.out.println("1.Criar turma");
        System.out.println("2.matricular aluno");
        System.out.println("3.mostrar diario");
        System.out.println("4.alterar nota");
        System.out.println("0.voltar");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1:
                criarTurma();
                break;
            case 2:
                matricularAluno();
                break;
            case 3:
                mostrarDiario();
                break;
            case 4:
                alterarNotas();
                break;
            case 0:
                return;
        }
    }

    public static Diario buscarTurmaPorCodigo(int codigo) {
        for (Diario d : diarios) {
            if (d.getCodigo() == codigo) {
                return d;
            }
        }
        return null;
    }

    public static void mostrarDiario(){
        if (diarios.isEmpty()) {
            System.out.println("Não há turmas criadas.");
            return;
        }

        System.out.println("Escolha a turma pelo código:");
        mostrarTurmas();
        int codigo = Integer.parseInt(sc.nextLine());

        Diario diario = buscarTurmaPorCodigo(codigo);
        if (diario == null) {
            System.out.println("Turma não encontrada.");
            return;
        }

        Disciplina d = diario.getDisciplina();

        System.out.println("\n=== DIÁRIO DA TURMA ===");
        System.out.println("Código da turma: " + diario.getCodigo());
        System.out.println("Disciplina: " + d.getNome());
        System.out.println("Professor: " + diario.getProfessor().getNome());
        System.out.println("----------------------");

        if (diario.getAlunos().isEmpty()) {
            System.out.println("Nenhum aluno matriculado nesta turma.");
            return;
        }

        for (Aluno a : diario.getAlunos()) {
            Disciplina da = a.searchDisciplina(d.getNome());

            System.out.println(
                    "Aluno: " + a.getNome() +
                            " | N1: " + da.getN1() +
                            " | N2: " + da.getN2() +
                            " | Média: " + da.getMedia() +
                            " | " + (da.getAprovacao() ? "APROVADO" : "REPROVADO")
            );
        }
    }

    public static void alterarNotas() {
        System.out.println("==============");
        if (diarios.isEmpty()) {
            System.out.println("Não há turmas criadas.");
            return;
        }

        System.out.println("Escolha a turma:");
        mostrarTurmas();
        int codigo = Integer.parseInt(sc.nextLine());

        Diario diario = buscarTurmaPorCodigo(codigo);
        if (diario == null) {
            System.out.println("Turma não encontrada.");
            return;
        }

        if (diario.getAlunos().isEmpty()) {
            System.out.println("Nenhum aluno matriculado nesta turma.");
            return;
        }

        System.out.println("Escolha o aluno:");
        for (int i = 0; i < diario.getAlunos().size(); i++) {
            System.out.println(i + " - " + diario.getAlunos().get(i).getNome());
        }

        int idxAluno = Integer.parseInt(sc.nextLine());
        Aluno aluno = diario.getAlunos().get(idxAluno);

        Disciplina da = aluno.searchDisciplina(diario.getDisciplina().getNome());

        System.out.println("Digite N1:");
        double n1 = Double.parseDouble(sc.nextLine());
        da.setN1(n1);

        System.out.println("Digite N2:");
        double n2 = Double.parseDouble(sc.nextLine());
        da.setN2(n2);

        // recalcula média e aprovação
        diario.calcularMedias();


        System.out.println("Notas atualizadas com sucesso!");
    }

}
