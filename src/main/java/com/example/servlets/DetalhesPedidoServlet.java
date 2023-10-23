package com.example.servlets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Importações do Servlet
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

// Importações do JDBC
import com.example.jdbc.Pedido.PedidoConexao;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(name = "detalhe-pedido", value = "/detalhe-pedido")
public class DetalhesPedidoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
        String cliente = "";
        String total = "";
        List<Map<String, String>> listaItems = new ArrayList<>();

        try {
            ResultSet pedidos = new PedidoConexao().buscarItensPorIDPedido(id_pedido);

            while (pedidos.next()){

                if (pedidos.isFirst()) {
                    cliente = pedidos.getString("CLIENTE");
                    total = String.format("R$%.2f", pedidos.getDouble("TOTAL"));
                }

                Map<String, String> pedido = new HashMap<>(3);

                pedido.put("nome", pedidos.getString("DESC"));
                pedido.put("qtd", pedidos.getString("QTD"));
                pedido.put("valor_uni", String.format("R$%.2f", pedidos.getDouble("VALOR_UNI")));

                listaItems.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("id_pedido", id_pedido);
        request.setAttribute("titulo", "Pedido N° " + id_pedido);
        request.setAttribute("cliente", cliente);
        request.setAttribute("listaItems", listaItems);
        request.setAttribute("total", total);


        request.getRequestDispatcher("detalhe_pedido.jsp").forward(request, response);
    }
}
