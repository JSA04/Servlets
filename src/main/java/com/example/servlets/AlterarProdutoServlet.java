package com.example.servlets;

import com.example.jdbc.Produto.Produto;
import com.example.jdbc.Produto.ProdutoConexao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "alterar-produto", value = "/alterar_produto")
public class AlterarProdutoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (!(EntrarServlet.verificaAutenticacao(request))) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id_produto"));
        ResultSet produto_info = new ProdutoConexao().buscarPorID(id);

        String nome = null, categoria = null, imagem_url = null, descricao = null;
        int quantidade = 0;
        double preco = 0;
        String validade = null;

        try {
            if (produto_info != null){
                while (produto_info.next()){
                    nome = produto_info.getString("nome");
                    preco = produto_info.getDouble("preco");
                    descricao = produto_info.getString("descricao");
                    validade = produto_info.getString("data_validade");
                    quantidade = produto_info.getInt("quant");
                    imagem_url = produto_info.getString("imagem");
                    categoria = produto_info.getString("categoria");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("id_produto", id);
        request.setAttribute("nome", nome);
        request.setAttribute("preco", preco);
        request.setAttribute("descricao", descricao);
        request.setAttribute("validade", validade);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("imagem_url", imagem_url);
        request.setAttribute("categoria", categoria);

        request.getRequestDispatcher("alterar_produto.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (!(EntrarServlet.verificaAutenticacao(request))) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id_produto"));
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String descricao = request.getParameter("descricao");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String imagem_url = request.getParameter("imagem_url");
        String categoria = request.getParameter("categoria");

        Produto produtoAlterado = new Produto(nome, preco, descricao, null, id, quantidade, imagem_url, null, categoria);
        new ProdutoConexao().alterar(produtoAlterado);

        request.getRequestDispatcher("/dashboard").forward(request, response);
    }
}
