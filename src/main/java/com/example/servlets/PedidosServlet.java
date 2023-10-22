package com.example.servlets;

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
        StringBuilder listaPedidos = new StringBuilder();
        String produto = "";

        try {
            ResultSet pedidos = new PedidoConexao().buscarPedidosPorProduto(id_produto);

            while (pedidos.next()) {

                if (pedidos.isFirst()) produto = pedidos.getString("PRODUTO");

                listaPedidos.append(pedidos.getString("ID_PEDIDO"));
                listaPedidos.append("%%");
                listaPedidos.append(pedidos.getString("CLIENTE"));
                listaPedidos.append("%%");
                listaPedidos.append(String.format("R$%.2f", pedidos.getDouble("TOTAL")));
                listaPedidos.append("%%");
                listaPedidos.append(pedidos.getString("STATUS"));

                listaPedidos.append("///");
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
