package com.example.jdbc.Administrador;

import java.sql.*;

public interface AdmInterface {
    void conectar();
    void desconectar();
    boolean inserir(Administracao adm);
    boolean alterar(Administracao adm);
    boolean remover(Administracao adm);
    ResultSet buscarPorCPF(String cpf);
    ResultSet buscar();
    boolean verificaExistencia(Administracao adm);
}
