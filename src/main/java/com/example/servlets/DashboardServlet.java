package com.example.servlets;

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

        StringBuilder listaProdutos = new StringBuilder();

        try {
            ResultSet produtos = new ProdutoConexao().buscar();

            while (produtos.next()){

                listaProdutos.append(produtos.getInt("id"));
                listaProdutos.append("%%");
                listaProdutos.append(produtos.getString("nome"));
                listaProdutos.append("%%");
                listaProdutos.append(produtos.getString("imagem"));

                listaProdutos.append("///");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("titulo", "Produtos");
        request.setAttribute("listaProdutos", listaProdutos.toString());

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
