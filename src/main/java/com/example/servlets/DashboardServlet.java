package com.example.servlets;

import java.sql.Date;
import java.time.LocalDate;
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

        //se a autenticação não funcionar o código redireciona o usuário para a página "entrar.jsp"
        if (!(EntrarServlet.verificaAutenticacao(request))) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return;
        }

        //lista que vai armazenar informações sobre produtos.
        List<Map<String, String>> listaProdutos = new ArrayList<>();

        boolean pesquisa;
        try {
            ResultSet produtos;
            pesquisa = true;
            Date filtroData = null;
            String filtroNome = request.getParameter("busca_input");
            String filtroDataStr = request.getParameter("data_input");

            if (filtroDataStr != null) {
                try {
                    String[] dv = filtroDataStr.split("-");
                    filtroData = Date.valueOf(LocalDate.of(Integer.parseInt(dv[0]), Integer.parseInt(dv[1]), Integer.parseInt(dv[2])));
                } catch (NumberFormatException err) {
                    filtroData = null;
                }
            }

            try { if (filtroNome.isEmpty()) filtroNome = null; }
            catch (NullPointerException err) { filtroNome = null; }

            if (filtroNome != null && filtroData != null) produtos = new ProdutoConexao().pesquisar(filtroNome, filtroData);
            else if (filtroNome != null) produtos = new ProdutoConexao().pesquisar(filtroNome);
            else if (filtroData != null) produtos = new ProdutoConexao().pesquisar(filtroData);
            else {
                pesquisa = false;
                produtos = new ProdutoConexao().pesquisar();
            }

            //busca produtos no banco de dados
            if (produtos != null) {
                while (produtos.next()) {

                    if (produtos.getBoolean("ativado")) {
                        //armazenar informações sobre o produto, como seu ID, nome e imagem
                        Map<String, String> produto = new HashMap<>(3);

                        produto.put("id", produtos.getString("id"));
                        produto.put("nome", produtos.getString("nome"));
                        produto.put("imagem", produtos.getString("imagem"));

                        //armazenha na lista
                        listaProdutos.add(produto);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("titulo", "Produtos");
        request.setAttribute("listaProdutos", listaProdutos);
        request.setAttribute("pesquisa", pesquisa);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

}

