package main.java.repositorio;

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

public class GerenciadorMonitor implements IGerenciador<Monitor>{
    private static int id = 0;

    private static List<Monitor> monitores = new ArrayList<Monitor>();

    public GerenciadorMonitor() {
        super();
    }

    ////////////         Métodos de gerenciamento de Monitores  ///////////////////////
    public static void adicionarMonitor(Monitor monitor) throws Exception{
        if (monitor == null)
            throw new NullPointerException("[Adicionar Monitor] O monitor a ser adicionado não pode ser nulo");

        //Validar se monitor já existe na lista de monitores antes de inserir

        monitores.add(monitor);
    }

    public static Monitor removerMonitor(int ra) throws Exception{
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

    public static Monitor buscaMonitor(int ra) throws Exception{
        List<Monitor> monitorersAchados = monitores.stream().filter(monitor -> {
            return monitor.ra == ra;
        }).collect(Collectors.toList());

        if (monitorersAchados.size() >= 1) {
            return monitorersAchados.get(0);
        }

        return null;
    }

    public static Monitor buscaMonitor(String email) throws Exception{
        List<Monitor> monitorersAchados = monitores.stream().filter(monitor -> {
            return monitor.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (monitorersAchados.size() >= 1) {
            return monitorersAchados.get(0);
        }

        return null;
    }

    public List<Monitor> getMonitores() {
        return monitores;
    }

    public void setMonitores(List<Monitor> monitores) {
        this.monitores = monitores;
    }

    @Override
    public void add(Monitor monitor) throws Exception {
        if (monitor == null)
            throw new NullPointerException("[Adicionar Monitor] O monitor a ser adicionado não pode ser nulo");

        //Validar se monitor já existe na lista de monitores antes de inserir

        monitores.add(monitor);
    }

    @Override
    public Monitor find(int ra) throws Exception {
        List<Monitor> monitorersAchados = monitores.stream().filter(monitor -> {
            return monitor.ra == ra;
        }).collect(Collectors.toList());

        if (monitorersAchados.size() >= 1) {
            return monitorersAchados.get(0);
        }

        return null;
    }

    @Override
    public Monitor find(String email) throws Exception {
        List<Monitor> monitorersAchados = monitores.stream().filter(monitor -> {
            return monitor.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (monitorersAchados.size() >= 1) {
            return monitorersAchados.get(0);
        }

        return null;
    }

    @Override
    public Monitor remover(String ra) throws Exception {
        Monitor monitorEncontrado = buscaMonitor(ra);

        if (monitorEncontrado == null) {
            //Monitor não existe na base;
            return null;
        }

        monitores.remove(monitorEncontrado);
        return monitorEncontrado;
    }

    @Override
    public boolean remover(Monitor obj) throws Exception {
        return monitores.remove(obj);
    }

    @Override
    public List<Monitor> buscarTodos() throws Exception {
        return monitores;
    }
}
