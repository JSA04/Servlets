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

    //solicitar que o servidor web aceite os dados anexados no corpo da mensagem de requisição para armazenamento.
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //cria uma sessão se um já não existe para este usuário
        HttpSession session = request.getSession();

        //se o login não for null ele vai para a tela de deshboard
        if (!(EntrarServlet.verificaAutenticacao(request))) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return;
        }

        //cria as variavei guardando o que o usuario responder
        String nome = request.getParameter("nome");
        String categoria = request.getParameter("categoria");
        double preco = Double.parseDouble(String.valueOf(request.getParameter("preco")));
        String dataValidade = request.getParameter("validade");
        String[] dv = dataValidade.split("-");
        Date dataValidadeCorrigida = Date.valueOf(LocalDate.of(Integer.parseInt(dv[0]), Integer.parseInt(dv[1]), Integer.parseInt(dv[2])));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String linkImagem = request.getParameter("imagem_url");
        String descricao = request.getParameter("descricao");

        //gera um id que não existe para por na tabela
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

        //objeto
        Produto produto = new Produto(nome, preco, descricao, dataValidadeCorrigida, id, quantidade, linkImagem, (int) session.getAttribute("idUsuario"), categoria);
        new ProdutoConexao().inserir(produto);

        //no fim do cadastro ele vai para a tela dashboard
        request.getRequestDispatcher("/dashboard").forward(request, response);
    }
}
