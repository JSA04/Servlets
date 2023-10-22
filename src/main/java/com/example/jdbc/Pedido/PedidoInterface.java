package com.example.jdbc.Pedido;


import java.sql.ResultSet;

public interface PedidoInterface {
    void conectar();
    void desconectar();
    boolean inserir(Pedido ped);
    boolean alterar(Pedido ped);
    boolean remover(Pedido ped);
    ResultSet buscarItensPorIDPedido(int id_pedido);
    ResultSet buscarPorID(int id);
    ResultSet buscar();
}
