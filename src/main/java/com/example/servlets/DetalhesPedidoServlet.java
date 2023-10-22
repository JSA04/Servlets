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

@WebServlet(name = "detalhes-pedido", value = "/detalhe-pedido")
public class DetalhesPedidoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
        String cliente = "";
        String total = "";
        StringBuilder listaItems = new StringBuilder();

        try {
            ResultSet pedidos = new PedidoConexao().buscarItensPorIDPedido(id_pedido);

            while (pedidos.next()){

                if (pedidos.isFirst()) {
                    cliente = pedidos.getString("CLIENTE");
                    total = String.format("R$%.2f", pedidos.getDouble("TOTAL"));
                }

                listaItems.append(pedidos.getString("DESC"));
                listaItems.append("%%");
                listaItems.append(pedidos.getString("QTD"));
                listaItems.append("%%");
                listaItems.append(String.format("R$%.2f", pedidos.getDouble("VALOR_UNI")));

                listaItems.append("///");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("id_pedido", id_pedido);
        request.setAttribute("titulo", "Pedido N° " + id_pedido);
        request.setAttribute("cliente", cliente);
        request.setAttribute("listaItems", listaItems.toString());
        request.setAttribute("total", total);


        request.getRequestDispatcher("detalhe_pedido.jsp").forward(request, response);
    }
}
