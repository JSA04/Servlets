package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AutenticacaoServlet extends HttpServlet {
    public static boolean verificaAutenticacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("usuario") == null) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return false;
        } else return true;

    }
}
