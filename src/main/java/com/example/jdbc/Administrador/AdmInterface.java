package com.example.jdbc.Administrador;

import java.sql.*;

public interface AdmInterface {
    void conectar();
    void desconectar();
    boolean inserir(Administracao adm);
    boolean alterar(Administracao adm);
    boolean remover(Administracao adm);
    ResultSet buscarPorID(int id);
    ResultSet buscar();
}
