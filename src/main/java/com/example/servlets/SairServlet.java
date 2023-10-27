package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "sair", value = "/sair")
public class SairServlet extends HttpServlet {
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
            HttpSession session = request.getSession();

            session.setAttribute("usuario", null);
            session.setAttribute("idUsuario", null);
            session.setAttribute("classMsg", "sucesso_msg");
            session.setAttribute("msg", "Você foi deslogado com sucesso!");

            request.getRequestDispatcher("/entrar.jsp").forward(request, response);
        }

}
