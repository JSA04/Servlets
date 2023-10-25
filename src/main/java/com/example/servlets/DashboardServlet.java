package com.example.servlets;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

// Importações do Servlet
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

// Importações do JDBC
import com.example.jdbc.Produto.ProdutoConexao;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "dashboard", value = "/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processo(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processo(request, response);
    }

    protected void processo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!(EntrarServlet.verificaAutenticacao(request, response))) return;

        List<Map<String, String>> listaProdutos = new ArrayList<>();

        try {
            ResultSet produtos = new ProdutoConexao().buscar();

            while (produtos.next()){

                if (produtos.getBoolean("ativado")) {
                    Map<String, String> produto = new HashMap<>(3);

                    produto.put("id", produtos.getString("id"));
                    produto.put("nome", produtos.getString("nome"));
                    produto.put("imagem", produtos.getString("imagem"));

                    listaProdutos.add(produto);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("titulo", "Produtos");
        request.setAttribute("listaProdutos", listaProdutos);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

}

