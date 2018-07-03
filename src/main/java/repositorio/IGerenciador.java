package main.java.repositorio;

import java.util.List;

public interface IGerenciador<T> {

    void add(T obj) throws Exception;
    T find(int id) throws Exception;
    T find(String id) throws Exception;
    T remover(String id) throws Exception;
    boolean remover(T obj) throws Exception;
    List<T> buscarTodos() throws Exception;

}
