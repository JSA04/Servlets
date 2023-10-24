package com.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.example.jdbc.Produto.ProdutoConexao;
import com.example.jdbc.Produto.Produto;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;

@WebServlet(name = "cadastrar-produto", value = "/cadastrar_produto")
public class CadastroProdutoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        // String tipo = request.getParameter("tipo");
        double preco = Double.parseDouble(String.valueOf(request.getParameter("preco")));
        // Date dataValidade = new Date(Date.parse(request.getParameter("validade")));
        // int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String linkImagem = request.getParameter("imagem_url");
        // String descricao = request.getParameter("descricao_id");

        int id = new Random().nextInt(10);;
        boolean id_igual = true;
        while (id_igual) {
            id = new Random().nextInt(10);
            try {
                if (!(new ProdutoConexao().buscarPorID(id).next())) {
                    id_igual = false;
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        Produto produto = new Produto(nome, preco, "", new Date(12), id, 1, linkImagem, 1, "");
        new ProdutoConexao().inserir(produto);

        request.getRequestDispatcher("/dashboard").forward(request, response);
    }
}
