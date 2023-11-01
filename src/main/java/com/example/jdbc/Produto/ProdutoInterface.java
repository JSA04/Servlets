package com.example.jdbc.Produto;

import java.sql.Date;
import java.sql.ResultSet;

public interface ProdutoInterface {
    void conectar();
    void desconectar();
    boolean inserir(Produto pro);
    boolean alterar(Produto pro);
    boolean softDelete(int id_produto);
    boolean remover(int id_produto);
    ResultSet buscarPorID(int id);
    ResultSet pesquisar();
    ResultSet pesquisar(String nome);
    ResultSet pesquisar(Date data);
    ResultSet pesquisar(String nome, Date data);
}
