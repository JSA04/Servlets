package com.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.example.jdbc.Produto.ProdutoConexao;
import com.example.jdbc.Produto.Produto;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "cadastrar-produto", value = "/cadastrar_produto")
public class CadastroProdutoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome_id");
        // String tipo = request.getParameter("tipo_id");
        String precoString = request.getParameter("preco_id");
        String quantidadeString = request.getParameter("quantidade_id");
        String linkImagem = request.getParameter("imagem_url");
        String descricao = request.getParameter("descricao_id");
        String dataValidadeString = request.getParameter("validade_id");

        long dataValidade1 = Date.parse(dataValidadeString);
        Date dataValidade = new Date(dataValidade1);
        Produto produto = new Produto(nome,0, linkImagem, Integer.parseInt(quantidadeString), Double.parseDouble(precoString), dataValidade, descricao);
        ProdutoConexao produtoConexao = new ProdutoConexao();

        if (produtoConexao.inserir(produto)){
            produtoConexao.inserir(produto);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
}
