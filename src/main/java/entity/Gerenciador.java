package main.java.entity;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.entity.member.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Gerenciador {
    private static int id = 0;

    private static List<Professor> professores = new ArrayList<Professor>();
    private static List<Aluno> alunos = new ArrayList<Aluno>();
    private static List<Monitor> monitores = new ArrayList<Monitor>();
    private static List<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private static List<Turma> turmas = new ArrayList<Turma>();
    private static List<Conteudo> conteudos = new ArrayList<Conteudo>();
    private static List<Pergunta> perguntas = new ArrayList<Pergunta>();
    private static Usuario usuarioAtual;

    public Gerenciador() {
        super();
    }

    ////////////         Métodos de gerenciamento de Login  ///////////////////////
    private static boolean verifyLoginAdmin(String email, String senha) {
        if (email.equals("admin") && senha.equals("admin")) {
            Usuario admin = new Aluno(1, 1, 1, "admin", "admin", "admin");
            usuarioAtual = admin;
            return true;
        }

        return false;
    }
    public static boolean login(TipoDeUsuario tipoUsuario, String email, String senha) {
        if (verifyLoginAdmin(email, senha)) {
            System.out.println("****** Admin logged ******");
            return true;
        }
        switch (tipoUsuario) {
            case PROFESSOR:
                return loginProfessor(email, senha);
            case ALUNO:
                return loginAluno(email, senha);
            case MONITOR:
                return loginMonitor(email, senha);
        }
        return false;
    }

    private static boolean loginAluno(String email, String senha) {
        Predicate<Usuario> predicate = usuario -> {
            return usuario.getEmail().equals(email) && usuario.getSenha().equals(senha);
        };

        List<Aluno> alunosAchados = alunos.stream().filter(predicate).collect(Collectors.toList());
        if (alunosAchados.size() < 1) {
            System.out.println("Não foi encontrado aluno cadastrado com o email (" + email + ") e senha (" + senha + ")");
            return false;
        }
        if (alunosAchados.size() > 1) {
            System.out.println("Foram encontrados vários alunos com o email (" + email + ") e senha (" + senha + ") : " + alunosAchados.toString() + ". Será utilizado somente o primeiro.");
            return false;
        }
        usuarioAtual = alunosAchados.get(0);
        return true;
    }

    private static boolean loginProfessor(String email, String senha) {
        Predicate<Usuario> predicate = usuario -> {
            return usuario.getEmail().equals(email) && usuario.getSenha().equals(senha);
        };

        List<Professor> profsAchados = professores.stream().filter(predicate).collect(Collectors.toList());
        if (profsAchados.size() < 1) {
            System.out.println("Não foi encontrado professor cadastrado com o email (" + email + ") e senha (" + senha + ")");
            return false;
        }
        if (profsAchados.size() > 1) {
            System.out.println("Foram encontrados vários professores com o email (" + email + ") e senha (" + senha + ") : " + profsAchados.toString() + ". Será utilizado somente o primeiro.");
            return false;
        }
        usuarioAtual = profsAchados.get(0);
        return true;
    }

    private static boolean loginMonitor(String email, String senha) {
        Predicate<Usuario> predicate = usuario -> {
            return usuario.getEmail().equals(email) && usuario.getSenha().equals(senha);
        };

        List<Monitor> monitoresAchados = monitores.stream().filter(predicate).collect(Collectors.toList());
        if (monitoresAchados.size() < 1) {
            System.out.println("Não foi encontrado monitor cadastrado com o email (" + email + ") e senha (" + senha + ")");
            return false;
        }
        if (monitoresAchados.size() > 1) {
            System.out.println("Foram encontrados vários monitores com o email (" + email + ") e senha (" + senha + ") : " + monitoresAchados.toString() + ". Será utilizado somente o primeiro.");
            return false;
        }
        usuarioAtual = monitoresAchados.get(0);
        return true;
    }

    public static int proximoId() {
        Gerenciador.id++;
        return id;
    }

    public static Usuario deslogar() {
        Usuario usuarioLogado = usuarioAtual;
        usuarioAtual = null;
        return usuarioLogado;
    }

    public static Usuario getUsarioAtual() {
        return usuarioAtual;
    }

    ////////////         Métodos de gerenciamento de Alunos  ///////////////////////
    public static void adicionarUsuatio(Usuario user) {
        if (user == null)
            throw new NullPointerException("[Adicionar Usuario] O usuario a ser adicionado não pode ser nulo");
        if (user instanceof Monitor)
            adicionarMonitor((Monitor) user);
        else if (user instanceof Professor)
            adicionarProfessor((Professor) user);
        else if (user instanceof Aluno)
            adicionarAluno((Aluno) user);
        else
            throw new Error("User deve ser uma instância de Aluno, Professor ou Monitor.");
    }

    public static void adicionarAluno(Aluno aluno) {
        if (aluno == null)
            throw new NullPointerException("[Adicionar Aluno] O aluno a ser adicionado não pode ser nulo");

        Aluno alunoEncontrado = buscaAluno(aluno.ra);

        if (alunoEncontrado != null) {
            throw new Error("[Adicionar Aluno] Aluno com RA " + aluno.ra + " já existe.");
        }

        alunos.add(aluno);
    }

    public static Aluno removerAluno(int ra) {
        Aluno alunoEncontrado = buscaAluno(ra);

        if (alunoEncontrado == null) {
            //Aluno não existe na base;
            return null;
        }

        alunos.remove(alunoEncontrado);
        return alunoEncontrado;
    }

    public static List<Aluno> buscarTodosAlunos() {
        return alunos;
    }

    public static Aluno buscaAluno(int ra) {
        List<Aluno> alunosEncontrados = alunos.stream().filter(aluno -> {
            return aluno.ra == ra;
        }).collect(Collectors.toList());

        if (alunosEncontrados.size() >= 1) {
            return alunosEncontrados.get(0);
        }

        return null;
    }


    public static Aluno buscaAluno(String email) {
        List<Aluno> alunosEncontrados = alunos.stream().filter(aluno -> {
            return aluno.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (alunosEncontrados.size() >= 1) {
            return alunosEncontrados.get(0);
        }

        return null;
    }

    public static void adicionarComentario(Post postagem, String textoComentario) throws Exception {
        if (postagem != null) {
            Comentario comentario = new Comentario(
                    Gerenciador.proximoId(), new Date(), usuarioAtual, textoComentario);
            postagem.addComentario(comentario);
        } else {
            throw new Exception("Não é possível adicionar comentário sem uma postagem!");
        }
    }

    public static void adicionarComentarioEmConteudo(int conteudoId, String textoComentario) throws Exception {
        Conteudo conteudo = buscaConteudo(conteudoId);
        adicionarComentario(conteudo, textoComentario);
    }

    public static void adicionarComentarioEmPergunta(int perguntaId, String textoComentario) throws Exception {
        Pergunta pergunta = buscaPergunta(perguntaId);
        adicionarComentario(pergunta, textoComentario);
    }

    public static Pergunta buscaPergunta(int id) {
        List<Pergunta> perguntasEncontradas = perguntas.stream().filter(pergunta -> {
            return pergunta.getID() == id;
        }).collect(Collectors.toList());
        if (!perguntasEncontradas.isEmpty()) {
            return perguntasEncontradas.get(0);
        }
        return null;
    }

    public static Conteudo buscaConteudo(int id) {
        List<Conteudo> conteudosEncontrados = conteudos.stream().filter(conteudo -> {
            return conteudo.getID() == id;
        }).collect(Collectors.toList());
        if (!conteudosEncontrados.isEmpty()) {
            return conteudosEncontrados.get(0);
        }
        return null;
    }

    public static List<Conteudo> buscaConteudos(String autor) {
        return conteudos.stream().filter(conteudo -> {
            return conteudo.getAutor().getNome().equals(autor);
        }).collect(Collectors.toList());
    }

    public static List<Post> filtrarPorAutor(ArrayList<Post> postagens, String autor) {
        return postagens.stream().filter(post -> {
            return post.getAutor().getNome().equals(autor);
        }).collect(Collectors.toList());
    }



    public static List<Pergunta> buscarPerguntas(String autor) {
        return perguntas.stream().filter(pergunta -> {
            return pergunta.getAutor().getNome().equals(autor);
        }).collect(Collectors.toList());
    }

    public static List<Conteudo> buscaConteudos(){
        return conteudos;
    }

    public static List<Pergunta> buscarPerguntas(){
        return perguntas;
    }

    private static class ComentarioRemocao{
        private Comentario comentario;
        private Post post;

        public ComentarioRemocao(Comentario comentario, Post post){
            this.comentario = comentario;
            this.post = post;
        }

        public Comentario getComentario(){
            return this.comentario;
        }

        public Post getPost(){
            return this.post;
        }
    }

    private static ComentarioRemocao buscarComentarioEmConteudos(int comentarioId){
        if (conteudos != null){
            for (Conteudo conteudo : conteudos){
                List<Comentario> comentariosBuscados = conteudo.getComentarios().stream().filter(
                        comentario -> {return comentario.getID() == comentarioId;}).collect(Collectors.toList());
                if (comentariosBuscados != null && !comentariosBuscados.isEmpty()){
                    return new ComentarioRemocao(comentariosBuscados.get(0), conteudo);
                }
            }
        }
        return null;
    }

    private static ComentarioRemocao buscarComentarioEmPerguntas(int comentarioId){
        if (perguntas != null){
            for (Pergunta pergunta : perguntas){
                List<Comentario> comentariosBuscados = pergunta.getComentarios().stream().filter(
                        comentario -> {return comentario.getID() == comentarioId;}).collect(Collectors.toList());
                if (comentariosBuscados != null && !comentariosBuscados.isEmpty()) {
                    return new ComentarioRemocao(comentariosBuscados.get(0), pergunta);
                }
            }
        }
        return null;
    }

    public static Comentario removerComentarioEmConteudo(int comentarioId){
        ComentarioRemocao comentarioRemocao = buscarComentarioEmConteudos(comentarioId);
        if (comentarioRemocao != null) {
            Conteudo conteudo = (Conteudo)comentarioRemocao.getPost();
            Comentario comentario = comentarioRemocao.getComentario();
            conteudo.removeComentario(comentario);
            return comentario;
        }
        return null;
    }

    public static Comentario removerComentarioEmPergunta(int comentarioId){
        ComentarioRemocao comentarioRemocao = buscarComentarioEmPerguntas(comentarioId);
        if (comentarioRemocao != null) {
            Pergunta pergunta = (Pergunta)comentarioRemocao.getPost();
            Comentario comentario = comentarioRemocao.getComentario();
            pergunta.removeComentario(comentario);
            return comentario;
        }
        return null;
    }

    public static Conteudo removerConteudo(int id){
        Conteudo conteudo = buscaConteudo(id);
        if (conteudo != null){
            conteudos.remove(conteudo);
            return conteudo;
        }
        return null;
    }

    public static Pergunta removerPergunta(int id){
        Pergunta pergunta = buscaPergunta(id);
        if (pergunta != null){
            perguntas.remove(pergunta);
            return pergunta;
        }
        return null;
    }

    public static Post removerPostagem(Post p) {
        if(p instanceof Conteudo)
            return removerConteudo(p.getID());
        if(p instanceof Pergunta)
            return removerPergunta(p.getID());
        if(p instanceof Comentario) {
            Comentario removido = removerComentarioEmConteudo(p.getID());
            if(removido == null)
                return removerComentarioEmPergunta(p.getID());
            return removido;
        }
        return null;
    }

    ////////////         Métodos de gerenciamento de Professores  ///////////////////////
    public static void adicionarProfessor(Professor professor) {
        if (professor == null)
            throw new NullPointerException("[Adicionar Professor] O professor a ser adicionado não pode ser nulo");

        Professor professorEncontrado = buscaProfessor(professor.getEmail());

        if (professorEncontrado != null) {
            throw new Error("[Adicionar Professor] Professor com email " + professor.getEmail() + " já existe.");
        }

        professores.add(professor);
    }

    public static Professor removerProfessor(int id) {
        Professor profEncontrado = buscaProfessor(id);

        if (profEncontrado == null) {
            //Professor não existe na base;
            return null;
        }

        professores.remove(profEncontrado);
        return profEncontrado;
    }

    public static List<Professor> buscarTodosProfessores() {
        return professores;
    }

    public static Professor buscaProfessor(int id) {
        List<Professor> professoresAchados = professores.stream().filter(professor -> {
            return professor.id == id;
        }).collect(Collectors.toList());

        if (professoresAchados.size() >= 1) {
            return professoresAchados.get(0);
        }

        return null;
    }

    public static Professor buscaProfessor(String email) {
        List<Professor> professoresAchados = professores.stream().filter(professor -> {
            return professor.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (professoresAchados.size() >= 1) {
            return professoresAchados.get(0);
        }

        return null;
    }

    ////////////         Métodos de gerenciamento de Monitores  ///////////////////////
    public static void adicionarMonitor(Monitor monitor) {
        if (monitor == null)
            throw new NullPointerException("[Adicionar Monitor] O monitor a ser adicionado não pode ser nulo");

        //Validar se monitor já existe na lista de monitores antes de inserir

        monitores.add(monitor);
    }

    public static void adicionarConteudo(Conteudo conteudo) throws Exception {
        if (conteudo == null) {
            throw new Exception("Conteúdo nulo ao adicionar conteúdo");
        }
        if (conteudos.contains(conteudo)) {
            throw new Exception("Conteúdo já cadastrado");
        }
        conteudos.add(conteudo);
    }

    public static void adicionarPergunta(Pergunta pergunta) throws Exception {
        if (pergunta == null) {
            throw new Exception("Pergunta nula ao adicionar pergunta");
        }
        if (perguntas.contains(pergunta)) {
            throw new Exception("Pergunta já cadastrada");
        }
        perguntas.add(pergunta);
    }

    public static Monitor removerMonitor(int ra) {
        Monitor monitorEncontrado = buscaMonitor(ra);

        if (monitorEncontrado == null) {
            //Monitor não existe na base;
            return null;
        }

        monitores.remove(monitorEncontrado);
        return monitorEncontrado;
    }

    public static List<Monitor> buscarTodosMonitores() {
        return monitores;
    }

    public static Monitor buscaMonitor(int ra) {
        List<Monitor> monitorersAchados = monitores.stream().filter(monitor -> {
            return monitor.ra == ra;
        }).collect(Collectors.toList());

        if (monitorersAchados.size() >= 1) {
            return monitorersAchados.get(0);
        }

        return null;
    }

    public static Monitor buscaMonitor(String email) {
        List<Monitor> monitorersAchados = monitores.stream().filter(monitor -> {
            return monitor.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (monitorersAchados.size() >= 1) {
            return monitorersAchados.get(0);
        }

        return null;
    }

    public static List<Usuario> buscarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (professores != null) {
            usuarios.addAll(professores);
        }
        if (alunos != null) {
            usuarios.addAll(alunos);
        }
        if (monitores != null) {
            usuarios.addAll(monitores);
        }
        return usuarios;
    }

    ////////////         Métodos de gerenciamento de Disciplinas  ///////////////////////
    public static void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina == null)
            throw new NullPointerException("[Adicionar Disciplina] A disciplina a ser adicionado não pode ser nula");

        //Validar se a disciplina já existe na lista de disciplinas antes de inserir

        disciplinas.add(disciplina);
    }

    public static Disciplina removerDisciplina(String codigo) {
        Disciplina disciplinaEncontrada = buscaDisciplina(codigo);

        if (disciplinaEncontrada == null) {
            //Disciplina não existe na base;
            return null;
        }

        disciplinas.remove(disciplinaEncontrada);
        return disciplinaEncontrada;
    }

    public static List<Disciplina> buscarTodasDisciplinas() {
        return disciplinas;
    }

    public static Disciplina buscaDisciplina(String codigo) {
        List<Disciplina> disciplinasAchadas = disciplinas.stream().filter(disciplina -> {
            return disciplina.getCodigo().equals(codigo);
        }).collect(Collectors.toList());

        if (disciplinasAchadas.size() >= 1) {
            return disciplinasAchadas.get(0);
        }

        return null;
    }

    ////////////         Métodos de gerenciamento de Turmas  /////////f//////////////
    public static void adicionarTurma(Turma turma) {
        if (turma == null)
            throw new NullPointerException("[Adicionar Turma] A turma a ser adicionado não pode ser nula");

        //Validar se a turma já existe na lista de turmas antes de inserir

        turmas.add(turma);
    }

    public static Turma removerTurma(int id) {
        Turma turmaEncontrada = buscaTurma(id);

        if (turmaEncontrada == null) {
            //Turma não existe na base;
            return null;
        }

        turmas.remove(turmaEncontrada);
        return turmaEncontrada;
    }

    public static void adicionarAlunoEmTurma(Turma turma, Aluno aluno) {
        try {
            turma.adicionarAluno(aluno);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean removerAlunoEmTurma(Turma turma, List<Aluno> alunos) {
        try {
            return turma.removerAluno(alunos);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removerAlunoEmTurma(Turma turma, Aluno aluno) {
        try {
            return turma.removerAluno(aluno);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Aluno removerAlunoEmTurma(Turma turma, int id) {
        try {
            return turma.removerAluno(id);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void adicionarPostEmTurma(Turma turma, Post post) {
        try {
            turma.adicionarPost(post);

            if (post instanceof Conteudo) {
                adicionarConteudo((Conteudo) post);
            } else {
                adicionarPergunta((Pergunta) post);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean removerPostEmTurma(Turma turma, List<Post> posts) {
        try {
            if (posts instanceof Conteudo) {
                for (Post post : posts) {
                    if (post instanceof Conteudo) {
                        removerConteudo(post.getID());
                    } else {
                        removerPergunta(post.getID());
                    }
                }
            }
            return turma.removerPost(posts);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removerPostEmTurma(Turma turma, Post post) {
        try {
            if (post instanceof Conteudo) {
                removerConteudo(post.getID());
            } else {
                removerPergunta(post.getID());
            }
            return turma.removerPost(post);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Post removerPostEmTurma(Turma turma, int id) {
        try {
            if (buscaConteudo(id) != null) {
                removerConteudo(id);
            } else {
                removerPergunta(id);
            }
            return turma.removerPost(id);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void adicionarMonitorEmTurma(Turma turma, Monitor monitor) {
        try {
            turma.adicionarMonitor(monitor);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean removerMonitorEmTurma(Turma turma, List<Monitor> monitores) {
        try {
            return turma.removerMonitor(monitores);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removerMonitorEmTurma(Turma turma, Monitor monitor) {
        try {
            return turma.removerMonitor(monitor);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Monitor removerMonitorEmTurma(Turma turma, int id) {
        try {
            return turma.removerMonitor(id);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Turma> buscarTodasTurmas() {
        return turmas;
    }

    public static Turma buscaTurma(int id) {
        List<Turma> turmasEncontradas = turmas.stream().filter(turma -> {
            return turma.getId() == id;
        }).collect(Collectors.toList());

        if (turmasEncontradas.size() >= 1) {
            return turmasEncontradas.get(0);
        }

        return null;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Monitor> getMonitores() {
        return monitores;
    }

    public void setMonitores(List<Monitor> monitores) {
        this.monitores = monitores;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public static List<Pergunta> getPerguntas() { return perguntas; }

    public static List<Conteudo> getConteudos() { return conteudos; }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioAtual;
    }

    public static void setUsuarioAtual(Usuario usuarioAtual) {
        Gerenciador.usuarioAtual = usuarioAtual;
    }
}
