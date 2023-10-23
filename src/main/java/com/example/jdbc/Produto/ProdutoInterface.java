package com.example.jdbc.Produto;

import java.sql.ResultSet;

public interface ProdutoInterface {
    void conectar();
    void desconectar();
    boolean inserir(Produto pro);
    boolean alterar(Produto pro);
    boolean remover(int id_produto);
    ResultSet buscarPorID(int id);
    ResultSet buscar();
}
