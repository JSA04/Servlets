package com.example.jdbc.Item;

import java.sql.*;

public interface ItemInterface {
    void conectar();
    void desconectar();
    boolean inserir(Item adm);
    boolean alterar(Item adm);
    boolean remover(Item adm);
    ResultSet buscarPorID(int id);
    ResultSet buscar();
}
