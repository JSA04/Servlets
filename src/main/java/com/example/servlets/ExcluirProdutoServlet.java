package com.example.servlets;

import com.example.jdbc.Produto.ProdutoConexao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "excluir-produto", value = "/excluir")
public class ExcluirProdutoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_produto = Integer.parseInt(request.getParameter("id_produto"));

        new ProdutoConexao().softDelete(id_produto);

        request.getRequestDispatcher("/dashboard").forward(request, response);
    }

}
