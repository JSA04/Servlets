package com.example.jdbc.Historico;

import java.sql.ResultSet;

public interface HistoricoInterface {
    void conectar();
    void desconectar();
    boolean inserir(Historico historico);
    boolean alterar(Historico historico);
    boolean remover(Historico historico);
    ResultSet buscarPorID(int id);
    ResultSet buscar();
}
