package com.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.example.jdbc.Produto.ProdutoConexao;
import com.example.jdbc.Produto.Produto;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

@WebServlet(name = "cadastrar-produto", value = "/cadastrar_produto")
public class CadastroProdutoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (!(EntrarServlet.verificaAutenticacao(request))) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return;
        }

        String nome = request.getParameter("nome");
        String categoria = request.getParameter("categoria");
        double preco = Double.parseDouble(String.valueOf(request.getParameter("preco")));
        String dataValidade = request.getParameter("validade");
        String[] dv = dataValidade.split("-");
        Date dataValidadeCorrigida = Date.valueOf(LocalDate.of(Integer.parseInt(dv[0]), Integer.parseInt(dv[1]), Integer.parseInt(dv[2])));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String linkImagem = request.getParameter("imagem_url");
        String descricao = request.getParameter("descricao");

        int id = new Random().nextInt(10);
        boolean id_igual = true;
        while (id_igual) {
            try {
                if (!(new ProdutoConexao().buscarPorID(id).next())) {
                    id_igual = false;
                } else {
                    id = new Random().nextInt(10);
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        Produto produto = new Produto(nome, preco, descricao, dataValidadeCorrigida, id, quantidade, linkImagem, (int) session.getAttribute("idUsuario"), categoria);
        new ProdutoConexao().inserir(produto);

        request.getRequestDispatcher("/dashboard").forward(request, response);
    }
}
