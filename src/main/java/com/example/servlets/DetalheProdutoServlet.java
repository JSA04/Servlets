package com.example.servlets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;

// Importações do Servlet
import com.example.jdbc.Produto.ProdutoConexao;
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

@WebServlet(name = "produtos", value = "/detalhe-produto")
public class DetalheProdutoServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!(EntrarServlet.verificaAutenticacao(request))) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return;
        }

        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        List<Map<String, String>> listaPedidos = new ArrayList<>();
        Map<String, String> produto = new HashMap<>();

        try {
            ResultSet pedidos = new PedidoConexao().buscarPedidosPorProduto(id_produto);

            while (pedidos.next()) {

                Map<String, String> pedido = new HashMap<>(4);

                pedido.put("id_pedido", pedidos.getString("ID_PEDIDO"));
                pedido.put("cliente", pedidos.getString("CLIENTE"));
                pedido.put("total", String.format("R$%.2f", pedidos.getDouble("TOTAL")));
                pedido.put("status", pedidos.getString("STATUS"));

                listaPedidos.add(pedido);
            }

            ResultSet produtoRS = new ProdutoConexao().buscarPorID(id_produto);

            while (produtoRS.next()) {
                produto.put("nome", produtoRS.getString("nome"));
                produto.put("quantidade", produtoRS.getString("quant"));
                produto.put("preco", String.format("R$%.2f", produtoRS.getDouble("preco")));
                produto.put("categoria", produtoRS.getString("categoria"));
                produto.put("validade", new SimpleDateFormat("dd/MM/yyyy").format(produtoRS.getDate("data_validade")));
                produto.put("descricao", produtoRS.getString("descricao"));
                produto.put("imagem", produtoRS.getString("imagem"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("titulo", "Produto N° " + id_produto + ": " + produto);
        request.setAttribute("id_produto", id_produto);
        request.setAttribute("listaPedidos", listaPedidos);
        request.setAttribute("produto", produto);

        request.getRequestDispatcher("detalhe_produto.jsp").forward(request, response);
    }
}
