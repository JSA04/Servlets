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

@WebServlet(name = "pedidos", value = "/pedidos")
public class PedidosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        List<Map<String, String>> listaPedidos = new ArrayList<>();
        String produto = "";

        try {
            ResultSet pedidos = new PedidoConexao().buscarPedidosPorProduto(id_produto);

            while (pedidos.next()) {

                if (pedidos.isFirst()) produto = pedidos.getString("PRODUTO");

                Map<String, String> pedido = new HashMap<>(4);

                pedido.put("id_pedido", pedidos.getString("ID_PEDIDO"));
                pedido.put("cliente", pedidos.getString("CLIENTE"));
                pedido.put("total", String.format("R$%.2f", pedidos.getDouble("TOTAL")));
                pedido.put("status", pedidos.getString("STATUS"));

                listaPedidos.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("titulo", "Produto N° " + id_produto + ": " + produto);
        request.setAttribute("id_produto", id_produto);
        request.setAttribute("listaPedidos", listaPedidos.toString());

        request.getRequestDispatcher("pedido.jsp").forward(request, response);
    }
}
